package org.project.clouds5_backend.service;

import org.project.clouds5_backend.model.Commission;
import org.project.clouds5_backend.model.Statistique;
import org.project.clouds5_backend.repository.CommissionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommissionService {
    private final CommissionRepository commissionRepository;
    private final JdbcTemplate jdbcTemplate;

    public CommissionService(CommissionRepository commissionRepository, JdbcTemplate jdbcTemplate) {
        this.commissionRepository = commissionRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Commission> getAllCommissions() {
        List<Commission> commissions = commissionRepository.findAll();
        if(commissions.isEmpty()){
            return Collections.emptyList();
        }
        return commissions;
    }

    public Commission getCommissionById(Integer id) {
        Commission commission = commissionRepository.findByIdCommission(id);
        if(commission == null){
            return null;
        }
        return commission;
    }

    public Commission createCommission(Commission commission) {
        try{
            return commissionRepository.save(commission);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Commission updateCommissionById(Integer id, Commission commission) {
        Optional<Commission> optionalCommission = Optional.ofNullable(commissionRepository.findByIdCommission(id));
        if(optionalCommission.isPresent()){
            Commission commissionToUpdate = optionalCommission.get();
            commissionToUpdate.setAnnonce(commission.getAnnonce());
            commissionToUpdate.setDateCommission(commission.getDateCommission());
            commissionToUpdate.setMontant(commission.getMontant());
            commissionRepository.save(commissionToUpdate);
            return commissionToUpdate;
        }else {
            throw new RuntimeException("Commission non trouvee");
        }
    }

    public Commission deleteCommissionById(Integer id) {
        Commission commissionToDelete = commissionRepository.findByIdCommission(id);
        if(commissionToDelete != null){
            commissionRepository.delete(commissionToDelete);
            return commissionToDelete;
        }else{
            throw new RuntimeException("Favoris non trouvee");
        }
    }

    public Statistique getCommissionByMois(int mois, int annee) {
        String sql = "select * from v_CommissionByMois where mois = ? and annee = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            Statistique statistique = new Statistique();
            statistique.setLibelle("Commission du mois " + mois + " de l'année " + annee);
            statistique.setNombre(resultSet.getDouble("montant"));
            return statistique;
        }, mois, annee);
    }
}
