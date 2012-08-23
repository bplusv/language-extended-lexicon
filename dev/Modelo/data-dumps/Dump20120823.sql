CREATE DATABASE  IF NOT EXISTS `lel` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `lel`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: lel
-- ------------------------------------------------------
-- Server version	5.5.25a

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
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'general'),(2,'requirement'),(3,'system');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'In The Day We Died, Walter explains that a time paradox is responsible for the matter at hand. At some point after May 2026, they will send the device that destroyed one universe, and is devastating this universe.','2012-07-11 02:21:42',1),(2,'the music channel of ABS-CBN and is currently one of the most popular music channels in the Philippines. ... MTV Philippines is a 24-hour Music/Entertainment television network, owned by All Youth Channel','2012-07-11 02:21:42',1),(3,'But I can remember things that Peter couldn\'t have known, like details of cases that he wasn\'t on or names, um, places he\'s never been.','2012-07-11 02:21:42',1),(4,' In The Last Sam Weiss, he and Olivia find an ancient key in a museum that identifies her as the second human factor involved with controlling the Wave Sink Device. He explains his heritage in the grand scheme, but is left behind as non-essential personnel','2012-07-11 02:21:42',1),(5,' Science is the fourth album released by the Norwegian singer/songwriter Thomas Dybdahl','2012-07-11 02:21:42',1),(6,' Any of various extinct reptiles belonging to the Dinosauria, existing between about 230 million and 65 million years ago; A person or organisation which is very old or has very old-fashioned views or is not willing to change and adapt; Anything that is n','2012-07-11 02:21:42',1),(7,' a computer file that contains text (and possibly formatting instructions) ','2012-07-11 02:21:42',1),(8,' In the January 2010 version of the ranking, Nintendo scored 1.9 points, at which, three days later, Nintendo issued a response that addressed primary concerns, highlighting a policy to indicate the materials used in each product, which makes end-of-life','2012-07-11 02:21:42',1),(9,' The console contains a number of internal features made available from its hardware and firmware components. The hardware allows for extendibility through expansion ports while the firmware and some other pieces of software can receive periodic updates v','2012-07-11 02:21:42',1),(10,'Another Adventure Gamers editor Kim Wild stated that while she has matured since Another Code, citing her learning the guitar and starting a band, as well as becoming more logical, she still demonstrates a \"childlike frustration\" for her father','2012-07-11 02:21:42',1),(11,' There is an Ashley trophy in Super Smash Bros. Brawl.','2012-07-11 02:21:42',1),(15,'This is another comment for the ancient people test...','2012-08-10 14:21:42',2),(16,'A good Comment it\'s a art','2012-08-23 14:21:42',1),(17,'For yourself thy comment too short, this it\'s a brief comment of yours','2012-08-23 16:21:42',1),(18,'And one last comment for this great test of usability','2012-08-23 17:22:01',2);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'2012-07-11 02:21:42',1,1,1),(2,'2012-07-13 19:45:53',1,1,2),(3,'2012-07-13 19:46:02',1,1,3),(4,'2012-07-13 19:46:12',1,1,4),(5,'2012-07-13 20:20:27',1,1,5),(6,'2012-07-13 20:20:45',1,1,6),(7,'2012-07-14 04:32:53',1,2,3),(8,'2012-07-14 04:33:04',1,2,6),(9,'2012-07-14 04:33:16',1,2,2),(10,'2012-07-14 04:33:28',1,2,4),(11,'2012-07-14 04:33:41',1,2,5),(12,'2012-07-14 04:33:49',1,2,1),(13,'2012-07-24 17:53:15',1,2,3),(14,'2012-07-24 17:54:02',1,2,6),(15,'2012-07-24 17:54:09',1,2,6),(16,'2012-07-24 17:54:51',1,2,2),(17,'2012-07-24 17:55:49',1,2,4),(18,'2012-07-24 17:56:09',1,2,5),(19,'2012-07-24 18:06:46',1,1,7),(20,'2012-07-24 18:06:54',1,1,8),(21,'2012-07-24 18:07:21',1,1,9),(22,'2012-07-24 20:58:22',1,2,7),(23,'2012-07-24 21:23:43',1,2,1),(24,'2012-07-25 17:33:41',1,1,10),(25,'2012-07-25 17:35:06',1,1,11),(26,'2012-07-25 17:35:27',1,2,11),(27,'2012-07-25 18:30:21',1,2,1),(28,'2012-07-25 18:31:19',1,2,1),(29,'2012-07-25 19:03:10',1,1,12),(30,'2012-07-25 19:04:57',1,1,13),(31,'2012-07-27 16:13:14',1,2,1),(32,'2012-07-27 16:13:32',1,2,1),(33,'2012-07-27 16:39:57',1,1,14),(34,'2012-07-27 16:40:15',1,2,14),(35,'2012-07-27 16:50:22',1,2,7),(36,'2012-07-27 16:56:32',1,1,7),(37,'2012-07-27 16:56:32',1,1,8),(38,'2012-07-27 16:56:32',1,1,9),(39,'2012-07-27 16:57:05',1,2,15),(40,'2012-07-27 16:58:28',1,1,16),(41,'2012-07-27 16:58:28',1,1,7),(42,'2012-07-27 16:58:28',1,1,8),(43,'2012-07-27 16:58:28',1,1,9),(44,'2012-07-27 16:58:28',1,1,15),(45,'2012-07-27 17:00:53',1,2,11),(46,'2012-07-27 17:09:09',1,2,11),(47,'2012-07-27 17:10:52',1,2,11),(48,'2012-07-27 17:10:52',1,2,10),(49,'2012-07-27 17:13:54',1,2,2),(50,'2012-07-27 17:13:54',1,2,1),(51,'2012-07-27 17:13:54',1,2,14),(52,'2012-07-27 17:14:09',1,2,2),(53,'2012-07-27 17:14:09',1,2,1),(54,'2012-07-27 17:14:09',1,2,14),(55,'2012-07-27 18:18:19',1,1,17),(56,'2012-07-27 19:07:30',2,2,11),(57,'2012-07-27 19:07:30',2,2,10),(58,'2012-07-27 19:16:43',1,1,18),(59,'2012-07-27 19:18:01',1,1,19),(60,'2012-07-27 19:19:04',1,1,20),(61,'2012-07-27 19:22:41',1,1,21),(62,'2012-07-27 19:24:05',1,1,22),(63,'2012-07-27 19:24:36',1,2,22),(64,'2012-07-27 19:27:43',1,2,11),(65,'2012-07-27 19:27:43',1,2,10),(66,'2012-07-27 19:27:56',1,2,11),(67,'2012-07-27 19:27:56',1,2,10),(68,'2012-07-27 19:28:16',2,2,11),(69,'2012-07-27 19:28:16',2,2,10),(70,'2012-07-27 19:28:57',2,2,15),(71,'2012-07-27 19:28:57',2,2,3),(72,'2012-07-28 17:01:36',2,1,23),(73,'2012-08-08 20:01:20',1,1,24),(74,'2012-08-09 16:32:01',1,2,7),(75,'2012-08-09 16:32:01',1,2,8),(76,'2012-08-09 16:32:01',1,2,9),(77,'2012-08-09 16:32:02',1,2,16),(78,'2012-08-10 01:38:26',1,1,25),(79,'2012-08-17 00:24:28',1,1,26),(80,'2012-08-17 18:20:59',1,2,14),(81,'2012-08-17 18:20:59',1,2,2),(82,'2012-08-17 20:21:18',1,2,7),(83,'2012-08-17 20:21:18',1,2,8),(84,'2012-08-17 20:21:18',1,2,9),(85,'2012-08-17 20:21:18',1,2,16),(86,'2012-08-17 20:22:57',1,2,7),(87,'2012-08-17 20:22:57',1,2,8),(88,'2012-08-17 20:22:57',1,2,9),(89,'2012-08-17 20:22:57',1,2,16),(90,'2012-08-20 18:09:23',1,2,11),(91,'2012-08-20 18:09:23',1,2,10),(92,'2012-08-20 18:09:52',1,2,11),(93,'2012-08-20 18:09:52',1,2,10),(94,'2012-08-20 18:16:04',1,2,11),(95,'2012-08-20 18:16:04',1,2,10),(96,'2012-08-20 18:17:33',1,2,10),(97,'2012-08-20 18:17:33',1,2,11),(98,'2012-08-20 18:32:17',1,2,25),(99,'2012-08-20 19:26:29',1,1,27),(100,'2012-08-20 19:26:29',1,1,14),(101,'2012-08-20 19:26:29',1,1,2),(102,'2012-08-20 19:40:21',1,2,20),(103,'2012-08-20 19:40:21',1,2,22),(104,'2012-08-20 19:40:39',1,2,22),(105,'2012-08-20 19:40:39',1,2,20),(106,'2012-08-20 19:41:53',1,2,21),(107,'2012-08-20 19:57:20',1,2,8),(108,'2012-08-20 19:57:20',1,2,10),(109,'2012-08-20 19:57:20',1,2,11),(110,'2012-08-21 16:29:58',1,2,14),(111,'2012-08-21 16:29:58',1,2,2),(112,'2012-08-21 16:29:58',1,2,27),(113,'2012-08-21 16:34:30',1,1,28),(114,'2012-08-21 16:35:18',1,2,28),(115,'2012-08-21 16:37:34',1,1,29);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `classification`
--

