CREATE TABLE `customer` (
    `id` mediumint(8) unsigned NOT NULL auto_increment,
    `firstName` varchar(255) DEFAULT NULL,
    `lastName` varchar(255) DEFAULT NULL,
    `birthDate` varchar(255),
    PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;