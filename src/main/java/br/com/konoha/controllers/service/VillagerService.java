package br.com.konoha.controllers.service;

import br.com.konoha.model.transport.VillagerDTO;
import br.com.konoha.config.KonohaConfiguration;
import br.com.konoha.exceptions.VillagerException;
import br.com.konoha.model.dao.VillagerDAO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
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
        return villagerDAO.listAllVillagers();
    }

    public BigDecimal totalCost() {
        List<BigDecimal> costOfEachVillager = new ArrayList<>();
        for (VillagerDTO villager : villagerDAO.listAllVillagers()) {
            costOfEachVillager.add(villager.getCost());
        }
        return costOfEachVillager.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, String> getFinancialReport() {
        HashMap<String, String> financialReport = new HashMap<>();
        BigDecimal totalCost = this.totalCost();
        BigDecimal budget = new KonohaConfiguration().getBudget();
        BigDecimal remainingBudget = budget.subtract(totalCost);
        VillagerDTO moreExpensiveVillager = this.getMoreExpensiveVillager();

        financialReport.put("totalCost", totalCost.toString());
        financialReport.put("budget", budget.toString());
        financialReport.put("remainingBudget", remainingBudget.toString());
        financialReport.put("moreExpensiveVillager", moreExpensiveVillager.getName());
        return financialReport;
    }

    public VillagerDTO getVillagerById(BigInteger id) throws VillagerException {
        List<VillagerDTO> allVillagers = listAllVillagers();
        VillagerDTO villagerFound = allVillagers.stream().filter(villager -> villager.getId().equals(id)).findFirst().orElse(null);
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
        VillagerDTO villagerFound = allVillagers.stream().max((villager1, villager2) -> villager1.getCost().compareTo(villager2.getCost())).orElse(null);
        return villagerFound;
    }
}
