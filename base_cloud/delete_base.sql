TRUNCATE pourcentage RESTART IDENTITY;
TRUNCATE commission RESTART IDENTITY;
SELECT setval('seq_refus', 1, false);
TRUNCATE refus;
SELECT setval('seq_validation', 1, false);
TRUNCATE validation;
SELECT setval('seq_vente', 1, false);
TRUNCATE vente;
SELECT setval('s_Favoris', 1, false);
TRUNCATE favoris;
SELECT setval('seq_annonce', 1, false);
TRUNCATE annonce cascade;
SELECT setval('seq_voiture', 1, false);
TRUNCATE voiture cascade;
SELECT setval('seq_utilisateur', 1, false);
TRUNCATE utilisateur cascade;
TRUNCATE place cascade;
SELECT setval(pg_get_serial_sequence('place', 'id_place'), 1, false);
TRUNCATE porte cascade;
SELECT setval(pg_get_serial_sequence('porte', 'id_porte'), 1, false);
TRUNCATE boite cascade;
SELECT setval(pg_get_serial_sequence('boite', 'id_boite'), 1, false);
TRUNCATE ville cascade;
SELECT setval(pg_get_serial_sequence('ville', 'id_ville'), 1, false);
TRUNCATE couleur cascade;
SELECT setval(pg_get_serial_sequence('couleur', 'id_couleur'), 1, false);
TRUNCATE energie cascade;
SELECT setval(pg_get_serial_sequence('energie', 'id_energie'), 1, false);
TRUNCATE modele cascade;
SELECT setval(pg_get_serial_sequence('modele', 'id_modele'), 1, false);
TRUNCATE marque cascade;
SELECT setval(pg_get_serial_sequence('marque', 'id_marque'), 1, false);
TRUNCATE categorie cascade;
SELECT setval(pg_get_serial_sequence('categorie', 'id_categorie'), 1, false);
