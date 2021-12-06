package br.com.konoha.controllers.service;

import br.com.konoha.model.transport.VillagerDTO;
import br.com.konoha.config.KonohaConfiguration;
import br.com.konoha.exceptions.VillagerException;
import br.com.konoha.model.dao.VillagerDAO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VillagerService {

    private final VillagerDAO villagerDAO;

    public VillagerService(VillagerDAO villagerDAO) {
        this.villagerDAO = villagerDAO;
    }

    public List<VillagerDTO> listAllVillagers() {
        try {
            return villagerDAO.listAllVillagers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public double totalCost() {
        try {
            return this.villagerDAO.totalCost();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public Map<String, String> getFinancialReport() {
        HashMap<String, String> financialReport = new HashMap<>();
        Double totalCost = this.totalCost();
        Double budget = new KonohaConfiguration().getBudget();
        double remainingBudget = budget - totalCost;
        VillagerDTO moreExpensiveVillager = this.getMoreExpensiveVillager();

        financialReport.put("totalCost", totalCost.toString());
        financialReport.put("budget", budget.toString());
        financialReport.put("remainingBudget", Double.toString(remainingBudget));
        financialReport.put("moreExpensiveVillager", moreExpensiveVillager.getName());
        return financialReport;
    }

    public VillagerDTO getVillagerById(BigInteger id) throws VillagerException, SQLException {
        VillagerDTO villagerFound = this.villagerDAO.getVillagerById(id);
        if (villagerFound == null) {
            throw new VillagerException("Villager not found");
        }
        return villagerFound;
    }

    public VillagerDTO getVillagerByName(String name) throws VillagerException {
        List<VillagerDTO> allVillagers = listAllVillagers();
        VillagerDTO villagerFound = allVillagers.stream().filter(villager -> villager.getName().toLowerCase().equals(name.toLowerCase())).findFirst().orElse(null);
        if (villagerFound == null) {
            throw new VillagerException("Villager not found");
        }
        return villagerFound;
    }

    public VillagerDTO getMoreExpensiveVillager() {
        List<VillagerDTO> allVillagers = listAllVillagers();
        return allVillagers.stream().max((villager1, villager2) -> villager1.getCost(). compareTo(villager2.getCost())).orElse(null);
    }

    public VillagerDTO create(VillagerDTO villager) throws SQLException {
        if (villager == null) {
            throw new SQLException("Error creating villager");
        }
        return this.villagerDAO.create(villager);
    }
}
