package com.konoha.controllers.rest;

import com.konoha.controllers.service.VillagerService;
import com.konoha.model.transport.VillagerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/villagers")
public class VillagerRest {

    @Autowired
    VillagerService villagerService;

    @GetMapping("/list-all")
    public List<VillagerDTO> listAllVillagers() {
        return villagerService.listAllVillagers();
    }
}
