CREATE DATABASE  IF NOT EXISTS `hzq_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hzq_admin`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: hzq_admin
-- ------------------------------------------------------
-- Server version	5.7.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_button`
--

DROP TABLE IF EXISTS `sys_button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_button` (
  `but_id` varchar(32) NOT NULL,
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单id',
  `but_name` varchar(45) DEFAULT NULL COMMENT '按钮名称',
  `permission` varchar(45) DEFAULT NULL COMMENT '权限标示,唯一',
  `enabled` char(1) DEFAULT 'Y' COMMENT '是否启用,‘N’ 表示禁用 ’Y’表示启用 默认是: ‘Y’',
  `create_id` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_id` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`but_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='按钮表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_button`
--

LOCK TABLES `sys_button` WRITE;
/*!40000 ALTER TABLE `sys_button` DISABLE KEYS */;
INSERT INTO `sys_button` VALUES ('80019b108f7b4dbfa6143aaa8c6dec4a','ff71a7693ba64dc3a1f1041a2343413f','d','d','N','','2018-10-27 14:30:36','','2018-10-27 14:30:36'),('b80c764f728e4d50aef55b829633b606','3367c5161bee4169a67c494e7349d2ff','c','c','N',NULL,'2018-10-31 20:33:03',NULL,'2018-10-31 20:33:03'),('c717a855e90c46b8a15286bdb73a442a','ff71a7693ba64dc3a1f1041a2343413f','d','d','N','','2018-10-27 14:30:36','','2018-10-27 14:30:36'),('ebb81e747a4c433bb4f363bed6ad0390','3367c5161bee4169a67c494e7349d2ff','e','ed','N',NULL,'2018-10-31 20:16:29',NULL,'2018-10-31 20:16:29');
/*!40000 ALTER TABLE `sys_button` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_department`
--

DROP TABLE IF EXISTS `sys_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_department` (
  `depart_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `depart_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '部门名称',
  `depart_sort` int(11) DEFAULT NULL COMMENT '排序',
  `leader` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '部门负责人',
  `phone` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '部门电话',
  `status` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `del_flag` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '删除标记, 默认’N’ ‘Y’ 表示已经删除 ’N’没有删除',
  ` create_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `parent_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`depart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='部门管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_department`
--

LOCK TABLES `sys_department` WRITE;
/*!40000 ALTER TABLE `sys_department` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict` (
  `dict_id` varchar(32) NOT NULL,
  `dict_name` varchar(45) DEFAULT NULL COMMENT '字典名称,字典标签',
  `dict_code` varchar(45) DEFAULT NULL COMMENT '字典数据值  唯一校验',
  `dict_sort` int(11) DEFAULT NULL COMMENT '排序',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `parent_id` varchar(32) DEFAULT '0' COMMENT '父级编码,默认是0 ',
  `del_flag` char(1) DEFAULT 'N' COMMENT '是否被删除 N 表示没有。Y表示被删除 默认是N',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建着id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新着',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `log_id` varchar(32) NOT NULL,
  `login_name` varchar(45) DEFAULT NULL COMMENT '登陆名',
  `ipaddr` varchar(45) DEFAULT NULL COMMENT '访问ip地址',
  `browser` varchar(100) DEFAULT NULL COMMENT '浏览器',
  `os` varchar(45) DEFAULT NULL COMMENT '操作系统',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(32) NOT NULL COMMENT '菜单id',
  `menu_name` varchar(45) DEFAULT NULL COMMENT '名称',
  `parent_id` varchar(45) DEFAULT '0' COMMENT '上级id 父级编码,默认是0 ',
  `menu_sort` int(11) DEFAULT NULL COMMENT '排序',
  `menu_type` char(1) DEFAULT NULL COMMENT '类型 M 菜单 C目录',
  `enabled` char(1) DEFAULT 'Y' COMMENT '是否启用,‘N’ 表示禁用 ’Y’表示启用 默认是: ‘Y’',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `permission` varchar(45) DEFAULT NULL COMMENT '权限标示 唯一',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者id',
  `create_time` datetime DEFAULT NULL COMMENT ' 创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者id',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('3367c5161bee4169a67c494e7349d2ff','系统管理','0',NULL,'','N','','','','','2018-10-27 14:19:57','','2018-10-27 14:19:57'),('39406006ac2f4d95b98c53f0eedcc10d','fds ','ff71a7693ba64dc3a1f1041a2343413f',545,'C','N','afsaf','afsa','fas','','2018-10-31 10:58:34','','2018-10-31 10:58:34'),('958ffcb7ff194be2873bd4ccb475a6cf','fsafdsafaa','39406006ac2f4d95b98c53f0eedcc10d',NULL,'','N','','','','','2018-10-31 10:59:52','','2018-10-31 10:59:52'),('a','系统工具','0',12,NULL,'Y',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('b','代码生成','a',56,NULL,'Y',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('fa94ff7aa1fe4b4988d7ea9535f0cd74','用户管理','3367c5161bee4169a67c494e7349d2ff',NULL,'','N','','','system:user','','2018-10-31 11:04:31','','2018-10-31 11:04:31'),('fec20a40316c4eb79c172196285eb1d1','aaa','958ffcb7ff194be2873bd4ccb475a6cf',NULL,'','N','','','','','2018-10-31 11:03:32','','2018-10-31 11:03:32'),('ff71a7693ba64dc3a1f1041a2343413f','用户管理','0',NULL,'','N','','','system:user','','2018-10-27 14:21:14','','2018-10-27 14:21:14');
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `role_id` varchar(32) NOT NULL,
  `role_name` varchar(45) DEFAULT NULL COMMENT '角色名称',
  `role_sort` int(11) DEFAULT NULL COMMENT '排序',
  `enabled` char(1) DEFAULT 'Y' COMMENT '是否启用,‘N’ 表示禁用 ’Y’表示启用 默认是: ‘Y’',
  `del_flag` char(1) DEFAULT 'N' COMMENT '是否被删除 N 表示没有。Y表示被删除 默认是N',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建着id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人id\n',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `permission` varchar(100) DEFAULT NULL COMMENT '权限编码 。唯一校验',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('77a70c972747404b8bf87f8adc8ecd51','一级角色',0,'Y','N','',NULL,'2018-10-27 23:35:43',NULL,'2018-10-27 23:35:43',NULL),('d0f38454a7c24292bb2c8e15e814a796','二级角色',0,'Y','N','二级别角色',NULL,'2018-10-27 23:44:56',NULL,'2018-10-27 23:44:56',NULL),('ec843fe60e1e4492a51a1727e14143f7','测试角色',0,'Y','N','北京开始',NULL,'2018-10-30 14:14:53',NULL,'2018-10-30 14:14:53',NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_menu`
--

DROP TABLE IF EXISTS `sys_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role_menu` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL COMMENT ' 角色id',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色和菜单关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_menu`
--

LOCK TABLES `sys_role_menu` WRITE;
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `login_name` varchar(45) DEFAULT NULL COMMENT '登陆名',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户名称',
  `avatar` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL COMMENT '登陆密码		',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `del_flag` char(1) DEFAULT 'N' COMMENT '删除表示 ‘N’没有被删除 ‘Y’被删除  默认是(N)',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新人ID',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `depart_id` varchar(32) DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户管理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('1f4bed9399b549b6bd41162b32cd75a7','hzqing','衡钊清','','$2a$10$9JNleIip4cK7SNXIlFnKfONyFw5JFh2lyD6dSNYdNnanQH.5vVP3e','13711111111','N',NULL,NULL,NULL,NULL,NULL),('admin','admin','系统超级管理元',NULL,'admin','110','N',NULL,NULL,NULL,NULL,NULL),('af5f5baaab0440e3ad04dd8f14ab0686','test','测试用户','','$2a$10$fcX2cbbF9NMmVtEGCevrP.8ga9CKvQChFWdp43XDY4lmbUvhHG.J6','112015464','N',NULL,'2018-10-27 23:35:12',NULL,'2018-10-27 23:35:12',NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES ('dc269c753d7c43c48e1d244b6bc9121a','1f4bed9399b549b6bd41162b32cd75a7','d0f38454a7c24292bb2c8e15e814a796'),('f268ec42278348c79cfd02de6931ef73','1f4bed9399b549b6bd41162b32cd75a7','77a70c972747404b8bf87f8adc8ecd51');
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-01 15:22:08
