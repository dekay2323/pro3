-- MySQL dump 10.13  Distrib 5.7.16, for osx10.11 (x86_64)
--
-- Host: 127.0.0.1    Database: pro3
-- ------------------------------------------------------
-- Server version	5.7.16

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
-- Table structure for table `account`
--
UNLOCK TABLES;

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `version`, `date_created`, `last_updated`, `name`) VALUES (1,1,'2017-06-30 21:00:22','2017-06-30 21:00:22','TestAccount');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clarification`
--

DROP TABLE IF EXISTS `clarification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clarification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(25) NOT NULL,
  `rfq_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p53ifr5wpe9ynhb6pvk4wu3of` (`name`),
  KEY `FKqp84llmuprl1o5j2fx05oib5x` (`rfq_id`),
  CONSTRAINT `FKqp84llmuprl1o5j2fx05oib5x` FOREIGN KEY (`rfq_id`) REFERENCES `rfq` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clarification`
--

LOCK TABLES `clarification` WRITE;
/*!40000 ALTER TABLE `clarification` DISABLE KEYS */;
/*!40000 ALTER TABLE `clarification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKisxed4kdvp061a3sweh6ml6pg` (`account_id`),
  CONSTRAINT `FKisxed4kdvp061a3sweh6ml6pg` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` (`id`, `version`, `account_id`, `address`, `contact_name`, `date_created`, `last_updated`, `name`, `phone_number`) VALUES (1,0,1,',','Edward','2017-06-30 21:01:55','2017-06-30 21:01:55','TestClient1','403 123 0000'),(2,0,1,NULL,'Stewart','2017-06-30 21:02:13','2017-06-30 21:02:13','TestClient2','403 123 4444');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `criteria`
--

DROP TABLE IF EXISTS `criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `criteria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(255) NOT NULL,
  `request_id` bigint(20) NOT NULL,
  `weighting` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmn211ur0ev7a3rca8o0wvadrt` (`request_id`),
  CONSTRAINT `FKmn211ur0ev7a3rca8o0wvadrt` FOREIGN KEY (`request_id`) REFERENCES `material_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criteria`
--

LOCK TABLES `criteria` WRITE;
/*!40000 ALTER TABLE `criteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lead_time_type`
--

DROP TABLE IF EXISTS `lead_time_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lead_time_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `help` varchar(255) NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_d0rhrp93pjhmbxlh8c868eg8l` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lead_time_type`
--

