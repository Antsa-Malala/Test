psql -h monorail.proxy.rlwy.net -U postgres -d railway -p 38925
C1D4fG-bd2eCa6436f*B4AcEa6g4gF1*
psql -h roundhouse.proxy.rlwy.net -U postgres -d railway -p 13900
ee-*2afFD**B4C66*-4aD1B*4C4eB42C


create table categorie(
    id_categorie serial primary key,
    nom_categorie varchar(100) not null ,
    etat_categorie int not null
);

create table marque(
    id_marque serial primary key,
    nom_marque varchar(100) not null ,
    etat_marque int not null
);

CREATE TABLE Modele(
   id_modele serial primary key,
   nom_modele VARCHAR(200) ,
   id_marque INT NOT NULL references marque(id_marque),
   id_categorie INT NOT NULL references categorie(id_categorie),
   etat_modele INT
);

CREATE OR REPLACE VIEW v_Modele as
SELECT marque.*,
categorie.*,modele.id_modele,Modele.nom_modele,modele.etat_modele
from Modele
join marque on marque.id_marque=modele.id_marque
join categorie on categorie.id_categorie=modele.id_categorie;

create table energie(
    id_energie serial primary key,
    nom_energie varchar(100) not null ,
    etat_energie int not null
);

create table couleur(
    id_couleur serial primary key,
    nom_couleur varchar(100) not null ,
    etat_couleur int not null
);


create table ville(
    id_ville serial primary key,
    nom_ville varchar(100) not null ,
    etat_ville int not null
);


create table boite(
    id_boite serial primary key,
    nom_boite varchar(100) not null ,
    etat_boite int not null
);

CREATE TABLE Porte(
   id_porte serial,
   valeur INT NOT NULL ,
   etat_porte INT,
   PRIMARY KEY(id_porte)
);

CREATE TABLE Place(
   id_place serial,
   valeur INT NOT NULL ,
   etat_place INT,
   PRIMARY KEY(id_place)
);

create sequence seq_utilisateur start 1 increment 1;
create table utilisateur(
    id_utilisateur varchar(10) primary key,
    nom varchar(255) not null,
    id_ville int references ville(id_ville),
    prenom varchar(255) not null,
    adresse varchar(255) not null,
    contact varchar(20) not null,
    mail varchar(255) not null UNIQUE,
    mot_de_passe varchar(255) not null,
    role int not null,
    etat_utilisateur int
);

create or replace view v_Utilisateur as 
select utilisateur.*,ville.nom_ville,ville.etat_ville
from utilisateur 
join ville on 
utilisateur.id_ville=ville.id_ville;


create sequence seq_voiture start 1 increment 1;
create table voiture(
    id_voiture varchar(10) primary key,
    id_categorie int references categorie(id_categorie),
    id_marque int references marque(id_marque),
    id_modele int references modele(id_modele),
    id_energie int references energie(id_energie),
    id_boite int references boite(id_boite),
    consommation double precision not null,
    id_place int references place(id_place),
    id_porte int references porte(id_porte),
    kilometrage double precision not null,
    id_couleur int references couleur(id_couleur),
    etat_voiture int not null
);


create or replace view v_Voiture as 
select
voiture.*, 
categorie.nom_categorie,categorie.etat_categorie,
marque.nom_marque,marque.etat_marque,
modele.nom_modele,modele.etat_modele,
energie.nom_energie,energie. etat_energie,
boite.nom_boite,boite.etat_boite,
porte.valeur as porte_valeur, porte.etat_porte,
place.valeur as place_valeur, place.etat_place,
couleur.nom_couleur,couleur.etat_couleur
from voiture 
join categorie on categorie.id_categorie=voiture.id_categorie
join marque on marque.id_marque=voiture.id_marque
join modele on modele.id_modele=voiture.id_modele
join energie on energie.id_energie=voiture.id_energie
join boite on boite.id_boite=voiture.id_boite
join porte on porte.id_porte=voiture.id_porte
join place on place.id_place=voiture.id_place
join couleur on couleur.id_couleur=voiture.id_couleur;


create sequence seq_annonce start 1 increment 1;
create table annonce(
    id_annonce varchar(10) primary key,
    date_annonce date not null,
    prix double precision not null ,
    id_voiture varchar(10) references voiture(id_voiture),
    id_ville int references ville(id_ville),
    description text not null,
    etat_annonce int not null,
    id_utilisateur varchar(10) references utilisateur(id_utilisateur)
);

