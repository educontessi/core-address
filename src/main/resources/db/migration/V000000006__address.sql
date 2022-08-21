CREATE TABLE `address` (
   `id` BIGINT(19) NOT NULL AUTO_INCREMENT,
   `integration_id` VARCHAR(36) NOT NULL COLLATE 'latin1_swedish_ci',
   `country_id` BIGINT(19) NOT NULL,
   `state_id` BIGINT(19) NOT NULL,
   `city_id` BIGINT(19) NOT NULL,
   `neighborhood_id` BIGINT(19) NOT NULL,
   `street_id` BIGINT(19) NOT NULL,
   `number` VARCHAR(10) NOT NULL COLLATE 'latin1_swedish_ci',
   `recipient_name` VARCHAR(100) NOT NULL COLLATE 'latin1_swedish_ci',
   `recipient_phone` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
   `property_type` VARCHAR(50) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
   `complement` VARCHAR(100) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
   `proximity` VARCHAR(100) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
   `delivery_instructions` VARCHAR(150) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
   `zip_code` VARCHAR(50) NOT NULL COLLATE 'latin1_swedish_ci',
   `default_address` TINYINT(3) NULL DEFAULT '0',
   `date_time_creation` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `date_time_change` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
   `deleted` TINYINT(3) NOT NULL DEFAULT '0',
   `date_time_deletion` TIMESTAMP NULL DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE,
   UNIQUE INDEX `UK_address_combination` (`integration_id`, `country_id`, `state_id`, `city_id`, `neighborhood_id`, `street_id`, `number`) USING BTREE,
   INDEX `integration_id` (`integration_id`) USING BTREE,
   INDEX `FK_address_country` (`country_id`) USING BTREE,
   INDEX `FK_address_state` (`state_id`) USING BTREE,
   INDEX `FK_address_city` (`city_id`) USING BTREE,
   INDEX `FK_address_street` (`street_id`) USING BTREE,
   INDEX `FK_address_neighborhood` (`neighborhood_id`) USING BTREE,
   CONSTRAINT `FK_address_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT `FK_address_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT `FK_address_neighborhood` FOREIGN KEY (`neighborhood_id`) REFERENCES `neighborhood` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT `FK_address_state` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT `FK_address_street` FOREIGN KEY (`street_id`) REFERENCES `street` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
    COLLATE='latin1_swedish_ci'
    ENGINE=InnoDB
;

CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `address_view` AS SELECT * FROM address WHERE deleted = false ;