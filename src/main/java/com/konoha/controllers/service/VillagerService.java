package com.konoha.controllers.service;

import com.konoha.model.dao.VillagerDAO;
import com.konoha.model.transport.VillagerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class VillagerService {

    @Autowired
    private VillagerDAO villagerDAO;

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
}
