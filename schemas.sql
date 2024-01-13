create database vente_vehicule;

create table categorie(
    id_categorie serial primary key,
    nom_categorie varchar(100) not null,
    etat int not null
);
drop table categorie;
insert into categorie(nom_categorie,etat) values('SUV',0);
insert into categorie(nom_categorie,etat) values('Berline',0);
insert into categorie(nom_categorie,etat) values('Citadine',0);
insert into categorie(nom_categorie,etat) values('Monospace',0);
insert into categorie(nom_categorie,etat) values('Coupé',0);
insert into categorie(nom_categorie,etat) values('Cabriolet',0);
insert into categorie(nom_categorie,etat) values('Pickup',0);
insert into categorie(nom_categorie,etat) values('Utilitaire',0);
insert into categorie(nom_categorie,etat) values('4x4',0);
insert into categorie(nom_categorie,etat) values('Camion',0);
insert into categorie(nom_categorie,etat) values('Bus',0);
insert into categorie(nom_categorie,etat) values('Camionnette',0);
insert into categorie(nom_categorie,etat) values('Remorque',0);
insert into categorie(nom_categorie,etat) values('Tracteur',0);
insert into categorie(nom_categorie,etat) values('Engin',0);

create table marque(
    id_marque serial primary key,
    nom_marque varchar(100) not null,
    etat int not null
);
drop table marque;
insert into marque(nom_marque,etat) values('Mercedes',0);
insert into marque(nom_marque,etat) values('BMW',0);
insert into marque(nom_marque,etat) values('Audi',0);
insert into marque(nom_marque,etat) values('Volkswagen',0);
insert into marque(nom_marque,etat) values('Ford',0);
insert into marque(nom_marque,etat) values('Opel',0);
insert into marque(nom_marque,etat) values('Renault',0);
insert into marque(nom_marque,etat) values('Peugeot',0);
insert into marque(nom_marque,etat) values('Citroen',0);
insert into marque(nom_marque,etat) values('Fiat',0);
insert into marque(nom_marque,etat) values('Toyota',0);
insert into marque(nom_marque,etat) values('Nissan',0);
insert into marque(nom_marque,etat) values('Hyundai',0);
insert into marque(nom_marque,etat) values('Kia',0);
insert into marque(nom_marque,etat) values('Dacia',0);
insert into marque(nom_marque,etat) values('Seat',0);
insert into marque(nom_marque,etat) values('Skoda',0);
insert into marque(nom_marque,etat) values('Mazda',0);
insert into marque(nom_marque,etat) values('Mitsubishi',0);
insert into marque(nom_marque,etat) values('Suzuki',0);
insert into marque(nom_marque,etat) values('Honda',0);
insert into marque(nom_marque,etat) values('Alfa Romeo',0);
insert into marque(nom_marque,etat) values('Lancia',0);
insert into marque(nom_marque,etat) values('Land Rover',0);
insert into marque(nom_marque,etat) values('Jeep',0);
insert into marque(nom_marque,etat) values('Porsche',0);

create table ville(
    id_ville serial primary key,
    nom_ville varchar(100) not null,
    etat int not null
);
insert into ville(nom_ville,etat) values('France',0);
insert into ville(nom_ville,etat) values('Allemagne',0);
insert into ville(nom_ville,etat) values('Italie',0);
insert into ville(nom_ville,etat) values('Espagne',0);
insert into ville(nom_ville,etat) values('Portugal',0);
insert into ville(nom_ville,etat) values('Belgique',0);
insert into ville(nom_ville,etat) values('Suisse',0);
insert into ville(nom_ville,etat) values('Luxembourg',0);
insert into ville(nom_ville,etat) values('Royaume-Uni',0);
insert into ville(nom_ville,etat) values('Pays-Bas',0);