create or replace view v_Annonce as 
select annonce.id_annonce,annonce.prix,annonce.description,annonce.etat_annonce,v_voiture.*,ville.id_ville as ville_voiture,ville.nom_ville as nom_ville_voiture,ville.etat_ville as etat_ville_voiture,utilisateur.* 
from annonce
join v_Voiture on v_Voiture.id_voiture=annonce.id_voiture
join ville on ville.id_ville=annonce.id_ville
join utilisateur on utilisateur.id_utilisateur=annonce.id_utilisateur;

create or replace view v_AnnonceNonValidee as 
select * from v_Annonce where etat_annonce=0; 

create or replace view v_AnnonceNonVendue as 
select * from v_Annonce where etat_annonce=20; 

create or replace view v_AnnonceVendue as 
select * from v_Annonce where etat_annonce=30; 

create sequence s_Favoris start with 1 increment by 1;
CREATE TABLE Favoris(
   id_favoris VARCHAR(10),
   id_utilisateur VARCHAR(10) references utilisateur(id_utilisateur),
   id_annonce VARCHAR(10) references annonce(id_annonce),
   PRIMARY KEY(id_favoris)
);

create or replace view v_Favoris as 
select favoris.id_favoris,
v_Utilisateur.id_utilisateur as id_utilisateur_favoris,
v_Utilisateur.nom as nom_favoris,
v_Utilisateur.id_ville as id_ville_favoris,
v_Utilisateur.prenom as prenom_favoris,
v_Utilisateur.adresse as adresse_favoris,
v_Utilisateur.contact as contact_favoris,
v_Utilisateur.mail as mail_favoris,
v_Utilisateur.mot_de_passe as mot_de_passe_favoris,
v_utilisateur.role as role_favoris,
v_utilisateur.nom_ville as nom_ville_favoris,
v_utilisateur.etat_ville as etat_ville_favoris,
v_Annonce.*
 from favoris
 join v_Utilisateur on v_Utilisateur.id_utilisateur=favoris.id_utilisateur
 join v_Annonce on v_Annonce.id_annonce=favoris.id_annonce;

create sequence seq_vente start 1 increment 1;
create table vente(
    id_vente varchar(10) primary key,
    id_annonce varchar(10) references annonce(id_annonce) UNIQUE,
    date_vente date not null
);

create or replace view v_Vente
as select 
vente.id_vente,vente.date_vente,v_Annonce.*
from vente 
join v_Annonce on v_Annonce.id_Annonce=vente.id_Annonce;

create sequence seq_validation start 1 increment 1;
create table validation(
    id_validation varchar(10) primary key,
    date_validation date not null,
    id_utilisateur varchar(10) references utilisateur(id_utilisateur),
    id_annonce varchar(10) references annonce(id_annonce) UNIQUE
);

create sequence seq_refus start 1 increment 1;
create table refus(
    id_refus varchar(10) primary key,
    date_refus date not null,
    id_utilisateur varchar(10) references utilisateur(id_utilisateur),
    id_annonce varchar(10) references annonce(id_annonce) UNIQUE
);

create or replace view v_Validation
as select 
validation.id_validation,validation.date_validation,v_Utilisateur.*,v_Annonce
from validation 
join v_Utilisateur on v_Utilisateur.id_utilisateur=validation.id_utilisateur
join v_Annonce on v_Annonce.id_Annonce=validation.id_Annonce;

create view v_NbUtilisateur as
    select count(*) as nb_utilisateur from utilisateur where role = 0;

create or replace view v_NbPubliee as
    select count(*) as nb_publiee from annonce where etat_annonce>=20;

create or replace view v_VenduParMarque as
    select m.nom_marque , count(*) as nb_annonce from annonce join voiture v on v.id_voiture = annonce.id_voiture join marque m on m.id_marque = v.id_marque where annonce.etat_annonce >=30 group by m.nom_marque;

create or replace view v_NbVendu as
    select count(*) as nb_vendu from v_VenduParMarque;

create or replace view v_VenduByPrix as
    select a.prix from vente join annonce a on a.id_annonce = vente.id_annonce;

create or replace view v_AnnonceByMarque as
    select m.nom_marque , count(*) as nb_annonce from annonce join voiture v on v.id_voiture = annonce.id_voiture join marque m on m.id_marque = v.id_marque where annonce.etat_annonce >= 20 group by m.nom_marque;

create view v_BeneficeByMois as
    select date_part('month',v.date_vente) as mois , date_part('year',v.date_vente) as annee, sum(a.prix*0.0001) as benefice from vente v join annonce a on a.id_annonce = v.id_annonce group by date_part('month',v.date_vente), date_part('year',v.date_vente);

CREATE TABLE Commission(
   id_commission serial,
   id_annonce VARCHAR(10) references annonce(id_annonce) UNIQUE,
   date_commission DATE,
   montant double precision,
   PRIMARY KEY(id_commission)
);

