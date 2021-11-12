package com.konoha.model.dao;

import com.konoha.model.transport.VillagerDTO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class VillagerDAO {
    public static List<VillagerDTO> villagers = Arrays.asList(
            new VillagerDTO("João", "Gomes", new BigInteger("24"), new BigDecimal(50.0)),
            new VillagerDTO("Andriele", "Portela", new BigInteger("22"), new BigDecimal(25.0)),
            new VillagerDTO("Geruza", "Prim", new BigInteger("19"), new BigDecimal(30.0))
    );


    public List<VillagerDTO> listAllVillagers() {
        return villagers;
    }
}
