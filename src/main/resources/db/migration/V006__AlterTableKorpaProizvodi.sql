

alter table `margotekstil`.`korpa_proizvodi` add column `boja` int default null;


alter table `margotekstil`.`korpa_proizvodi` add foreign key (`boja`) references `color_paleta`(id);