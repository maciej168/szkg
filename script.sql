CREATE TABLE `Games` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;
commit;

CREATE TABLE `Categories` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;
commit;

CREATE TABLE `GameCategories` (
  `game_id` int(5) unsigned NOT NULL,
  `category_id` int(5) unsigned NOT NULL,
  PRIMARY KEY (`game_id`,`category_id`),
  CONSTRAINT `FK_CATEGORY_ID` FOREIGN KEY (`category_id`) REFERENCES `Categories` (`id`),
  CONSTRAINT `FK_GAME_ID` FOREIGN KEY (`game_id`) REFERENCES `Games` (`id`)
) DEFAULT CHARSET=utf8;
commit;
