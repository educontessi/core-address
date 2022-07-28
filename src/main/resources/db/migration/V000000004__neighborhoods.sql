CREATE TABLE `neighborhood` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`city_id` BIGINT(20) NOT NULL,
	`name` VARCHAR(100) NOT NULL,
	`date_time_creation` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`date_time_change` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`deleted` TINYINT(4) NOT NULL DEFAULT '0',
	`date_time_deletion` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_neighborhood_city` (`city_id`),
	CONSTRAINT `FK_neighborhood_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

INSERT INTO `neighborhood` (`name`, `city_id`) VALUES ('Mato Alto', 4330);
INSERT INTO `neighborhood` (`name`, `city_id`) VALUES ('Cidade Alta', 4330);
INSERT INTO `neighborhood` (`name`, `city_id`) VALUES ('Centro', 4330);
INSERT INTO `neighborhood` (`name`, `city_id`) VALUES ('Caverazinho', 4330);
INSERT INTO `neighborhood` (`name`, `city_id`) VALUES ('Urussanguinha', 4330);


CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `neighborhood_view` AS SELECT * FROM neighborhood WHERE deleted = false ;