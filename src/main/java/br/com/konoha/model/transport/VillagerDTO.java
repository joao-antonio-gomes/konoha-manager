package br.com.konoha.model.transport;

import java.math.BigDecimal;
import java.math.BigInteger;

public class VillagerDTO {
    private String name;
    private String surname;
    private BigInteger age;
    private BigDecimal cost;
    private BigInteger id;

    public VillagerDTO(BigInteger id, String name, String surname, BigInteger age, BigDecimal cost) {
        this.id = id;
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

    public BigInteger getAge() {
        return age;
    }

    public void setAge(BigInteger age) {
        this.age = age;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
