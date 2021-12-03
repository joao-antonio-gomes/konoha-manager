package br.com.konoha.model.transport;

import java.math.BigDecimal;

public class VillagerDTO {
    private String name;
    private String surname;
    private Integer age;
    private Double cost;
    private Integer id;

    public VillagerDTO(String name, String surname, Integer age, Double cost) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
