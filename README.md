# 基于 Merkle 树的透明化慈善募捐系统

本项目在原有 `Spring Boot + MySQL + Vue` 募捐系统基础上，新增了 Merkle 树可信存证能力，用于提升捐赠记录可验证性与公信力。

## 1. 项目简介

原系统已支持：
- 募捐项目发布与管理
- 捐赠记录创建与支付
- 前后台管理与统计

本次新增：
- 支付后捐赠记录自动进入 Merkle 存证流程
- 定时批量构建 Merkle 批次（Root）
- 生成每笔捐赠的 Merkle Proof
- 提供公开验证接口（可独立验证记录完整性）

## 2. 技术栈

- 后端：Spring Boot, MyBatis-Plus
- 数据库：MySQL
- 前端：Vue2 + ElementUI
- 构建：Maven Wrapper (`mvnw.cmd`)
- 哈希算法：SHA-256

## 3. 新增功能总览（本次实现）

### 3.1 支付流程收口

过去支付页通过前端直接修改 `ispay` 并调用 `update`。

现在新增后端专用接口：
- `POST /juanzengjilu/pay/{id}`

作用：
- 服务端完成支付状态变更
- 记录支付时间
- 生成叶子哈希
- 标记 `merkle_status = PENDING`

### 3.2 Merkle 批次构建

新增定时任务：
- 按配置 cron 周期扫描 `PENDING` 且已支付记录
- 按项目分组构建 Merkle 树
- 生成 Root、批次号、Proof
- 将记录状态更新为 `COMMITTED`

默认 cron：
- `0 */10 * * * ?`（每 10 分钟）

### 3.3 证明与验证接口

新增控制器：`/merkle`

- `GET /merkle/public/roots`
  - 公共 Root 列表（支持按项目筛选）
- `GET /merkle/proof/{donationId}`
  - 获取指定捐赠的 proof 信息
- `POST /merkle/verify`
  - 验证指定捐赠 proof 是否匹配 root
- `POST /merkle/admin/build`
  - 管理端手动触发批次构建

### 3.4 前端交互

- 前台/后台支付页改为优先调用 `POST /juanzengjilu/pay/{id}`
- 捐赠详情新增：
  - Merkle 状态
  - Merkle 批次号
  - 下载证明
  - 去验证
- 新增前台验证页：`/index/merkleVerify`

## 4. 数据库变更

在 `db/springboot51ug89lx.sql` 已追加 Merkle 扩展 SQL：

### 4.1 `juanzengjilu` 增加字段
- `mujuanxiangmu_id`
- `pay_time`
- `merkle_status`
- `merkle_batch_no`
- `leaf_hash`

### 4.2 新增表
- `merkle_batch`
- `merkle_proof`

## 5. 关键后端文件

- `src/main/java/com/controller/JuanzengjiluController.java`
  - 新增 `/pay/{id}`
- `src/main/java/com/controller/MerkleController.java`
  - proof / verify / roots / admin build
- `src/main/java/com/service/MerkleTrustService.java`
  - 叶子构造、建树、proof、验证核心逻辑
- `src/main/java/com/task/MerkleBatchTask.java`
  - 定时构建任务
- `src/main/java/com/utils/MerkleTreeUtil.java`
  - SHA-256、root 构建、proof 生成与校验

## 6. 启动与编译

### 6.1 环境准备

- JDK 8+
- MySQL 5.7/8.0

### 6.2 配置

编辑：
- `src/main/resources/application.yml`

配置你的数据库连接：
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

### 6.3 执行 SQL

导入：
- `db/springboot51ug89lx.sql`

### 6.4 编译运行

Windows：

```bash
.\mvnw.cmd -DskipTests compile
.\mvnw.cmd spring-boot:run
```

## 7. 验证示例流程

1. 在项目详情发起在线捐赠
2. 在支付页完成支付（走 `/juanzengjilu/pay/{id}`）
3. 等待定时任务或手动调用 `/merkle/admin/build`
4. 在捐赠详情点击“下载证明”
5. 打开“去验证”页面，校验结果应为“验证通过”

## 8. 当前状态

- [x] Merkle 数据结构接入
- [x] 支付到存证链路打通
- [x] Proof 生成与验证接口
- [x] 前台验证入口
- [x] 编译通过（`mvnw.cmd -DskipTests compile`）

## 9. 后续可扩展方向

- Root 上链（第三方时间戳/区块链锚定）
- 公示看板（按项目展示批次、Root、验证统计）
- 验证失败告警与审计报表

---

如需进一步支持论文写作，可基于本实现继续补充：
- 系统架构图
- 时序图（支付→存证→验证）
- 安全性分析（抗篡改性、可审计性、局限性）
