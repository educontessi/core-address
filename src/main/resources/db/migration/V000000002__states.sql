CREATE TABLE `state` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`country_id` BIGINT(20) NOT NULL,
	`name` VARCHAR(100) NOT NULL,
	`uf` VARCHAR(10) NOT NULL,
    `date_time_creation` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `date_time_change` TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `deleted` TINYINT(4) NOT NULL DEFAULT '0',
    `date_time_deletion` TIMESTAMP NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK_state_country` (`country_id`),
	CONSTRAINT `FK_state_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;


INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Acre', 'AC', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Alagoas', 'AL', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Amapá', 'AP', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Amazonas', 'AM', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Bahia', 'BA', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Ceará', 'CE', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Distrito Federal', 'DF', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Espírito Santo', 'ES', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Goiás', 'GO', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Maranhão', 'MA', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Mato Grosso', 'MT', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Mato Grosso do Sul', 'MS', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Minas Gerais', 'MG', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Pará', 'PA', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Paraíba', 'PB', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Paraná', 'PR', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Pernambuco', 'PE', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Piauí', 'PI', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Rio de Janeiro', 'RJ', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Rio Grande do Norte', 'RN', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Rio Grande do Sul', 'RS', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Rondônia', 'RO', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Roraima', 'RR', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Santa Catarina', 'SC', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('São Paulo', 'SP', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Sergipe', 'SE', '1');
INSERT INTO `state` (`name`, `uf`, `country_id`) VALUES ('Tocantins', 'TO', '1');

CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `state_view` AS SELECT * FROM state WHERE deleted = false ;
