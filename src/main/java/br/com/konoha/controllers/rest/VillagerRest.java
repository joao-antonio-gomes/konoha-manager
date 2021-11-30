package br.com.konoha.controllers.rest;

import br.com.konoha.model.transport.VillagerDTO;
import br.com.konoha.controllers.service.VillagerService;
import br.com.konoha.exceptions.VillagerException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/villagers")
public class VillagerRest {

    private final VillagerService villagerService;

    public VillagerRest(VillagerService villagerService) {
        this.villagerService = villagerService;
    }

    @GetMapping
    public List<VillagerDTO> listAllVillagers() {
        return villagerService.listAllVillagers();
    }

    @GetMapping("/{id}")
    public VillagerDTO getVillagerById(@PathVariable("id") long id) {
        try {
            BigInteger bigId = BigInteger.valueOf(id);
            return villagerService.getVillagerById(bigId);
        } catch (VillagerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/total-cost")
    public String totalCost() {
        BigDecimal totalCost = villagerService.totalCost();
        return String.format("R$ %.2f", totalCost);
    }

    @GetMapping("/search")
    public VillagerDTO searchVillagerByName(@RequestParam("name") String name) {
        try {
            return villagerService.getVillagerByName(name);
        } catch (VillagerException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/financial-report")
    public Map<String, String> getFinancialReport() {
        return villagerService.getFinancialReport();
    }
}
