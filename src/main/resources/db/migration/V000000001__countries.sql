ALTER DATABASE `core-address` CHARSET = Latin1 COLLATE = latin1_swedish_ci;

CREATE TABLE `country` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(100) NOT NULL,
	`acronym` VARCHAR(10) NOT NULL,
	`bacen` VARCHAR(10) NOT NULL,
	`date_time_creation` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`date_time_change` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`deleted` TINYINT(4) NOT NULL DEFAULT '0',
	`date_time_deletion` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;

INSERT INTO `country` (`name`, `acronym`, `bacen`)VALUES ('Brasil', 'BR', '1058');
INSERT INTO `country` (`name`, `acronym`, `bacen`)VALUES ('Uruguai', 'UY', '8451');
INSERT INTO `country` (`name`, `acronym`, `bacen`)VALUES ('Argentina', 'AR', '0639');
INSERT INTO `country` (`name`, `acronym`, `bacen`)VALUES ('Paraguai', 'PY', '5860');
INSERT INTO `country` (`name`, `acronym`, `bacen`)VALUES ('Chile', 'CL', '1589');
INSERT INTO `country` (`name`, `acronym`, `bacen`)VALUES ('Bolívia', 'BO', '0973');
INSERT INTO `country` (`name`, `acronym`, `bacen`)VALUES ('Perú', 'PE', '5894');


CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `country_view` AS SELECT * FROM country WHERE deleted = false ;