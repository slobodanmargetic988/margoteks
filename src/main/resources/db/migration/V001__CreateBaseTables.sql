create table `margotekstil`.`users` (
`id` int NOT NULL AUTO_INCREMENT,
`ime` varchar(255),
`prezime` varchar(255),
`adresa` varchar(255),
`broj_telefona` varchar(255),
`postanski_broj` varchar(255),
`mesto` varchar(255),
`drzava` varchar(255),
`email` varchar(255),
`lozinka` varchar(255),
`role` varchar(255),
PRIMARY KEY (id)
)ENGINE=InnoDB;


create table `margotekstil`.`korpa` (
`id` int NOT NULL AUTO_INCREMENT,
`user_id` int not null,
primary key (`id`),
foreign key(`user_id`) references `users`(`id`)
)ENGINE=InnoDB;


alter table `margotekstil`.`users` add column `korpa_id` int default null;
alter table `margotekstil`.`users` add foreign key (`korpa_id`) references `korpa`(id);

create table `margotekstil`.`proizvodi` (
`id` int NOT NULL AUTO_INCREMENT,
`ime` varchar(255),
`cena` double,
`opis` varchar(1255),
`pdv` double,
`keywords` varchar(255),
`kategorija` varchar(255),
`kilaza` double default 0,
`dimenzije` varchar(255),
`active` BOOLEAN default true,
PRIMARY KEY (`id`)
)ENGINE=InnoDB;

create table `margotekstil`.`photo` (
`id` int NOT NULL AUTO_INCREMENT,
`title` varchar(255),
`file_name` varchar(255),
`proizvod_id` int,
`active` BOOLEAN default true,
`glavna_slika` int default null,
primary key (`id`),
foreign key (`proizvod_id`) references `proizvodi`(`id`),
foreign key (`glavna_slika`) references `proizvodi`(`id`)
)ENGINE=InnoDB;

alter table `margotekstil`.`proizvodi` add column `glavna_slika` int default null;
alter table `margotekstil`.`proizvodi` add foreign key (`glavna_slika`) references `photo`(`id`);



create table `margotekstil`.`slicni_proizvodi` (
`id` int NOT NULL AUTO_INCREMENT,
`proizvod_id` int not null,
`slicni_proizvod_id` int not null,
primary key (`id`),
foreign key(`proizvod_id`) references `proizvodi`(`id`), 
foreign key(`slicni_proizvod_id`) references `proizvodi`(`id`)
)ENGINE=InnoDB;



create table `margotekstil`.`korpa_proizvodi` (
`id` int NOT NULL AUTO_INCREMENT,
`korpa_id` int not null,
`proizvod_id` int not null,
`kolicina` int not null,
primary key (`id`),
foreign key(`proizvod_id`) references `proizvodi`(`id`),
foreign key(`korpa_id`) references `korpa`(`id`)
)ENGINE=InnoDB;


create table `margotekstil`.`zavrsene_porudzbine` (
`id` int NOT NULL AUTO_INCREMENT,
`user_id` int not null,
`korpa_id` int not null,
`ime` varchar(255),
`prezime` varchar(255),
`telefon` int,
`email` varchar(255),
`adresa` varchar(255),
`postanski_broj` varchar(255),
`grad` varchar(255),
`napomena` varchar(1255),
`nacin_placanja` varchar(255),
primary key (`id`),
foreign key(`user_id`) references `users`(`id`),
foreign key(`korpa_id`) references `korpa`(`id`)
)ENGINE=InnoDB;


create table `margotekstil`.`reset_tokeni` (
`id` int NOT NULL AUTO_INCREMENT,
`vrednost` varchar(255),
`email` varchar(255),
primary key (`id`)
)ENGINE=InnoDB;

