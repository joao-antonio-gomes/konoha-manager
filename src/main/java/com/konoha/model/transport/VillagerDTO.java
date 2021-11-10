package com.konoha.model.transport;

import java.math.BigInteger;

public class VillagerDTO {
    private String name;
    private String surname;
    private int age;
    private double cost;
    private BigInteger id;

    public VillagerDTO(String name, String surname, int age, double cost) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.cost = cost;
    }
}
