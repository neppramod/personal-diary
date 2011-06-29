
--
-- Create schema personal_blog
--

CREATE DATABASE IF NOT EXISTS personal_blog;
USE personal_blog;

--
-- Definition of table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `TITLE` varchar(255) NOT NULL,
  `DESCRIPTION` text,
  `DATE_CREATED` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


