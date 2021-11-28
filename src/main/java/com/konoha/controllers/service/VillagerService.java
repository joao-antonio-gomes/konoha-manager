package com.konoha.controllers.service;

import com.konoha.config.KonohaConfiguration;
import com.konoha.exceptions.VillagerException;
import com.konoha.model.dao.VillagerDAO;
import com.konoha.model.transport.VillagerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

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
