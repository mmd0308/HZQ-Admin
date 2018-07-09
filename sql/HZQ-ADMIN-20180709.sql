-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: hzq-admin
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
-- Table structure for table `rl_role_resource`
--

DROP TABLE IF EXISTS `rl_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rl_role_resource` (
  `id` varchar(32) NOT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `resource_id` varchar(32) DEFAULT NULL,
  `resource_type` varchar(32) DEFAULT NULL COMMENT 'menu,button',
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rl_role_resource`
--

LOCK TABLES `rl_role_resource` WRITE;
/*!40000 ALTER TABLE `rl_role_resource` DISABLE KEYS */;
INSERT INTO `rl_role_resource` VALUES ('7e072d616e1711e888aa0242ac110002','779f72791570410191a23b75c7f69002','8fc9877a53fc4db7b317145c9e82edfe','menu',NULL),('7e07316b6e1711e888aa0242ac110002','779f72791570410191a23b75c7f69002','7a0055b5599911e8b5590242ac120002','menu',NULL),('7e0731f76e1711e888aa0242ac110002','779f72791570410191a23b75c7f69002','88837b47599911e8b5590242ac120002','menu',NULL),('7e08328e6e1711e888aa0242ac110002','779f72791570410191a23b75c7f69002','7a0055b5599911e8b5590242ac120045','button',NULL),('7e08345c6e1711e888aa0242ac110002','779f72791570410191a23b75c7f69002','7d21ed98db0f417089411688fcf2c17f','button',NULL);
/*!40000 ALTER TABLE `rl_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rl_user_role`
--

DROP TABLE IF EXISTS `rl_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rl_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT '',
  `role_id` varchar(32) DEFAULT '',
  `note` varchar(128) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rl_user_role`
--

LOCK TABLES `rl_user_role` WRITE;
/*!40000 ALTER TABLE `rl_user_role` DISABLE KEYS */;
INSERT INTO `rl_user_role` VALUES ('1e5f59996e0f11e888aa0242ac110002','3e622bf26ebd465498a1e60e2280fc11','779f72791570410191a23b75c7f69002',NULL);
/*!40000 ALTER TABLE `rl_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_button`
--

DROP TABLE IF EXISTS `sys_button`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_button` (
  `id` varchar(32) NOT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '按钮编码',
  `name` varchar(255) DEFAULT NULL COMMENT '按钮名称',
  `menu_id` varchar(255) NOT NULL COMMENT '关联菜单',
  `enabled` char(1) DEFAULT '0',
  `path` varchar(255) DEFAULT NULL COMMENT '请求路径',
  `attr1` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr2` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr3` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr4` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr5` varchar(256) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_button`
--

LOCK TABLES `sys_button` WRITE;
/*!40000 ALTER TABLE `sys_button` DISABLE KEYS */;
INSERT INTO `sys_button` VALUES ('7a0055b5599911e8b5590242ac120045','USER_ADD','新增','7a0055b5599911e8b5590242ac120002','1','/api/user/add',NULL,NULL,NULL,NULL,NULL),('7d21ed98db0f417089411688fcf2c17f','USER_UPDATE','修改','7a0055b5599911e8b5590242ac120002','1','/api/user/update',NULL,NULL,NULL,NULL,NULL),('e49786294d7c43b18eb1ac7f08fcbceb','ROLE_ADD','新增','88837b47599911e8b5590242ac120002','1','/api/role/add',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_button` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_dict`
--

DROP TABLE IF EXISTS `sys_dict`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_dict` (
  `id` varchar(32) NOT NULL,
  `dict_name` varchar(64) DEFAULT NULL COMMENT ' ',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级id',
  `dict_type` varchar(16) DEFAULT NULL COMMENT '字典类型，系统字典 SYS(系统字典不可以删除） 业务字典 BUS',
  `code` varchar(64) DEFAULT NULL COMMENT '字典编码 唯一',
  `enabled` char(1) DEFAULT '0' COMMENT '1启用。 0禁用',
  `note` varchar(512) DEFAULT NULL COMMENT '描述',
  `attr1` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr2` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr3` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr4` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr5` varchar(256) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_dict`
--

LOCK TABLES `sys_dict` WRITE;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
INSERT INTO `sys_dict` VALUES ('1','数据字典','-1','SYS','DICT_CODE','1',NULL,NULL,NULL,NULL,NULL,NULL),('1cef4d2229ac473d985a26356efc63e9','男','a8943382e084410bb7e28db8ed9dd4db','SYS','SEX_MAN','1',NULL,NULL,NULL,NULL,NULL,NULL),('7081dd8c45bc4710978a4592eb512d69','链接','eb4d9678967a412f80187e98091b8601','SYS','MENU_TYPE_LINK','1',NULL,NULL,NULL,NULL,NULL,NULL),('753a5fe3901444f3a6f12936e5a94396','女','a8943382e084410bb7e28db8ed9dd4db','SYS','SEX_WOMEN','1',NULL,NULL,NULL,NULL,NULL,NULL),('a8943382e084410bb7e28db8ed9dd4db','性别','1','SYS','DICT_SEX','1',NULL,NULL,NULL,NULL,NULL,NULL),('eb4d9678967a412f80187e98091b8601','菜单类型','1','SYS','DICT_MENU_TYPE','1',NULL,NULL,NULL,NULL,NULL,NULL),('f6b7b636d5b3469c9614fb4265cb454b','菜单','eb4d9678967a412f80187e98091b8601','SYS','MENU_TYPE_MENU','1',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_log` (
  `id` varchar(32) NOT NULL,
  `opt_path` varchar(128) DEFAULT NULL COMMENT '请求路径',
  `opt_type` varchar(32) DEFAULT NULL COMMENT '请求类型，新增，删除，查看',
  `opt_time` date DEFAULT NULL COMMENT '操作时间',
  `note` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES ('1','1','1',NULL,'1'),('11','11','11',NULL,NULL),('2','2','2',NULL,'2'),('22','22','22',NULL,NULL),('3','3','3',NULL,'3'),('33','33',NULL,NULL,NULL),('4','4','4',NULL,'4'),('5','5','5',NULL,NULL),('6','6','6',NULL,NULL),('7','7','7',NULL,NULL),('8','8','8',NULL,NULL),('9','9','9',NULL,NULL);
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_menu` (
  `id` varchar(32) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL COMMENT '标题',
  `code` varchar(64) DEFAULT NULL,
  `parent_id` varchar(32) NOT NULL COMMENT '父级节点',
  `href` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `component` varchar(255) DEFAULT NULL COMMENT '前端组件',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `menu_type` varchar(32) COMMENT '菜单类型 数据字典',
  `note` varchar(255) DEFAULT NULL COMMENT '描述',
  `enabled` char(1) DEFAULT '0' COMMENT '0 禁用 1启用',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `attr1` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr2` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr3` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr4` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr5` varchar(256) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_menu`
--

LOCK TABLES `sys_menu` WRITE;
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;
INSERT INTO `sys_menu` VALUES ('3b569dee599911e8b5590242ac120002','系统管理','MENU_SYSTEM','7d2cf379c68411e7a1880242ac110045','','','system','f6b7b636d5b3469c9614fb4265cb454b','','1',3,NULL,NULL,NULL,NULL,NULL),('7a0055b5599911e8b5590242ac120002','用户管理','MENU_SYSTEM_USER','3b569dee599911e8b5590242ac120002','','','user','f6b7b636d5b3469c9614fb4265cb454b','','1',10,NULL,NULL,NULL,NULL,NULL),('7d2cf379c68411e7a1880242ac110045','菜单管理','MENU_MANAGER','-1','/','/','manager','7081dd8c45bc4710978a4592eb512d69','菜单管理','1',0,NULL,NULL,NULL,NULL,NULL),('88837b47599911e8b5590242ac120002','角色管理','MENU_SYSTEM_ROLE','3b569dee599911e8b5590242ac120002','','','role','f6b7b636d5b3469c9614fb4265cb454b','','1',20,NULL,NULL,NULL,NULL,NULL),('8fc9877a53fc4db7b317145c9e82edfe','图标管理','MENU_ICON','7d2cf379c68411e7a1880242ac110045','','','example','f6b7b636d5b3469c9614fb4265cb454b','','1',2,NULL,NULL,NULL,NULL,NULL),('bdee80c8599911e8b5590242ac120002','资源管理','MENU_SYSTEM_MENU','3b569dee599911e8b5590242ac120002','','','class','f6b7b636d5b3469c9614fb4265cb454b','','1',30,NULL,NULL,NULL,NULL,NULL),('dfb01b0c01894dee9021a8bd6b014ccd','Welcome','MENU_WECLOME','7d2cf379c68411e7a1880242ac110045','','','welcome','f6b7b636d5b3469c9614fb4265cb454b','','1',1,NULL,NULL,NULL,NULL,NULL),('e09c15f6edd07d8a4f6d247a2c1da0a5','数据字典管理','MENU_SYSTEM_DICT','3b569dee599911e8b5590242ac120002','','','dict','f6b7b636d5b3469c9614fb4265cb454b','','1',40,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` varchar(36) NOT NULL,
  `code` varchar(128) DEFAULT NULL,
  `role_name` varchar(128) DEFAULT NULL,
  `enabled` char(1) DEFAULT '0' COMMENT '0禁用 1启用',
  `note` varchar(256) DEFAULT NULL,
  `attr1` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr2` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr3` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr4` varchar(256) DEFAULT NULL COMMENT '保留字段',
  `attr5` varchar(256) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('294c40b083334290bc2d9c7996f0bf1d','ROLE_ONE','一级角色','1','',NULL,NULL,NULL,NULL,NULL),('779f72791570410191a23b75c7f69002','ROLE_COMMON','普通用户角色','1','',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) DEFAULT '' COMMENT '登陆名',
  `password` varchar(128) DEFAULT '',
  `note` varchar(512) DEFAULT '',
  `email` varchar(32) DEFAULT '',
  `avatar` varchar(256) DEFAULT NULL COMMENT '用户头像',
  `full_name` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `attr1` varchar(256) DEFAULT NULL,
  `attr2` varchar(256) DEFAULT NULL,
  `attr3` varchar(256) DEFAULT NULL,
  `attr4` varchar(256) DEFAULT NULL,
  `attr5` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('3e622bf26ebd465498a1e60e2280fc11','guest','21232f297a57a5a743894a0e4a801fc3',NULL,'guest@163.com',NULL,'游客','134567891236',NULL,NULL,NULL,NULL,NULL),('8c343344d3dd11e79cde0242ac110003','admin','21232f297a57a5a743894a0e4a801fc3','系统管理员','admin@163.com',NULL,'系统管理员',NULL,NULL,NULL,NULL,NULL,NULL),('b137851cd3dd11e79cde0242ac110003','hzqing','21232f297a57a5a743894a0e4a801fc3','衡钊清','hengzhaoqing@163.com','http://localhost:8899/images/201805/eef0a9ec21874a27a3360da982dfb967.png','衡钊清','17629210308',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-09 10:08:20
