CREATE TABLE `street` (
   `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
   `city_id` BIGINT(20) NOT NULL,
   `name` VARCHAR(100) NOT NULL,
   `date_time_creation` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `date_time_change` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
   `deleted` TINYINT(4) NOT NULL DEFAULT '0',
   `date_time_deletion` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_street_city` (`city_id`),
	CONSTRAINT `FK_street_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

INSERT INTO `street` (`name`, `city_id`) VALUES ('Rua A', 4330);
INSERT INTO `street` (`name`, `city_id`) VALUES ('Rua B', 4330);
INSERT INTO `street` (`name`, `city_id`) VALUES ('Rua C', 4330);
INSERT INTO `street` (`name`, `city_id`) VALUES ('Rua D', 4330);
INSERT INTO `street` (`name`, `city_id`) VALUES ('Rua E', 4330);
INSERT INTO `street` (`name`, `city_id`) VALUES ('Rua F', 4330);
INSERT INTO `street` (`name`, `city_id`) VALUES ('Rua G', 4330);
INSERT INTO `street` (`name`, `city_id`) VALUES ('Rua H', 4330);


CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `street_view` AS SELECT * FROM street WHERE deleted = false ;