LOCK TABLES `classification` WRITE;
/*!40000 ALTER TABLE `classification` DISABLE KEYS */;
INSERT INTO `classification` VALUES (1,'object'),(2,'state'),(3,'subject'),(4,'verb');
/*!40000 ALTER TABLE `classification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `definition`
--

LOCK TABLES `definition` WRITE;
/*!40000 ALTER TABLE `definition` DISABLE KEYS */;
INSERT INTO `definition` VALUES (1,'They were a people of great technological prowess who made the ultimate discovery.\r\nA mechanism known to them as The Vacuum, containing at once both the power to Create,\r\nand to Destroy.',' Information book from the ancient manuscript about gaining entry into the Wave Sink Device once it was activated was not published in the books','Sam possesses those ancient writings and uses the knowledge to determine the missing component to the ancient device',3,4),(2,'Radio is the transmission of signals by modulation of electromagnetic waves with frequencies below those of visible light. Electromagnetic radiation travels by means of oscillating electromagnetic fields that pass through the air and the vacuum of space','A band of adjacent frequencies having sufficient width to permit its use for radio communications.','faja de frecuencia, canal de radio, (en España y Argentina) radiocanal',2,3),(3,'The whole world is on fire, starting with my office. We\'ve had to completely reevaluate all our security measures after what that woman did. The truth is... I wanted to give you time to collect your thoughts.\r\nI know when there\'s something you want to talk about.\r\nI\'m in love with Peter.\r\nI know it sounds absurd to you.\r\nI hardly know him.\r\nBut it\'s like I\'ve known him my entire life.\r\nAnd everybody, including him, keeps telling me that it\'s impossible.\r\nWhen Walter told me what you were going through -- two sets of memories, two sets of experiences -- he said the Cortexiphan dosing might be enabling you to remember things that Peter wants you to remember.',NULL,NULL,1,NULL),(4,'Nina Sharp recommended Olivia meet Sam Weiss (Night Of Desirable Objects) following her initial return from the alternate universe. According to Nina, Sam is not a psychologist, but he was able to help her recover from the ordeal with her arm. Olivia later discovered that the Sam works as a bowling alley manager. Sam Weiss predicted that Olivia will get headaches.','In Dream Logic, Sam asked Olivia if she could collect business cards from people wearing something red','Olivia once again visits Sam in the bowling alley, seeking advice for her sleeplessness and secret she is hiding',3,1),(5,'The intellectual and practical activity encompassing the systematic study of the structure and behavior of the physical and natural world through observation and experiment',NULL,NULL,1,NULL),(6,'A fossil reptile of the Mesozoic era, often reaching an enormous size\r\nA person or thing that is outdated or has become obsolete because of failure to adapt to changing circumstances','Dinosaur!, not to be confused with Dinosaurs! or Dinosaur, is an American television documentary about dinosaurs and first aired on CBS in the United States on November 5, 1985.','any of numerous extinct terrestrial reptiles of the Mesozoic era',2,2),(7,'A piece of written, printed, or electronic matter that provides information or evidence or \r\nthat serves as an official record for the book.','writing that provides \r\ninformation (especially information\r\nof an official nature)\r\n\r\nrecord in detail; \"The parents documented every step of their child\'s development\"','anything serving as a representation of a person\'s thinking by means of symbolic marks\r\n\r\nsupport or supply with references; \"Can you document your claims?\"',3,4),(8,'','','',3,4),(9,'','','',3,4),(10,'Nintendo Co., Ltd. (任天堂株式会社 Nintendō Kabushiki gaisha?) is a Japanese multinational \r\nconsumer electronics company located in Kyoto, Japan. Founded on September 23, 1889[2] by\r\n\r\n\r\n\r\nFusajiro Yamauchi, it produced handmade hanafuda cards.[6] By 1963, the company had tried several small niche businesses, such as a cab company and a love hotel.[7] Nintendo is the world\'s largest gaming company by revenue.[8]','Nintendo of America has engaged in several high-profile marketing campaigns to define and position its brand. One of its earliest and most enduring slogans was \"Now you\'re playing with power!\", used first to promote its Nintendo Entertainment System.\r\n\r\nas Nintendo of america','t modified the slogan to include\r\n\r\n\r\n\"SUPER power\" for the Super Nintendo \r\n\r\nEntertainment System, and \"PORTABLE power\"\r\n\r\n\r\nfor the Game Boy. Its 1994 \"Play It Loud!\" campaign played upon teenage rebellion and fostered an edgy reputation',2,2),(11,'The Wii ( /ˈwiː/) is a home video game console released by Nintendo on November 19, 2006. As a seventh-generation console, the Wii competes with Microsoft\'s Xbox 360 and Sony\'s PlayStation 3. Nintendo states that its console targets a broader demographic than that of the two others.[8] As of the first quarter of 2012, the Wii leads the generation over the PlayStation 3 and Xbox 360 in worldwide sales,[9] and in December 2009, the console broke the record for best-selling console in a single month in the United States.[10]','The first Wii system software update via WiiConnect24 caused a very small portion of launch units to become completely unusable. This forced users to either send their units to Nintendo for repairs (if they wished to retain their saved data) or exchange it for a free replacement.','With the release of dual-layer Wii Optical Discs, Nintendo of America has stated that some Wii systems may have difficulty reading the high-density software due to a contaminated laser lens. Nintendo is offering retail lens cleaning kits and free console repairs for owners who experience this issue.',3,1),(12,'Ashley Mizuki Robbins (アシュレイ・ミズキ・ロビンズ?, surname often spelled Robins in PAL media) is a 16 year old fictional character in the Another Code series of video games. She first appeared in the Nintendo DS video game Another Code: Two Memories, known as Trace Memory in North America, and again later in the Wii video game Another Code: R - A Journey into Lost Memories.',' Four Fat Chicks editor \"Old Rooster\" described her as a petulant child, describing her as awkward, stubborn, and difficult, yet also elicits concern','Game Nikki editor James Williams described her as astonishing, stating that at no point did he consider her facial expressions to not be authentic',2,3),(13,'Ashley\'s deceased mother, A strong-willed Japanese scientist who specialized in researching memories, died when Ashley was only three years old. Originally from Japan, Sayoko came to the States to research Human memory in the same secret lab as Richard. She first met Richard when they were both working for MJ Labs.',NULL,NULL,1,NULL),(14,'sea','','',3,4),(15,'astro','','',3,4),(16,'she','','',3,4),(17,'central','','',3,4),(18,'wb','','',3,4),(19,'recy',NULL,NULL,1,NULL),(20,'','','',3,4),(21,'','','',3,4),(22,'u for Nintendo ','','',3,4),(23,'plural','','',3,4),(24,'this is a Hello and the ancient people.','','',3,4),(25,'this is a evolved symbol...','','',3,4);
/*!40000 ALTER TABLE `definition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `symbol`
--

LOCK TABLES `symbol` WRITE;
/*!40000 ALTER TABLE `symbol` DISABLE KEYS */;
INSERT INTO `symbol` VALUES (1,'\0','The First People',1,1,1),(2,'','radio channels',1,1,1),(3,'','ancient people',1,3,1),(4,'','Sam Weiss',1,4,1),(5,'','science',1,5,1),(6,'','dinosaurs',1,6,1),(7,'','document',1,7,1),(8,'','book',1,10,1),(9,'','paper',1,7,1),(10,'','Nintendo',2,10,1),(11,'','Wii',2,10,1),(12,'','Ashley Mizuki Robbins',3,12,2),(13,'','Sayoko Robbins',3,13,2),(14,'','Peter Bishop',1,1,1),(15,'','Massive Dynamic',1,3,1),(16,'','The Vacuum',1,7,1),(17,'','Seamus',1,14,1),(18,'','Astrid',1,15,1),(19,'','Ella',1,16,1),(20,'','Central Park',1,19,1),(21,'','William Bell',1,18,1),(22,'','Reciprocity',1,19,1),(23,'','The system',2,20,1),(24,'\0','made the same search a few years ago.In Concentrate And Ask Again, Nina',1,21,1),(25,'','Wii U',2,22,1),(26,'','books',1,23,1),(27,'','successor',2,1,1),(28,'','Hello',1,24,1),(29,'','evolved',1,25,1);
/*!40000 ALTER TABLE `symbol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project_users`
--

LOCK TABLES `project_users` WRITE;
/*!40000 ALTER TABLE `project_users` DISABLE KEYS */;
INSERT INTO `project_users` VALUES (1,1),(2,1),(3,1);
/*!40000 ALTER TABLE `project_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'first project',NULL),(2,'Other project',NULL),(3,'Another project',NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'luis','502ff82f7f1f8218dd41201fe4353687',''),(2,'yanet','a9eca9ca64f925e3ad82eca500717c5c',''),(3,'kolmos','266098d7428528582ede232a39fc31ca',''),(4,'irving','5e4d614d1c5e99716f23462a4e6aba4d','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `definition_comments`
--

LOCK TABLES `definition_comments` WRITE;
/*!40000 ALTER TABLE `definition_comments` DISABLE KEYS */;
INSERT INTO `definition_comments` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(3,15),(3,16),(3,17),(3,18);
/*!40000 ALTER TABLE `definition_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `document`
--

LOCK TABLES `document` WRITE;
/*!40000 ALTER TABLE `document` DISABLE KEYS */;
INSERT INTO `document` VALUES (1,'The First People','The First People is Hello a document(6955 kHz) by Seamus Wiles that was \r\npublished in 1897. In this new book this is an evolving fact!\r\nThe book describes a race of humans that evolved before the dinosaurs.\r\n\r\nOnly one edition of the book is believed to have been printed.\r\nEdward Markham gives Peter Bishop a rare copy of the book so the science team Sam Weiss\r\ncan start solving the puzzling broadcast of numbers over high \r\nfrequency radio channels.\r\n\r\nThis is a book of The Vacuum.\r\n\r\nOnce the team begins to review the book, they Hello the author\'s claim that a \r\ncataclysm completely decimated the First People, and any historical record they \r\nmay have left. The ancient humans were technologically advanced and knew the source \r\nof all creation and destruction, which they called The Vacuum.\r\n\r\nWiles claims that time was measured in months with widely varying numbers of days, \r\ni.e. 12, 34, 17, 9, 15, 8, 42, 40, etc....Peter suspects that the Numbers Broadcasts \r\ncorrespond exactly with the numbers in the book, matching the calendar.\r\n\r\nAstrid doesn\'t buy the claim in the book that an ancient people evolved before dinosaurs,\r\nand then vanished without a trace. Walter has no issue with justifying the disappearance \r\nof the First People - history is full of mass extinction events.\r\n\r\nIn Reciprocity, three more copies of the book are found by Massive Dynamic.\r\nThe three books were written in different languages by three different authors \r\nand published within two years of each other. Brandon claimed that the books were \r\nnot much different from one another paper. \r\n\r\nHe also told Nina that William Bell made the same search a few years ago.\r\nIn Concentrate And Ask Again, Nina Sharp reviews the multiple copies of the book that \r\nhave been quietly secured over the years and stored at Massive Dynamic.\r\nIt dawns on her that the letters in the name of each author are identical.\r\nShe manipulates the letters and finds the name of an old friend.\r\nShe promptly visits Sam Weiss and confronts him with her concerns.\r\nIn 6:02 AM EST, Nina shares what she knows with Olivia about the collection of\r\nFirst People books from around the planet - and the connection they have with Sam Weiss\r\nand the Wave Sink Device.In The Last Sam Weiss, Sam explains that The First People books \r\nare based on an ancient manuscript discovered by the his \r\ngreat-great-great-great-grandfather, Sam Weiss. \r\n\r\nInterim generations continued to search for additional ancient information, \r\nbefore the books were written by the fifth Sam Weiss in the late-1890\'s. \r\nInformation from the ancient manuscript about gaining entry into the Wave Sink Device \r\nonce it was activated was not published in the books.\r\n\r\nSam possesses those ancient writings and uses the knowledge to determine the missing \r\ncomponent to the ancient device - Olivia.In The Day We Died, Walter explains that a time\r\nparadox is responsible for the matter at hand. At some point after May 2026, they will \r\nsend the device that destroyed one universe, and is devastating this universe, \r\ninto the Kappa radiation wormhole in Central Park and back in time 250 million years.\r\n\r\nPeter returns to this time period from 2026 and tells everyone that Walter \r\nis/was/will be \"The First People\", along with maybe Ella and Astrid.\r\n',1),(2,'Wii U','The Wii U ( /ˌwiː ˈjuː/) (ウィー ユー) is an upcoming video game console by Nintendo and will \r\nbe the successor to the Wii.[4] and The system it\'s on the way!...\r\n\r\nThe system was unveiled during Nintendo\'s press conference at the Electronic Entertainment \r\nExpo 2011 on June 7, 2011, and is expected to be released during the fourth quarter of 2012 \r\nin North America, Europe, Australia, and Japan.[5] It will be the first entry in the eighth \r\ngeneration of video game consoles.[6][7][8][9]The Wii U also features a new controller, \r\ncalled the GamePad, with an embedded touchscreen. The controller allows a player to continue\r\nplaying certain games by displaying the game even when the television is off. At the E3 2012 expo, a second controller, called the Pro Controller, was unveiled, which is a more traditional gamepad. It features two analog sticks.[10]The system will be backwards compatible with Wii, and Wii U games can support compatibility with Wii peripherals, such as the Wii Remote Plusand the Nunchuk. However, unlike the Wii, the Wii U will not be backwards compatible with Nintendo GameCube discs or peripherals,[11] although games can be purchased and downloaded from Nintendo\'s Virtual Console service.\r\n \r\nThe console was first conceived in 2008,[13] after Nintendo recognized several limitations and challenges with the Wii, such as the general public perception that the system catered primarily for a \"casual\" audience.[14] With Wii U, Nintendo explicitly wishes to lure \"core\" gamers back.[15]Game designer Shigeru Miyamoto admitted that the lack of HD and limited network infrastructure for the Wii also contributed to the system being regarded in a separate class to its competitors\' systems, the Xbox 360 and PlayStation 3.[16] It was decided that a new console would have to be made to accommodate significant structural changes.Within the company, there was much debate over the idea for the new console, and the project was scrapped and restarted several times.[17] The concept of a touchscreen embedded within the controller was originally inspired by the blue light on the Wii that illuminates to indicate new messages.[18] Miyamoto and his team wanted to include a small screen to provide game feedback and status messages to players (in similar vein to the VMU for Sega\'s Dreamcast). Much later in development, this was expanded to a full screen that could display the game being played in its entirety, a concept which was suggested but not financially viable earlier in the project.\r\n',1),(3,'another document','\r\nPlot[edit]SettingThe events of the game take place on the fictional Blood Edward island. The game\'s protagonist, Ashley Mizuki Robbins, is the daughter of Richard and Sayoko Robbins, researchers of human memory. After her parents mysteriously went missing in 1994, they were presumed dead.[3] Consequently, Ashley was raised by her father\'s younger sister, Jessica Robbins, in the suburbs of Seattle. Eleven years later, two days before her fourteenth birthday, Ashley receives a package from her father containing a birthday card and a device called a DTS. The DTS, programmed to respond only to Ashley\'s biometrics, contains a message from her father, that claims he is waiting for her on Blood Edward Island.[2][3][edit]StoryThe game\'s protagonist is Ashley Mizuki Robbins, a 13 year old girl who has been raised by her aunt, Jessica, because her parents vanished when she was three years old. She believed that they were dead, but two days before her fourteenth birthday, she received a package. Inside was a letter from her father and a small machine. The letter said that he would be waiting on Blood Edward Island, an island located off the coast of Washington in the United States. The next day, the day before her birthday, Jessica and Ashley ride over to Blood Edward Island on a boat, but Ashley\'s father isn\'t there to greet them. Jessica goes to look for him, but when she doesn\'t return, Ashley ventures out to search for both of them. While exploring the island, Ashley also befriends \"D\", a ghost who has lost his memories. Together they enter the Edward Mansion, each looking for answers to their own questions.\r\n\r\nAt the end of her summer holiday 16 year old Ashley, now an aspiring musician, receives an invitation from her father Richard, who has always been curiously distant towards his daughter. He has been absent as he took over from Ashley’s mother to work tirelessly on a memory control system known only as “Another,” the cause of her mother’s death.In an effort to cement their bond following his protracted disappearance – Richard left Ashley to be brought up by her aunt – father and daughter are to spend a weekend together at Lake Juliet, a camping resort. But, as the game begins to unfold, Ashley discovers that there are a series of mysteries surrounding Lake Juliet. The intrigue deepens as she realises she has been to this resort before … she has a dim memory of being here just before her mother died 13 years ago.[edit]GameplayPlayers must help Ashley solve a series of clues and puzzles, journeying back into her earliest memories to unlock the past.\r\n\r\nThe story is played as if in the pages of a mystery novel. Ashley must engage with the other characters to follow up the myriad different trails and clues in order to solve the mystery. Journeying through the story’s digital pages, she will encounter people who clearly know more than they are letting on about the events leading up to her mother’s death, who she will have to interrogate to glean the information she needs.Players use the Wii Remote to point and click items and characters in the game’s watercolour backgrounds, interacting with them as she travels. Ashley is guided through the game’s world by clicking the arrows that tell players where she is able to go. Ashley is assisted in her puzzle solving by her DAS, a portable device with which she can take pictures during the game.By exploring every corner of the world created by the game, collecting items from across it and by combining their intuition and skills in deduction, players will feel like they are themselves characters in a mystery novel as they unravel the secrets of Lake Juliet, Ashley’s family and ultimately, of Project Another itself.\r\n',2),(4,'Another Document',NULL,1),(5,'binary document',NULL,1),(6,'abyss truth',NULL,1);
/*!40000 ALTER TABLE `document` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'create'),(3,'delete'),(2,'update');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-08-23 11:48:58