LOCK TABLES `lead_time_type` WRITE;
/*!40000 ALTER TABLE `lead_time_type` DISABLE KEYS */;
INSERT INTO `lead_time_type` (`id`, `version`, `date_created`, `help`, `last_updated`, `name`) VALUES (1,0,'2017-06-30 20:48:18','Item is in stock no lead time','2017-06-30 20:48:18','In Stock'),(2,0,'2017-06-30 20:48:18','After receipt of order','2017-06-30 20:48:18','ARO'),(3,0,'2017-06-30 20:48:18','After receipt of approved drawing','2017-06-30 20:48:18','ARAD');
/*!40000 ALTER TABLE `lead_time_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_item`
--

DROP TABLE IF EXISTS `line_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `line_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(500) NOT NULL,
  `quantity` int(11) NOT NULL,
  `request_id` bigint(20) NOT NULL,
  `unit_of_measure` varchar(25) NOT NULL,
  `wbs_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqvgdsoujf38sr4qmengrhg66q` (`request_id`),
  KEY `FK91ms1ejpr5m9t482ptqifird` (`wbs_id`),
  CONSTRAINT `FK91ms1ejpr5m9t482ptqifird` FOREIGN KEY (`wbs_id`) REFERENCES `wbs` (`id`),
  CONSTRAINT `FKqvgdsoujf38sr4qmengrhg66q` FOREIGN KEY (`request_id`) REFERENCES `material_request` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_item`
--

LOCK TABLES `line_item` WRITE;
/*!40000 ALTER TABLE `line_item` DISABLE KEYS */;
INSERT INTO `line_item` (`id`, `version`, `code`, `date_created`, `last_updated`, `name`, `quantity`, `request_id`, `unit_of_measure`, `wbs_id`) VALUES (1,0,'1','2017-07-01 17:19:06','2017-07-01 17:19:06','Side Slider 4x4',5,1,'Items',1),(2,0,'2','2017-07-01 17:19:27','2017-07-01 17:19:27','Casement 20x16',2,1,'Items',2),(3,0,'3','2017-07-01 17:19:46','2017-07-01 17:19:46','BayWindow',1,1,'Items',3);
/*!40000 ALTER TABLE `line_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_request`
--

DROP TABLE IF EXISTS `material_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_request` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `budget` decimal(19,2) DEFAULT NULL,
  `code` varchar(25) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `lead_time_id` bigint(20) DEFAULT NULL,
  `name` varchar(500) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `purchase_order_id` bigint(20) DEFAULT NULL,
  `ras_date` varchar(255) DEFAULT NULL,
  `rfq_id` bigint(20) DEFAULT NULL,
  `status_id` bigint(20) NOT NULL,
  `strategy_id` bigint(20) DEFAULT NULL,
  `technical_instructions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj4kn387es2n91384tyslgxg9n` (`lead_time_id`),
  KEY `FKfvcokvuiinyn84iu0n5cbare4` (`project_id`),
  KEY `FKgsm5fisx53j8lpxdon35ekrki` (`purchase_order_id`),
  KEY `FKq0ml3t0sbf76shemiypm54u8` (`rfq_id`),
  KEY `FKbvy69vypw7mupfr9cndvmc1r8` (`status_id`),
  KEY `FKkak2opo3ow3ieslnginlfrqms` (`strategy_id`),
  CONSTRAINT `FKbvy69vypw7mupfr9cndvmc1r8` FOREIGN KEY (`status_id`) REFERENCES `request_status` (`id`),
  CONSTRAINT `FKfvcokvuiinyn84iu0n5cbare4` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`),
  CONSTRAINT `FKgsm5fisx53j8lpxdon35ekrki` FOREIGN KEY (`purchase_order_id`) REFERENCES `purchase_order` (`id`),
  CONSTRAINT `FKj4kn387es2n91384tyslgxg9n` FOREIGN KEY (`lead_time_id`) REFERENCES `lead_time_type` (`id`),
  CONSTRAINT `FKkak2opo3ow3ieslnginlfrqms` FOREIGN KEY (`strategy_id`) REFERENCES `strategy` (`id`),
  CONSTRAINT `FKq0ml3t0sbf76shemiypm54u8` FOREIGN KEY (`rfq_id`) REFERENCES `rfq` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_request`
--

LOCK TABLES `material_request` WRITE;
/*!40000 ALTER TABLE `material_request` DISABLE KEYS */;
INSERT INTO `material_request` (`id`, `version`, `budget`, `code`, `date_created`, `last_updated`, `lead_time_id`, `name`, `project_id`, `purchase_order_id`, `ras_date`, `rfq_id`, `status_id`, `strategy_id`, `technical_instructions`) VALUES (1,3,50000.00,'1','2017-07-01 17:02:32','2017-07-01 17:20:38',NULL,'Windows',1,NULL,'2018-07-01',1,4,2,'A really large technical instruction'),(2,0,25000.00,'2','2017-07-01 17:03:27','2017-07-01 17:03:27',NULL,'Doors',1,NULL,'2018-07-01',NULL,2,2,NULL),(3,0,30000.00,'3','2017-07-01 17:04:35','2017-07-01 17:04:35',NULL,'Roofing',1,NULL,'2018-07-01',NULL,2,2,NULL);
/*!40000 ALTER TABLE `material_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_request_user`
--

DROP TABLE IF EXISTS `material_request_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_request_user` (
  `material_request_bidders_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  KEY `FKs3m42xqfbf60agj68xkroyy1x` (`user_id`),
  KEY `FK5vs1qtwg4o8rergxwnfbda9i3` (`material_request_bidders_id`),
  CONSTRAINT `FK5vs1qtwg4o8rergxwnfbda9i3` FOREIGN KEY (`material_request_bidders_id`) REFERENCES `material_request` (`id`),
  CONSTRAINT `FKs3m42xqfbf60agj68xkroyy1x` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_request_user`
--

LOCK TABLES `material_request_user` WRITE;
/*!40000 ALTER TABLE `material_request_user` DISABLE KEYS */;
INSERT INTO `material_request_user` (`material_request_bidders_id`, `user_id`) VALUES (1,4),(1,5);
/*!40000 ALTER TABLE `material_request_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material_request_vddr`
--

DROP TABLE IF EXISTS `material_request_vddr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material_request_vddr` (
  `material_request_vddrs_id` bigint(20) NOT NULL,
  `vddr_id` bigint(20) DEFAULT NULL,
  KEY `FKifsqx3a2xd4gncd89wrnk6k62` (`vddr_id`),
  KEY `FKl07k8va15o27c13m74b9g4kph` (`material_request_vddrs_id`),
  CONSTRAINT `FKifsqx3a2xd4gncd89wrnk6k62` FOREIGN KEY (`vddr_id`) REFERENCES `vddr` (`id`),
  CONSTRAINT `FKl07k8va15o27c13m74b9g4kph` FOREIGN KEY (`material_request_vddrs_id`) REFERENCES `material_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material_request_vddr`
--

LOCK TABLES `material_request_vddr` WRITE;
/*!40000 ALTER TABLE `material_request_vddr` DISABLE KEYS */;
/*!40000 ALTER TABLE `material_request_vddr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `option_line_item`
--

DROP TABLE IF EXISTS `option_line_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `option_line_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `lead_time` int(11) DEFAULT NULL,
  `line_item_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `quote_id` bigint(20) NOT NULL,
  `unit_of_measure` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1rik6wbhv8r0aqmuwjw0i476w` (`line_item_id`),
  KEY `FKqxe9jr5r0dl5rvynua5o0b1dc` (`quote_id`),
  CONSTRAINT `FK1rik6wbhv8r0aqmuwjw0i476w` FOREIGN KEY (`line_item_id`) REFERENCES `line_item` (`id`),
  CONSTRAINT `FKqxe9jr5r0dl5rvynua5o0b1dc` FOREIGN KEY (`quote_id`) REFERENCES `quote` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `option_line_item`
--

LOCK TABLES `option_line_item` WRITE;
/*!40000 ALTER TABLE `option_line_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `option_line_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `client_id` bigint(20) NOT NULL,
  `code` varchar(25) NOT NULL,
  `date_created` datetime NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eh3nusutt0qy84a4yr9pfxkyg` (`code`),
  KEY `FK8nw995uro0115f1go0dmrtn2d` (`client_id`),
  CONSTRAINT `FK8nw995uro0115f1go0dmrtn2d` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` (`id`, `version`, `client_id`, `code`, `date_created`, `description`, `last_updated`, `name`) VALUES (1,0,1,'1','2017-06-30 21:05:24',NULL,'2017-06-30 21:05:24','Build a House'),(2,0,1,'2','2017-06-30 21:41:30',NULL,'2017-06-30 21:41:30','Build a Pool'),(3,0,2,'2-A','2017-06-30 21:43:44',NULL,'2017-06-30 21:43:44','Pipeline');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_user`
--

DROP TABLE IF EXISTS `project_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_user` (
  `project_managers_id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  KEY `FK4jl2o131jivd80xsuw6pivnbx` (`user_id`),
  KEY `FKayufcm3b2jcssufbtn3t1i149` (`project_managers_id`),
  CONSTRAINT `FK4jl2o131jivd80xsuw6pivnbx` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKayufcm3b2jcssufbtn3t1i149` FOREIGN KEY (`project_managers_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_user`
--

LOCK TABLES `project_user` WRITE;
/*!40000 ALTER TABLE `project_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `quote_id` bigint(20) NOT NULL,
  `rfq_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf9hqi1we2jr8to0brhvpafp2t` (`quote_id`),
  KEY `FKbj6jaxurxa7mkx6lqmalcojjm` (`rfq_id`),
  CONSTRAINT `FKbj6jaxurxa7mkx6lqmalcojjm` FOREIGN KEY (`rfq_id`) REFERENCES `rfq` (`id`),
  CONSTRAINT `FKf9hqi1we2jr8to0brhvpafp2t` FOREIGN KEY (`quote_id`) REFERENCES `quote` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quote`
--

DROP TABLE IF EXISTS `quote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `bidding` bit(1) DEFAULT NULL,
  `changed_by_id` bigint(20) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `contact_phone_number` varchar(255) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `not_bidding_reason` varchar(255) DEFAULT NULL,
  `rfq_id` bigint(20) NOT NULL,
  `status_id` bigint(20) NOT NULL,
  `vendor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmldnb4l7kx4gy2arqliiapyrn` (`changed_by_id`),
  KEY `FKkd02cq6gaujlorfrvcp191qjj` (`rfq_id`),
  KEY `FKe3x4ia40owikbd9273pci14xl` (`status_id`),
  KEY `FKi1rh6mdb9g2rlxrhfyynpjdop` (`vendor_id`),
  CONSTRAINT `FKe3x4ia40owikbd9273pci14xl` FOREIGN KEY (`status_id`) REFERENCES `quote_status` (`id`),
  CONSTRAINT `FKi1rh6mdb9g2rlxrhfyynpjdop` FOREIGN KEY (`vendor_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkd02cq6gaujlorfrvcp191qjj` FOREIGN KEY (`rfq_id`) REFERENCES `rfq` (`id`),
  CONSTRAINT `FKmldnb4l7kx4gy2arqliiapyrn` FOREIGN KEY (`changed_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quote`
--

LOCK TABLES `quote` WRITE;
/*!40000 ALTER TABLE `quote` DISABLE KEYS */;
INSERT INTO `quote` (`id`, `version`, `bidding`, `changed_by_id`, `code`, `contact_email`, `contact_name`, `contact_phone_number`, `date_created`, `last_updated`, `not_bidding_reason`, `rfq_id`, `status_id`, `vendor_id`) VALUES (1,3,'',2,NULL,NULL,NULL,NULL,'2017-07-01 17:20:29','2017-07-01 17:52:42',NULL,1,3,4),(2,1,NULL,2,NULL,NULL,NULL,NULL,'2017-07-01 17:20:38','2017-07-01 17:20:38',NULL,1,1,5);
/*!40000 ALTER TABLE `quote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quote_line_item`
--

DROP TABLE IF EXISTS `quote_line_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quote_line_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `check_off` bit(1) NOT NULL,
  `code` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `lead_time` int(11) DEFAULT NULL,
  `lead_time_type_id` bigint(20) DEFAULT NULL,
  `line_item_id` bigint(20) NOT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `quote_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKexp8ncr9oiwlpvlouvhsvhv63` (`lead_time_type_id`),
  KEY `FKrblx0q36nb9flxj58ae8btib4` (`line_item_id`),
  KEY `FKo7bheg124qeqs6w7xwanrirnw` (`quote_id`),
  CONSTRAINT `FKexp8ncr9oiwlpvlouvhsvhv63` FOREIGN KEY (`lead_time_type_id`) REFERENCES `lead_time_type` (`id`),
  CONSTRAINT `FKo7bheg124qeqs6w7xwanrirnw` FOREIGN KEY (`quote_id`) REFERENCES `quote` (`id`),
  CONSTRAINT `FKrblx0q36nb9flxj58ae8btib4` FOREIGN KEY (`line_item_id`) REFERENCES `line_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quote_line_item`
--

LOCK TABLES `quote_line_item` WRITE;
/*!40000 ALTER TABLE `quote_line_item` DISABLE KEYS */;
INSERT INTO `quote_line_item` (`id`, `version`, `check_off`, `code`, `date_created`, `last_updated`, `lead_time`, `lead_time_type_id`, `line_item_id`, `price`, `quote_id`) VALUES (1,1,'','1','2017-07-01 17:20:29','2017-07-01 17:52:30',3,2,1,500.00,1),(2,1,'','2','2017-07-01 17:20:29','2017-07-01 17:52:30',2,3,2,200.00,1),(3,1,'','3','2017-07-01 17:20:29','2017-07-01 17:52:30',4,2,3,500.00,1),(4,0,'\0','1','2017-07-01 17:20:38','2017-07-01 17:20:38',NULL,NULL,1,NULL,2),(5,0,'\0','2','2017-07-01 17:20:38','2017-07-01 17:20:38',NULL,NULL,2,NULL,2),(6,0,'\0','3','2017-07-01 17:20:38','2017-07-01 17:20:38',NULL,NULL,3,NULL,2);
/*!40000 ALTER TABLE `quote_line_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quote_status`
--

DROP TABLE IF EXISTS `quote_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quote_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_tn34ndlyht4cp5luddfi1rilv` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quote_status`
--

LOCK TABLES `quote_status` WRITE;
/*!40000 ALTER TABLE `quote_status` DISABLE KEYS */;
INSERT INTO `quote_status` (`id`, `version`, `date_created`, `last_updated`, `name`) VALUES (1,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','START'),(2,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','INTENTION_TO_BID'),(3,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','BID'),(4,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','NOT_BIDDING'),(5,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','PO'),(6,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','PO_LOST');
/*!40000 ALTER TABLE `quote_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration_code`
--

DROP TABLE IF EXISTS `registration_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registration_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration_code`
--

LOCK TABLES `registration_code` WRITE;
/*!40000 ALTER TABLE `registration_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `registration_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_status`
--

DROP TABLE IF EXISTS `request_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ltt9k9hsmsdbs5q2t1oaejxm7` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_status`
--

LOCK TABLES `request_status` WRITE;
/*!40000 ALTER TABLE `request_status` DISABLE KEYS */;
INSERT INTO `request_status` (`id`, `version`, `date_created`, `last_updated`, `name`) VALUES (1,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','ADD_TO_PLAN'),(2,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','START'),(3,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','APPROVED_TO_PLAN'),(4,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','RFQ_ISSUED'),(5,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','BIDS_RECIEVED'),(6,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','EVALUATION_COMPLETE'),(7,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','PO_ISSUED');
/*!40000 ALTER TABLE `request_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rfq`
--

DROP TABLE IF EXISTS `rfq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rfq` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rfq`
--

LOCK TABLES `rfq` WRITE;
/*!40000 ALTER TABLE `rfq` DISABLE KEYS */;
INSERT INTO `rfq` (`id`, `version`, `date_created`, `last_updated`, `name`) VALUES (1,0,'2017-07-01 17:20:29','2017-07-01 17:20:29','1 - Windows');
/*!40000 ALTER TABLE `rfq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_irsamgnera6angm0prq1kemt2` (`authority`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `version`, `authority`, `name`) VALUES (1,0,'ROLE_ADMIN','Admin'),(2,0,'ROLE_USER','User'),(3,0,'ROLE_VENDOR','Vendor');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `strategy`
--

DROP TABLE IF EXISTS `strategy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `strategy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ijdlfwufomeirxn4yddpckkui` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `strategy`
--

LOCK TABLES `strategy` WRITE;
/*!40000 ALTER TABLE `strategy` DISABLE KEYS */;
INSERT INTO `strategy` (`id`, `version`, `date_created`, `last_updated`, `name`) VALUES (1,0,'2017-06-30 20:48:17','2017-06-30 20:48:17','Sole Source'),(2,0,'2017-06-30 20:48:18','2017-06-30 20:48:18','Competitive Bid');
/*!40000 ALTER TABLE `strategy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKc3b4xfbq6rbkkrddsdum8t5f0` (`account_id`),
  CONSTRAINT `FKc3b4xfbq6rbkkrddsdum8t5f0` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `version`, `account_id`, `account_expired`, `account_locked`, `email`, `enabled`, `password`, `password_expired`, `username`) VALUES (1,0,1,'\0','\0','contact@procurableapp.com','','$2a$10$ZK2Egqs2EYaOBLMWd2X0ou4nAtVScOxqfj64QIthGWRc.8zPv/0LK','\0','admin'),(2,0,1,'\0','\0','contact@procurableapp.com','','$2a$10$maMvCjv1iCqGpc0jJQkHZuICrQ5Udifb.pCepgb4egZ81PJo6AlNO','\0','user1'),(3,0,1,'\0','\0','contact@procurableapp.com','','$2a$10$Q8kaXRmN/NptmT9HJ/CuOOdw2Yl2PVumW/7NSkrVeEmGzlcTASLwi','\0','user2'),(4,0,1,'\0','\0','contact@procurableapp.com','','$2a$10$e1WNCb26YE93Jt92uaJPEecpAK1soWb9UmmUKO9eyDgyyErZdmqHa','\0','vendor1'),(5,0,1,'\0','\0','contact@procurableapp.com','','$2a$10$UkuPie8KHPhyAaG1NpfsvuHcPAdm6dziWKORgUr4vwhELg/PN9vgu','\0','vendor2');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1,1),(2,2),(3,2),(4,3),(5,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vddr`
--

DROP TABLE IF EXISTS `vddr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vddr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `code` varchar(50) NOT NULL,
  `copies` int(11) DEFAULT NULL,
  `copies_final` int(11) DEFAULT NULL,
  `copies_for_review` int(11) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vddr`
--

LOCK TABLES `vddr` WRITE;
/*!40000 ALTER TABLE `vddr` DISABLE KEYS */;
/*!40000 ALTER TABLE `vddr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wbs`
--

DROP TABLE IF EXISTS `wbs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wbs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `code` varchar(25) NOT NULL,
  `date_created` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `last_updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_21m3dupe2w6coprgf1cqym60m` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wbs`
--

LOCK TABLES `wbs` WRITE;
/*!40000 ALTER TABLE `wbs` DISABLE KEYS */;
INSERT INTO `wbs` (`id`, `version`, `code`, `date_created`, `description`, `last_updated`) VALUES (1,0,'105.1','2017-06-30 20:48:18',NULL,'2017-06-30 20:48:18'),(2,0,'105.2','2017-06-30 20:48:18',NULL,'2017-06-30 20:48:18'),(3,0,'106.1','2017-06-30 20:48:18',NULL,'2017-06-30 20:48:18');
/*!40000 ALTER TABLE `wbs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-01 19:03:40
