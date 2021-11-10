package com.konoha.model.dao;

import com.konoha.model.transport.VillagerDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class VillagerDAO {
    public static List<VillagerDTO> villagers = Arrays.asList(
            new VillagerDTO("João", "Gomes", 24, 0.0)
    );


    public List<VillagerDTO> listAllVillagers() {
        return villagers;
    }
}