create view v_Commission as 
select Commission.id_commission,Commission.date_commission,Commission.montant,v_annonce.*
from Commission
join v_annonce 
on v_annonce.id_annonce=Commission.id_annonce;

create table pourcentage
(
    id_pourcentage serial primary key,
    valeur double precision,
    date_pourcentage date
);

create or replace view v_CommissionByMois as 
select date_part('month',date_commission) as mois , date_part('year',date_commission) as annee, sum(montant) as montant from commission group by date_part('month',date_commission), date_part('year',date_commission) order by date_part('month',date_commission) asc;


CREATE OR REPLACE FUNCTION check_etat_categorie()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.etat_categorie = 10 AND EXISTS (
        SELECT 1 FROM Modele WHERE modele.id_categorie = NEW.id_categorie
        UNION
        SELECT 1 FROM voiture WHERE voiture.id_categorie = NEW.id_categorie
    ) THEN
        RAISE EXCEPTION 'Cette categorie est encore referencee dans une autre table';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update
BEFORE INSERT OR UPDATE
ON categorie
FOR EACH ROW
EXECUTE PROCEDURE check_etat_categorie();

-----------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION check_etat_marque()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.etat_marque = 10 AND EXISTS (
        SELECT 1 FROM Modele WHERE modele.id_marque = NEW.id_marque
        UNION
        SELECT 1 FROM voiture WHERE voiture.id_marque = NEW.id_marque
    ) THEN
        RAISE EXCEPTION 'Cette marque est encore referencee dans une autre table';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update
BEFORE INSERT OR UPDATE
ON marque
FOR EACH ROW
EXECUTE PROCEDURE check_etat_marque();
-----------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION check_etat_modele()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.etat_modele = 10 AND EXISTS (
        SELECT 1 FROM voiture WHERE voiture.id_modele = NEW.id_modele
    ) THEN
        RAISE EXCEPTION 'Ce modele est encore reference dans une autre table';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update
BEFORE INSERT OR UPDATE
ON modele
FOR EACH ROW
EXECUTE PROCEDURE check_etat_modele();
-------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION check_etat_energie()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.etat_energie = 10 AND EXISTS (
        SELECT 1 FROM voiture WHERE voiture.id_energie = NEW.id_energie
    ) THEN
        RAISE EXCEPTION 'Cette energie est encore reference dans une autre table';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update
BEFORE INSERT OR UPDATE
ON energie
FOR EACH ROW
EXECUTE PROCEDURE check_etat_energie();
-------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION check_etat_ville()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.etat_ville = 10 AND EXISTS (
        SELECT 1 FROM utilisateur WHERE utilisateur.id_ville = NEW.id_ville
        UNION
        SELECT 1 FROM annonce WHERE annonce.id_ville = NEW.id_ville
    ) THEN
        RAISE EXCEPTION 'Cette ville est encore reference dans une autre table';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update
BEFORE INSERT OR UPDATE
ON ville
FOR EACH ROW
EXECUTE PROCEDURE check_etat_ville();
-------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION check_etat_couleur()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.etat_couleur = 10 AND EXISTS (
         SELECT 1 FROM voiture WHERE voiture.id_couleur = NEW.id_couleur
    ) THEN
        RAISE EXCEPTION 'Cette couleur est encore reference dans une autre table';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update
BEFORE INSERT OR UPDATE
ON couleur
FOR EACH ROW
EXECUTE PROCEDURE check_etat_couleur();
-------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION check_etat_boite()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.etat_boite = 10 AND EXISTS (
         SELECT 1 FROM voiture WHERE voiture.id_boite = NEW.id_boite
    ) THEN
        RAISE EXCEPTION 'Cette boite est encore reference dans une autre table';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update
BEFORE INSERT OR UPDATE
ON boite
FOR EACH ROW
EXECUTE PROCEDURE check_etat_boite();
-------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION check_etat_porte()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.etat_porte = 10 AND EXISTS (
         SELECT 1 FROM voiture WHERE voiture.id_porte = NEW.id_porte
    ) THEN
        RAISE EXCEPTION 'Cette porte est encore reference dans une autre table';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update
BEFORE INSERT OR UPDATE
ON porte
FOR EACH ROW
EXECUTE PROCEDURE check_etat_porte();
-------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION check_etat_place()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.etat_place = 10 AND EXISTS (
         SELECT 1 FROM voiture WHERE voiture.id_place = NEW.id_place
    ) THEN
        RAISE EXCEPTION 'Cette place est encore reference dans une autre table';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_insert_update
BEFORE INSERT OR UPDATE
ON place
FOR EACH ROW
EXECUTE PROCEDURE check_etat_place();
-------------------------------------------------------------------------------------------------------------------------