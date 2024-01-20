package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Statistique;
import org.project.clouds5_backend.repository.StatistiqueRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatistiqueService {
    private final StatistiqueRepository statistiqueRepository;
    private final JdbcTemplate jdbcTemplate;

    public StatistiqueService(StatistiqueRepository statistiqueRepository, JdbcTemplate jdbcTemplate) {
        this.statistiqueRepository = statistiqueRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Statistique getNbUtilisateurs() {
        String sql = "select * from v_NbUtilisateur";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            Statistique statistique = new Statistique();
            statistique.setLibelle("Nombre d'utilisateurs");
            statistique.setNombre(resultSet.getDouble("nb_utilisateur"));
            return statistique;
        });
    }

    public Statistique getNbPublie() {
        String sql = "select * from v_NbPubliee";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            Statistique statistique = new Statistique();
            statistique.setLibelle("Nombre d'annonces publiées");
            statistique.setNombre(resultSet.getDouble("nb_publiee"));
            return statistique;
        });
    }

    public Statistique getNbVendu() {
        String sql = "select * from v_NbVendu";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            Statistique statistique = new Statistique();
            statistique.setLibelle("Nombre de voitures vendues");
            statistique.setNombre(resultSet.getDouble("nb_vendu"));
            return statistique;
        });
    }

    public Statistique getVenduByPrix(double prix1, double prix2) {
        String sql = "select count(*) as nb_produit from v_VenduByPrix where prix between ? and ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            Statistique statistique = new Statistique();
            statistique.setLibelle("Nombre de produits vendus entre " + prix1 + " et " + prix2);
            statistique.setNombre(resultSet.getDouble("nb_produit"));
            return statistique;
        }, prix1, prix2);
    }

    public List<Statistique> getAnnonceByMarque() {
       String sql = "select * from v_AnnonceByMarque";
         return jdbcTemplate.query(sql, (resultSet, i) -> {
              Statistique statistique = new Statistique();
              statistique.setLibelle(resultSet.getString("nom_marque"));
              statistique.setNombre(resultSet.getDouble("nb_annonce"));
              return statistique;
         });
    }

    public List<Statistique> getVenduByMarque() {
        String sql = "select * from v_VenduParMarque";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Statistique statistique = new Statistique();
            statistique.setLibelle(resultSet.getString("nom_marque"));
            statistique.setNombre(resultSet.getDouble("nb_annonce"));
            return statistique;
        });
    }


    public Statistique getBeneficeByMois(int mois, int annee) {
        String sql = "select * from v_BeneficeByMois where mois = ? and annee = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            Statistique statistique = new Statistique();
            statistique.setLibelle("Bénéfice du mois " + mois + " de l'année " + annee);
            statistique.setNombre(resultSet.getDouble("benefice"));
            return statistique;
        }, mois, annee);
    }
}
