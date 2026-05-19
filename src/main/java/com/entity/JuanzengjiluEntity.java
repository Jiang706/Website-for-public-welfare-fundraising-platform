package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 捐赠记录
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-05-18 16:39:31
 */
@TableName("juanzengjilu")
public class JuanzengjiluEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public JuanzengjiluEntity() {
		
	}
	
	public JuanzengjiluEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 捐赠编号
	 */
					
	private String juanzengbianhao;
	
	/**
	 * 项目名称
	 */
					
	private String xiangmumingcheng;
	
	/**
	 * 项目类型
	 */
					
	private String xiangmuleixing;
	
	/**
	 * 地区
	 */
					
	private String diqu;
	
	/**
	 * 图片
	 */
					
	private String tupian;
	
	/**
	 * 捐赠金额
	 */
					
	private Double yijuanjine;
	
	/**
	 * 捐赠详情
	 */
					
	private String juanzengxiangqing;
	
	/**
	 * 捐赠时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date juanzengshijian;
	
	/**
	 * 捐赠账号
	 */
					
	private String juanzengzhanghao;
	
	/**
	 * 捐赠姓名
	 */
					
	private String xingming;
	
	/**
	 * 募捐者账号
	 */
					
	private String mujuanzhezhanghao;
	
	/**
	 * 募捐者姓名
	 */
					
	private String mujuanzhexingming;
	
	/**
	 * 是否支付
	 */
					
	private String ispay;

	/**
	 * 募捐项目ID
	 */
	private Long mujuanxiangmuId;

	/**
	 * 支付时间
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date payTime;

	/**
	 * Merkle状态
	 */
	private String merkleStatus;

	/**
	 * Merkle批次号
	 */
	private String merkleBatchNo;

	/**
	 * 叶子哈希
	 */
	private String leafHash;
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：捐赠编号
	 */
	public void setJuanzengbianhao(String juanzengbianhao) {
		this.juanzengbianhao = juanzengbianhao;
	}
	/**
	 * 获取：捐赠编号
	 */
	public String getJuanzengbianhao() {
		return juanzengbianhao;
	}
	/**
	 * 设置：项目名称
	 */
	public void setXiangmumingcheng(String xiangmumingcheng) {
		this.xiangmumingcheng = xiangmumingcheng;
	}
	/**
	 * 获取：项目名称
	 */
	public String getXiangmumingcheng() {
		return xiangmumingcheng;
	}
	/**
	 * 设置：项目类型
	 */
	public void setXiangmuleixing(String xiangmuleixing) {
		this.xiangmuleixing = xiangmuleixing;
	}
	/**
	 * 获取：项目类型
	 */
	public String getXiangmuleixing() {
		return xiangmuleixing;
	}
	/**
	 * 设置：地区
	 */
	public void setDiqu(String diqu) {
		this.diqu = diqu;
	}
	/**
	 * 获取：地区
	 */
	public String getDiqu() {
		return diqu;
	}
	/**
	 * 设置：图片
	 */
	public void setTupian(String tupian) {
		this.tupian = tupian;
	}
	/**
	 * 获取：图片
	 */
	public String getTupian() {
		return tupian;
	}
	/**
	 * 设置：捐赠金额
	 */
	public void setYijuanjine(Double yijuanjine) {
		this.yijuanjine = yijuanjine;
	}
	/**
	 * 获取：捐赠金额
	 */
	public Double getYijuanjine() {
		return yijuanjine;
	}
	/**
	 * 设置：捐赠详情
	 */
	public void setJuanzengxiangqing(String juanzengxiangqing) {
		this.juanzengxiangqing = juanzengxiangqing;
	}
	/**
	 * 获取：捐赠详情
	 */
	public String getJuanzengxiangqing() {
		return juanzengxiangqing;
	}
	/**
	 * 设置：捐赠时间
	 */
	public void setJuanzengshijian(Date juanzengshijian) {
		this.juanzengshijian = juanzengshijian;
	}
	/**
	 * 获取：捐赠时间
	 */
	public Date getJuanzengshijian() {
		return juanzengshijian;
	}
	/**
	 * 设置：捐赠账号
	 */
	public void setJuanzengzhanghao(String juanzengzhanghao) {
		this.juanzengzhanghao = juanzengzhanghao;
	}
	/**
	 * 获取：捐赠账号
	 */
	public String getJuanzengzhanghao() {
		return juanzengzhanghao;
	}
	/**
	 * 设置：捐赠姓名
	 */
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	/**
	 * 获取：捐赠姓名
	 */
	public String getXingming() {
		return xingming;
	}
	/**
	 * 设置：募捐者账号
	 */
	public void setMujuanzhezhanghao(String mujuanzhezhanghao) {
		this.mujuanzhezhanghao = mujuanzhezhanghao;
	}
	/**
	 * 获取：募捐者账号
	 */
	public String getMujuanzhezhanghao() {
		return mujuanzhezhanghao;
	}
	/**
	 * 设置：募捐者姓名
	 */
	public void setMujuanzhexingming(String mujuanzhexingming) {
		this.mujuanzhexingming = mujuanzhexingming;
	}
	/**
	 * 获取：募捐者姓名
	 */
	public String getMujuanzhexingming() {
		return mujuanzhexingming;
	}
	/**
	 * 设置：是否支付
	 */
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	/**
	 * 获取：是否支付
	 */
	public String getIspay() {
		return ispay;
	}

	public Long getMujuanxiangmuId() {
		return mujuanxiangmuId;
	}

	public void setMujuanxiangmuId(Long mujuanxiangmuId) {
		this.mujuanxiangmuId = mujuanxiangmuId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getMerkleStatus() {
		return merkleStatus;
	}

	public void setMerkleStatus(String merkleStatus) {
		this.merkleStatus = merkleStatus;
	}

	public String getMerkleBatchNo() {
		return merkleBatchNo;
	}

	public void setMerkleBatchNo(String merkleBatchNo) {
		this.merkleBatchNo = merkleBatchNo;
	}

	public String getLeafHash() {
		return leafHash;
	}

	public void setLeafHash(String leafHash) {
		this.leafHash = leafHash;
	}

}
