create table `margotekstil`.`color_paleta` (
`id` int NOT NULL AUTO_INCREMENT,
`proizvod_id` int not null,
`title` varchar(255),
`alt_text` varchar(255),
`active` BOOLEAN default true,
`file_name` varchar(255),
PRIMARY KEY (id)
)ENGINE=InnoDB;
