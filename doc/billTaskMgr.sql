CREATE DATABASE  IF NOT EXISTS `billTaskMgr` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `billTaskMgr`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 172.18.3.10    Database: billTaskMgr
-- ------------------------------------------------------
-- Server version	5.5.25a-log

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
-- Table structure for table `RCKCLUSTER`
--

DROP TABLE IF EXISTS `RCKCLUSTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RCKCLUSTER` (
  `CLUSTERTYPE` varchar(20) NOT NULL,
  `HOSTADDR` varchar(255) DEFAULT NULL COMMENT '主机IP地址',
  `EXESTATE` varchar(5) DEFAULT NULL COMMENT '执行状态 N R C E',
  `EXEDATE` varchar(8) DEFAULT NULL COMMENT '最后执行日期',
  `EXESTARTTIME` varchar(6) DEFAULT NULL COMMENT '开始时间',
  `EXEENDTIME` varchar(6) DEFAULT NULL COMMENT '结束时间',
  `ISEXECUTE` varchar(1) DEFAULT NULL COMMENT '是否执行 Y:是 N：否',
  `ISASSIGN` varchar(1) DEFAULT NULL COMMENT '是否指定计算机运行 Y:是 N：否',
  `ASSIGNHOSTADDR` varchar(255) DEFAULT NULL COMMENT '指定计算IP地址【如果是指定计算运行】',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`CLUSTERTYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集群定时任务记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RCKCLUSTER`
--

LOCK TABLES `RCKCLUSTER` WRITE;
/*!40000 ALTER TABLE `RCKCLUSTER` DISABLE KEYS */;
INSERT INTO `RCKCLUSTER` VALUES ('SS','192.168.56.1','R','20150318','141842','141342','Y','N',NULL,'演示数据');
/*!40000 ALTER TABLE `RCKCLUSTER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RCKFLOW`
--

DROP TABLE IF EXISTS `RCKFLOW`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RCKFLOW` (
  `RCKTYPE` varchar(20) NOT NULL COMMENT '流程类型',
  `RCKORDER` int(11) NOT NULL COMMENT '流程顺序',
  `BEANID` varchar(255) DEFAULT NULL COMMENT '实体BEAN',
  `INITSTATE` varchar(5) DEFAULT NULL COMMENT '是否执行状态(Y:是 N:否)',
  `ALOWCHANGE` varchar(5) DEFAULT NULL COMMENT '是否允许更改可执行状态(Y:是 N:否)',
  `EXPCONT` varchar(5) DEFAULT NULL COMMENT '异常时是否继续执行(Y:是 N:否)',
  `SINGLEEXT` varchar(5) DEFAULT NULL COMMENT '单独执行(Y:是 N:否)',
  `REMARK` varchar(255) DEFAULT NULL COMMENT '备注(流程名称)',
  PRIMARY KEY (`RCKTYPE`,`RCKORDER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RCKFLOW`
--

LOCK TABLES `RCKFLOW` WRITE;
/*!40000 ALTER TABLE `RCKFLOW` DISABLE KEYS */;
INSERT INTO `RCKFLOW` VALUES ('FG',1,'flowFileFgTrade','Y','Y','N','N','TRADE 文件下载导入'),('FG',2,'flowFileAcoma','Y','Y','N','N','银联文件ACOMA下载导入 '),('FG',3,'flowFileZpsum','Y','Y','N','Y','直接ZPSUM文件下载导入'),('FG',4,'flowRckFgAcom','Y','Y','N','N','非税对账'),('FG',5,'flowComacomputeAcoma','Y','Y','N','N','手续费计算'),('FG',6,'flowSummAcoma','Y','Y','N','N','清算汇总'),('FG',7,'flowRemit','Y','Y','N','N','清算划账'),('FG',8,'flowFgNotify','Y','Y','N','N','非改生成对账文件，发通知报文'),('FS',1,'flowFileTrade','Y','N','N','N','TRADE 文件下载导入'),('FS',2,'flowFileAcoma','Y','N','N','N','银联文件ACOMA下载导入 '),('FS',3,'flowFileZpsum','Y','N','N','N','直接ZPSUM文件下载导入'),('FS',4,'flowRckAcom','Y','N','N','N','非税对账'),('FS',5,'flowComacomputeAcoma','Y','N','N','N','手续费计算'),('FS',6,'flowSummAcoma','Y','N','N','N','清算汇总'),('FS',7,'flowRemit','Y','N','N','N','清算划账'),('LC',1,'flowFileAcoma','Y','N','N','N','银联文件ACOMA下载导入'),('LC',2,'flowFileAs400Lc','Y','N','N','N','AS400文件下载导入'),('LC',3,'flowFileLcTrade','Y','N','N','N','理财TRADE导入'),('LC',4,'flowRckfinanc','Y','N','N','N','理财对账'),('LC',5,'flowRckacomacompute','Y','N','N','N','理财手续费计算'),('LC',6,'flowSummAcoma','Y','N','N','N','ACOMA汇总到REMIT'),('LC',7,'flowCommonRemit','Y','N','N','N','REMIT划账'),('RQ',1,'flowRqOpenApiTransOrder','Y','Y','N','Y','openapi.trans_order日切'),('RQ',2,'flowRqOpenApiPay','Y','Y','N','Y','openapi.pay日切'),('RQ',3,'flowRqFroadBillPay','Y','Y','N','Y','froadbill.pay日切'),('RQ',4,'flowRqFroadBillBill','Y','Y','N','Y','froadbill.bill日切');
/*!40000 ALTER TABLE `RCKFLOW` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RCKFLOWDETAIL`
--

DROP TABLE IF EXISTS `RCKFLOWDETAIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RCKFLOWDETAIL` (
  `CLEARDATE` varchar(16) NOT NULL COMMENT '流程日期',
  `RCKTYPE` varchar(20) NOT NULL COMMENT '流程类型',
  `RCKORDER` int(11) NOT NULL COMMENT '流程顺序',
  `FLOWNAME` varchar(255) DEFAULT NULL COMMENT '流程名称',
  `FLOWSTATE` varchar(5) DEFAULT NULL COMMENT '流程状态(C:成功 E:异常 N:未执行 R:正在运行)',
  `DEALCOUNT` int(11) DEFAULT NULL COMMENT '处理数量',
  `SUCCESSCOUNT` int(11) DEFAULT NULL COMMENT '成功数量',
  `FAILCOUNT` int(11) DEFAULT NULL COMMENT '失败数量',
  `DEALDATETIME` datetime DEFAULT NULL COMMENT '处理时间',
  `EXCEPTIONDESC` varchar(1000) DEFAULT NULL COMMENT '异常描述',
  `ISEXECUTE` varchar(5) DEFAULT NULL COMMENT '异常时是否继续执行(Y:是 N:否)',
  `ALOWCHANGE` varchar(5) DEFAULT NULL COMMENT '是否允许更改可执行状态(Y:是 N:否)',
  PRIMARY KEY (`CLEARDATE`,`RCKTYPE`,`RCKORDER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程执行记录表';
/*!40101 SET character_set_client = @saved_cs_client */;


