-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: springboot51ug89lx
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `springboot51ug89lx`
--

/*!40000 DROP DATABASE IF EXISTS `springboot51ug89lx`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `springboot51ug89lx` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `springboot51ug89lx`;

--
-- Table structure for table `chatmessage`
--

DROP TABLE IF EXISTS `chatmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chatmessage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `fid` bigint(20) NOT NULL COMMENT '好友用户ID',
  `content` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容',
  `format` int(11) DEFAULT NULL COMMENT '格式(1:文字，2:图片)',
  `isread` int(11) DEFAULT '0' COMMENT '消息已读(0:未读，1:已读)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatmessage`
--

LOCK TABLES `chatmessage` WRITE;
/*!40000 ALTER TABLE `chatmessage` DISABLE KEYS */;
/*!40000 ALTER TABLE `chatmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/picture1.jpg',NULL),(2,'picture2','upload/picture2.jpg',NULL),(3,'picture3','upload/picture3.jpg',NULL);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diquxinxi`
--

DROP TABLE IF EXISTS `diquxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diquxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `diqu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  PRIMARY KEY (`id`),
  UNIQUE KEY `diqu` (`diqu`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地区信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diquxinxi`
--

LOCK TABLES `diquxinxi` WRITE;
/*!40000 ALTER TABLE `diquxinxi` DISABLE KEYS */;
INSERT INTO `diquxinxi` VALUES (1,'2025-05-18 08:40:11','地区1'),(2,'2025-05-18 08:40:11','地区2'),(3,'2025-05-18 08:40:11','地区3'),(4,'2025-05-18 08:40:11','地区4'),(5,'2025-05-18 08:40:11','地区5'),(6,'2025-05-18 08:40:11','地区6'),(7,'2025-05-18 08:40:11','地区7'),(8,'2025-05-18 08:40:11','地区8');
/*!40000 ALTER TABLE `diquxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussmujuanxiangmu`
--

DROP TABLE IF EXISTS `discussmujuanxiangmu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussmujuanxiangmu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `istop` int(11) DEFAULT '0' COMMENT '置顶(1:置顶,0:非置顶)',
  `tuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `cuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='募捐项目评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussmujuanxiangmu`
--

LOCK TABLES `discussmujuanxiangmu` WRITE;
/*!40000 ALTER TABLE `discussmujuanxiangmu` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussmujuanxiangmu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussxiangmujinzhan`
--

DROP TABLE IF EXISTS `discussxiangmujinzhan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussxiangmujinzhan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `istop` int(11) DEFAULT '0' COMMENT '置顶(1:置顶,0:非置顶)',
  `tuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `cuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目进展评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussxiangmujinzhan`
--

LOCK TABLES `discussxiangmujinzhan` WRITE;
/*!40000 ALTER TABLE `discussxiangmujinzhan` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussxiangmujinzhan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend`
--

DROP TABLE IF EXISTS `friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `fid` bigint(20) NOT NULL COMMENT '好友用户ID',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `alias` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '别名',
  `type` int(11) DEFAULT '0' COMMENT '类型(0:好友申请，1:好友，2:消息)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='好友表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend`
--

LOCK TABLES `friend` WRITE;
/*!40000 ALTER TABLE `friend` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gonggaoxinxi`
--

DROP TABLE IF EXISTS `gonggaoxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gonggaoxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `biaoti` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `jianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `fabushijian` date DEFAULT NULL COMMENT '发布时间',
  `fengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '封面',
  `neirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '内容',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='公告信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gonggaoxinxi`
--

LOCK TABLES `gonggaoxinxi` WRITE;
/*!40000 ALTER TABLE `gonggaoxinxi` DISABLE KEYS */;
INSERT INTO `gonggaoxinxi` VALUES (1,'2025-05-18 08:40:11','标题1','简介1','2025-05-18','upload/gonggaoxinxi_fengmian1.jpg,upload/gonggaoxinxi_fengmian2.jpg,upload/gonggaoxinxi_fengmian3.jpg','内容1',1),(2,'2025-05-18 08:40:11','标题2','简介2','2025-05-18','upload/gonggaoxinxi_fengmian2.jpg,upload/gonggaoxinxi_fengmian3.jpg,upload/gonggaoxinxi_fengmian4.jpg','内容2',2),(3,'2025-05-18 08:40:11','标题3','简介3','2025-05-18','upload/gonggaoxinxi_fengmian3.jpg,upload/gonggaoxinxi_fengmian4.jpg,upload/gonggaoxinxi_fengmian5.jpg','内容3',3),(4,'2025-05-18 08:40:11','标题4','简介4','2025-05-18','upload/gonggaoxinxi_fengmian4.jpg,upload/gonggaoxinxi_fengmian5.jpg,upload/gonggaoxinxi_fengmian6.jpg','内容4',4),(5,'2025-05-18 08:40:11','标题5','简介5','2025-05-18','upload/gonggaoxinxi_fengmian5.jpg,upload/gonggaoxinxi_fengmian6.jpg,upload/gonggaoxinxi_fengmian7.jpg','内容5',5),(6,'2025-05-18 08:40:11','标题6','简介6','2025-05-18','upload/gonggaoxinxi_fengmian6.jpg,upload/gonggaoxinxi_fengmian7.jpg,upload/gonggaoxinxi_fengmian8.jpg','内容6',6),(7,'2025-05-18 08:40:11','标题7','简介7','2025-05-18','upload/gonggaoxinxi_fengmian7.jpg,upload/gonggaoxinxi_fengmian8.jpg,upload/gonggaoxinxi_fengmian1.jpg','内容7',7),(8,'2025-05-18 08:40:11','标题8','简介8','2025-05-18','upload/gonggaoxinxi_fengmian8.jpg,upload/gonggaoxinxi_fengmian1.jpg,upload/gonggaoxinxi_fengmian2.jpg','内容8',8);
/*!40000 ALTER TABLE `gonggaoxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juanzengjilu`
--

DROP TABLE IF EXISTS `juanzengjilu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `juanzengjilu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `juanzengbianhao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '捐赠编号',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `diqu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `yijuanjine` double NOT NULL COMMENT '捐赠金额',
  `juanzengxiangqing` longtext COLLATE utf8mb4_unicode_ci COMMENT '捐赠详情',
  `juanzengshijian` datetime DEFAULT NULL COMMENT '捐赠时间',
  `juanzengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '捐赠账号',
  `xingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '捐赠姓名',
  `mujuanzhezhanghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '募捐者账号',
  `mujuanzhexingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '募捐者姓名',
  `ispay` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`),
  UNIQUE KEY `juanzengbianhao` (`juanzengbianhao`),
  KEY `xiangmuleixing` (`xiangmuleixing`),
  CONSTRAINT `juanzengjilu_ibfk_1` FOREIGN KEY (`xiangmuleixing`) REFERENCES `xiangmuleixing` (`xiangmuleixing`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='捐赠记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juanzengjilu`
--

LOCK TABLES `juanzengjilu` WRITE;
/*!40000 ALTER TABLE `juanzengjilu` DISABLE KEYS */;
INSERT INTO `juanzengjilu` VALUES (1,'2025-05-18 08:40:11','1111111111','项目名称1','项目类型1','地区1','upload/juanzengjilu_tupian1.jpg,upload/juanzengjilu_tupian2.jpg,upload/juanzengjilu_tupian3.jpg',1,'捐赠详情1','2025-05-18 16:40:11','捐赠账号1','捐赠姓名1','募捐者账号1','募捐者姓名1','未支付'),(2,'2025-05-18 08:40:11','2222222222','项目名称2','项目类型2','地区2','upload/juanzengjilu_tupian2.jpg,upload/juanzengjilu_tupian3.jpg,upload/juanzengjilu_tupian4.jpg',2,'捐赠详情2','2025-05-18 16:40:11','捐赠账号2','捐赠姓名2','募捐者账号2','募捐者姓名2','未支付'),(3,'2025-05-18 08:40:11','3333333333','项目名称3','项目类型3','地区3','upload/juanzengjilu_tupian3.jpg,upload/juanzengjilu_tupian4.jpg,upload/juanzengjilu_tupian5.jpg',3,'捐赠详情3','2025-05-18 16:40:11','捐赠账号3','捐赠姓名3','募捐者账号3','募捐者姓名3','未支付'),(4,'2025-05-18 08:40:11','4444444444','项目名称4','项目类型4','地区4','upload/juanzengjilu_tupian4.jpg,upload/juanzengjilu_tupian5.jpg,upload/juanzengjilu_tupian6.jpg',4,'捐赠详情4','2025-05-18 16:40:11','捐赠账号4','捐赠姓名4','募捐者账号4','募捐者姓名4','未支付'),(5,'2025-05-18 08:40:11','5555555555','项目名称5','项目类型5','地区5','upload/juanzengjilu_tupian5.jpg,upload/juanzengjilu_tupian6.jpg,upload/juanzengjilu_tupian7.jpg',5,'捐赠详情5','2025-05-18 16:40:11','捐赠账号5','捐赠姓名5','募捐者账号5','募捐者姓名5','未支付'),(6,'2025-05-18 08:40:11','6666666666','项目名称6','项目类型6','地区6','upload/juanzengjilu_tupian6.jpg,upload/juanzengjilu_tupian7.jpg,upload/juanzengjilu_tupian8.jpg',6,'捐赠详情6','2025-05-18 16:40:11','捐赠账号6','捐赠姓名6','募捐者账号6','募捐者姓名6','未支付'),(7,'2025-05-18 08:40:11','7777777777','项目名称7','项目类型7','地区7','upload/juanzengjilu_tupian7.jpg,upload/juanzengjilu_tupian8.jpg,upload/juanzengjilu_tupian1.jpg',7,'捐赠详情7','2025-05-18 16:40:11','捐赠账号7','捐赠姓名7','募捐者账号7','募捐者姓名7','未支付'),(8,'2025-05-18 08:40:11','8888888888','项目名称8','项目类型8','地区8','upload/juanzengjilu_tupian8.jpg,upload/juanzengjilu_tupian1.jpg,upload/juanzengjilu_tupian2.jpg',8,'捐赠详情8','2025-05-18 16:40:11','捐赠账号8','捐赠姓名8','募捐者账号8','募捐者姓名8','未支付');
/*!40000 ALTER TABLE `juanzengjilu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juanzengzhe`
--

DROP TABLE IF EXISTS `juanzengzhe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `juanzengzhe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `juanzengzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '捐赠账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `xingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '捐赠姓名',
  `juanzengdianhua` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '捐赠电话',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `shenfenzheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '身份证',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '头像',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `juanzengzhanghao` (`juanzengzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='捐赠者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juanzengzhe`
--

LOCK TABLES `juanzengzhe` WRITE;
/*!40000 ALTER TABLE `juanzengzhe` DISABLE KEYS */;
INSERT INTO `juanzengzhe` VALUES (21,'2025-05-18 08:40:10','捐赠账号1','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','捐赠姓名1','13823888881','男','440300199101010001','upload/juanzengzhe_touxiang1.jpg',0),(22,'2025-05-18 08:40:10','捐赠账号2','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','捐赠姓名2','13823888882','男','440300199202020002','upload/juanzengzhe_touxiang2.jpg',0),(23,'2025-05-18 08:40:10','捐赠账号3','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','捐赠姓名3','13823888883','男','440300199303030003','upload/juanzengzhe_touxiang3.jpg',0),(24,'2025-05-18 08:40:10','捐赠账号4','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','捐赠姓名4','13823888884','男','440300199404040004','upload/juanzengzhe_touxiang4.jpg',0),(25,'2025-05-18 08:40:10','捐赠账号5','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','捐赠姓名5','13823888885','男','440300199505050005','upload/juanzengzhe_touxiang5.jpg',0),(26,'2025-05-18 08:40:11','捐赠账号6','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','捐赠姓名6','13823888886','男','440300199606060006','upload/juanzengzhe_touxiang6.jpg',0),(27,'2025-05-18 08:40:11','捐赠账号7','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','捐赠姓名7','13823888887','男','440300199707070007','upload/juanzengzhe_touxiang7.jpg',0),(28,'2025-05-18 08:40:11','捐赠账号8','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','捐赠姓名8','13823888888','男','440300199808080008','upload/juanzengzhe_touxiang8.jpg',0);
/*!40000 ALTER TABLE `juanzengzhe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mujuanxiangmu`
--

DROP TABLE IF EXISTS `mujuanxiangmu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mujuanxiangmu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目类型',
  `diqu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `mubiaojine` double NOT NULL COMMENT '目标金额',
  `yijuanjine` double NOT NULL COMMENT '已捐金额',
  `zhengmingcailiao` longtext COLLATE utf8mb4_unicode_ci COMMENT '证明材料',
  `shenqingshijian` datetime DEFAULT NULL COMMENT '申请时间',
  `xiangmuxiangqing` longtext COLLATE utf8mb4_unicode_ci COMMENT '项目详情',
  `xiangmuyongtu` longtext COLLATE utf8mb4_unicode_ci COMMENT '项目用途',
  `mujuanzhezhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '募捐者账号',
  `mujuanzhexingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '募捐者姓名',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `reversetime` datetime DEFAULT NULL COMMENT '倒计结束时间',
  `clicknum` int(11) DEFAULT '0' COMMENT '点击次数',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`),
  KEY `xiangmuleixing` (`xiangmuleixing`),
  CONSTRAINT `mujuanxiangmu_ibfk_1` FOREIGN KEY (`xiangmuleixing`) REFERENCES `xiangmuleixing` (`xiangmuleixing`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='募捐项目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mujuanxiangmu`
--

LOCK TABLES `mujuanxiangmu` WRITE;
/*!40000 ALTER TABLE `mujuanxiangmu` DISABLE KEYS */;
INSERT INTO `mujuanxiangmu` VALUES (1,'2025-05-18 08:40:11','项目名称1','项目类型1','地区1','upload/mujuanxiangmu_tupian1.jpg,upload/mujuanxiangmu_tupian2.jpg,upload/mujuanxiangmu_tupian3.jpg',1,1,'','2025-05-18 16:40:11','项目详情1','项目用途1','募捐者账号1','募捐者姓名1','是','',1,1,'2025-05-19 00:00:00',1,0,1),(2,'2025-05-18 08:40:11','项目名称2','项目类型2','地区2','upload/mujuanxiangmu_tupian2.jpg,upload/mujuanxiangmu_tupian3.jpg,upload/mujuanxiangmu_tupian4.jpg',2,2,'','2025-05-18 16:40:11','项目详情2','项目用途2','募捐者账号2','募捐者姓名2','是','',2,2,'2025-05-19 00:00:00',2,0,2),(3,'2025-05-18 08:40:11','项目名称3','项目类型3','地区3','upload/mujuanxiangmu_tupian3.jpg,upload/mujuanxiangmu_tupian4.jpg,upload/mujuanxiangmu_tupian5.jpg',3,3,'','2025-05-18 16:40:11','项目详情3','项目用途3','募捐者账号3','募捐者姓名3','是','',3,3,'2025-05-19 00:00:00',3,0,3),(4,'2025-05-18 08:40:11','项目名称4','项目类型4','地区4','upload/mujuanxiangmu_tupian4.jpg,upload/mujuanxiangmu_tupian5.jpg,upload/mujuanxiangmu_tupian6.jpg',4,4,'','2025-05-18 16:40:11','项目详情4','项目用途4','募捐者账号4','募捐者姓名4','是','',4,4,'2025-05-19 00:00:00',4,0,4),(5,'2025-05-18 08:40:11','项目名称5','项目类型5','地区5','upload/mujuanxiangmu_tupian5.jpg,upload/mujuanxiangmu_tupian6.jpg,upload/mujuanxiangmu_tupian7.jpg',5,5,'','2025-05-18 16:40:11','项目详情5','项目用途5','募捐者账号5','募捐者姓名5','是','',5,5,'2025-05-19 00:00:00',5,0,5),(6,'2025-05-18 08:40:11','项目名称6','项目类型6','地区6','upload/mujuanxiangmu_tupian6.jpg,upload/mujuanxiangmu_tupian7.jpg,upload/mujuanxiangmu_tupian8.jpg',6,6,'','2025-05-18 16:40:11','项目详情6','项目用途6','募捐者账号6','募捐者姓名6','是','',6,6,'2025-05-19 00:00:00',6,0,6),(7,'2025-05-18 08:40:11','项目名称7','项目类型7','地区7','upload/mujuanxiangmu_tupian7.jpg,upload/mujuanxiangmu_tupian8.jpg,upload/mujuanxiangmu_tupian1.jpg',7,7,'','2025-05-18 16:40:11','项目详情7','项目用途7','募捐者账号7','募捐者姓名7','是','',7,7,'2025-05-19 00:00:00',7,0,7),(8,'2025-05-18 08:40:11','项目名称8','项目类型8','地区8','upload/mujuanxiangmu_tupian8.jpg,upload/mujuanxiangmu_tupian1.jpg,upload/mujuanxiangmu_tupian2.jpg',8,8,'','2025-05-18 16:40:11','项目详情8','项目用途8','募捐者账号8','募捐者姓名8','是','',8,8,'2025-05-19 00:00:00',8,0,8);
/*!40000 ALTER TABLE `mujuanxiangmu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mujuanzhe`
--

DROP TABLE IF EXISTS `mujuanzhe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mujuanzhe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mujuanzhezhanghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '募捐者账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `mujuanzhexingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '募捐者姓名',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `shouji` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机',
  `shenfenzheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '身份证',
  `lianxidizhi` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系地址',
  `youxiang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '头像',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mujuanzhezhanghao` (`mujuanzhezhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='募捐者';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mujuanzhe`
--

LOCK TABLES `mujuanzhe` WRITE;
/*!40000 ALTER TABLE `mujuanzhe` DISABLE KEYS */;
INSERT INTO `mujuanzhe` VALUES (11,'2025-05-18 08:40:10','募捐者账号1','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','募捐者姓名1','男','13823888881','440300199101010001','联系地址1','773890001@qq.com','upload/mujuanzhe_touxiang1.jpg','是','',0),(12,'2025-05-18 08:40:10','募捐者账号2','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','募捐者姓名2','男','13823888882','440300199202020002','联系地址2','773890002@qq.com','upload/mujuanzhe_touxiang2.jpg','是','',0),(13,'2025-05-18 08:40:10','募捐者账号3','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','募捐者姓名3','男','13823888883','440300199303030003','联系地址3','773890003@qq.com','upload/mujuanzhe_touxiang3.jpg','是','',0),(14,'2025-05-18 08:40:10','募捐者账号4','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','募捐者姓名4','男','13823888884','440300199404040004','联系地址4','773890004@qq.com','upload/mujuanzhe_touxiang4.jpg','是','',0),(15,'2025-05-18 08:40:10','募捐者账号5','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','募捐者姓名5','男','13823888885','440300199505050005','联系地址5','773890005@qq.com','upload/mujuanzhe_touxiang5.jpg','是','',0),(16,'2025-05-18 08:40:10','募捐者账号6','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','募捐者姓名6','男','13823888886','440300199606060006','联系地址6','773890006@qq.com','upload/mujuanzhe_touxiang6.jpg','是','',0),(17,'2025-05-18 08:40:10','募捐者账号7','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','募捐者姓名7','男','13823888887','440300199707070007','联系地址7','773890007@qq.com','upload/mujuanzhe_touxiang7.jpg','是','',0),(18,'2025-05-18 08:40:10','募捐者账号8','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','募捐者姓名8','男','13823888888','440300199808080008','联系地址8','773890008@qq.com','upload/mujuanzhe_touxiang8.jpg','是','',0);
/*!40000 ALTER TABLE `mujuanzhe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) DEFAULT NULL COMMENT '商品id',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '类型',
  `inteltype` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `syslog`
--

DROP TABLE IF EXISTS `syslog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `syslog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `operation` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户操作',
  `method` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求方法',
  `params` longtext COLLATE utf8mb4_unicode_ci COMMENT '请求参数',
  `time` bigint(20) DEFAULT NULL COMMENT '请求时长(毫秒)',
  `ip` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `syslog`
--

LOCK TABLES `syslog` WRITE;
/*!40000 ALTER TABLE `syslog` DISABLE KEYS */;
/*!40000 ALTER TABLE `syslog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `image` varchar(200) DEFAULT NULL COMMENT '头像',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','upload/image1.jpg','管理员','2025-05-18 08:40:11');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xiangmujinzhan`
--

DROP TABLE IF EXISTS `xiangmujinzhan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xiangmujinzhan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmumingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目名称',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目类型',
  `diqu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地区',
  `tupian` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `xiangmujinzhan` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '项目进展',
  `dengjishijian` datetime DEFAULT NULL COMMENT '登记时间',
  `jinzhanxiangqing` longtext COLLATE utf8mb4_unicode_ci COMMENT '进展详情',
  `mujuanzhezhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '募捐者账号',
  `mujuanzhexingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '募捐者姓名',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`),
  KEY `xiangmuleixing` (`xiangmuleixing`),
  CONSTRAINT `xiangmujinzhan_ibfk_1` FOREIGN KEY (`xiangmuleixing`) REFERENCES `xiangmuleixing` (`xiangmuleixing`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目进展';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xiangmujinzhan`
--

LOCK TABLES `xiangmujinzhan` WRITE;
/*!40000 ALTER TABLE `xiangmujinzhan` DISABLE KEYS */;
INSERT INTO `xiangmujinzhan` VALUES (1,'2025-05-18 08:40:11','项目名称1','项目类型1','地区1','upload/xiangmujinzhan_tupian1.jpg,upload/xiangmujinzhan_tupian2.jpg,upload/xiangmujinzhan_tupian3.jpg','已完成','2025-05-18 16:40:11','进展详情1','募捐者账号1','募捐者姓名1',1,1,0,1),(2,'2025-05-18 08:40:11','项目名称2','项目类型2','地区2','upload/xiangmujinzhan_tupian2.jpg,upload/xiangmujinzhan_tupian3.jpg,upload/xiangmujinzhan_tupian4.jpg','已完成','2025-05-18 16:40:11','进展详情2','募捐者账号2','募捐者姓名2',2,2,0,2),(3,'2025-05-18 08:40:11','项目名称3','项目类型3','地区3','upload/xiangmujinzhan_tupian3.jpg,upload/xiangmujinzhan_tupian4.jpg,upload/xiangmujinzhan_tupian5.jpg','已完成','2025-05-18 16:40:11','进展详情3','募捐者账号3','募捐者姓名3',3,3,0,3),(4,'2025-05-18 08:40:11','项目名称4','项目类型4','地区4','upload/xiangmujinzhan_tupian4.jpg,upload/xiangmujinzhan_tupian5.jpg,upload/xiangmujinzhan_tupian6.jpg','已完成','2025-05-18 16:40:11','进展详情4','募捐者账号4','募捐者姓名4',4,4,0,4),(5,'2025-05-18 08:40:11','项目名称5','项目类型5','地区5','upload/xiangmujinzhan_tupian5.jpg,upload/xiangmujinzhan_tupian6.jpg,upload/xiangmujinzhan_tupian7.jpg','已完成','2025-05-18 16:40:11','进展详情5','募捐者账号5','募捐者姓名5',5,5,0,5),(6,'2025-05-18 08:40:11','项目名称6','项目类型6','地区6','upload/xiangmujinzhan_tupian6.jpg,upload/xiangmujinzhan_tupian7.jpg,upload/xiangmujinzhan_tupian8.jpg','已完成','2025-05-18 16:40:11','进展详情6','募捐者账号6','募捐者姓名6',6,6,0,6),(7,'2025-05-18 08:40:11','项目名称7','项目类型7','地区7','upload/xiangmujinzhan_tupian7.jpg,upload/xiangmujinzhan_tupian8.jpg,upload/xiangmujinzhan_tupian1.jpg','已完成','2025-05-18 16:40:11','进展详情7','募捐者账号7','募捐者姓名7',7,7,0,7),(8,'2025-05-18 08:40:11','项目名称8','项目类型8','地区8','upload/xiangmujinzhan_tupian8.jpg,upload/xiangmujinzhan_tupian1.jpg,upload/xiangmujinzhan_tupian2.jpg','已完成','2025-05-18 16:40:11','进展详情8','募捐者账号8','募捐者姓名8',8,8,0,8);
/*!40000 ALTER TABLE `xiangmujinzhan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xiangmuleixing`
--

DROP TABLE IF EXISTS `xiangmuleixing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `xiangmuleixing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xiangmuleixing` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '项目类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `xiangmuleixing` (`xiangmuleixing`),
  KEY `xiangmuleixing_54hu` (`xiangmuleixing`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xiangmuleixing`
--

LOCK TABLES `xiangmuleixing` WRITE;
/*!40000 ALTER TABLE `xiangmuleixing` DISABLE KEYS */;
INSERT INTO `xiangmuleixing` VALUES (1,'2025-05-18 08:40:11','项目类型1'),(2,'2025-05-18 08:40:11','项目类型2'),(3,'2025-05-18 08:40:11','项目类型3'),(4,'2025-05-18 08:40:11','项目类型4'),(5,'2025-05-18 08:40:11','项目类型5'),(6,'2025-05-18 08:40:11','项目类型6'),(7,'2025-05-18 08:40:11','项目类型7'),(8,'2025-05-18 08:40:11','项目类型8');
/*!40000 ALTER TABLE `xiangmuleixing` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-19 15:49:21

-- ============================================================
-- Merkle trust extension
-- ============================================================
ALTER TABLE `juanzengjilu`
  ADD COLUMN `mujuanxiangmu_id` bigint(20) DEFAULT NULL COMMENT '募捐项目ID' AFTER `id`,
  ADD COLUMN `pay_time` datetime DEFAULT NULL COMMENT '支付时间' AFTER `ispay`,
  ADD COLUMN `merkle_status` varchar(32) DEFAULT 'UNPAID' COMMENT 'Merkle状态' AFTER `pay_time`,
  ADD COLUMN `merkle_batch_no` varchar(64) DEFAULT NULL COMMENT 'Merkle批次号' AFTER `merkle_status`,
  ADD COLUMN `leaf_hash` varchar(128) DEFAULT NULL COMMENT '叶子哈希' AFTER `merkle_batch_no`;

CREATE INDEX `idx_juanzengjilu_campaign_id` ON `juanzengjilu` (`mujuanxiangmu_id`);
CREATE INDEX `idx_juanzengjilu_merkle_status` ON `juanzengjilu` (`merkle_status`);
CREATE INDEX `idx_juanzengjilu_merkle_batch_no` ON `juanzengjilu` (`merkle_batch_no`);

CREATE TABLE `merkle_batch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_no` varchar(64) NOT NULL,
  `campaign_id` bigint(20) DEFAULT NULL,
  `root_hash` varchar(128) NOT NULL,
  `leaf_count` int(11) NOT NULL,
  `status` varchar(32) DEFAULT 'PUBLISHED',
  `published_at` datetime DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_merkle_batch_no` (`batch_no`),
  KEY `idx_merkle_batch_campaign` (`campaign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Merkle批次';

CREATE TABLE `merkle_proof` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `juanzengjilu_id` bigint(20) NOT NULL,
  `batch_no` varchar(64) NOT NULL,
  `leaf_index` int(11) NOT NULL,
  `proof_json` longtext,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_merkle_proof_donation` (`juanzengjilu_id`),
  KEY `idx_merkle_proof_batch` (`batch_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Merkle证明';
