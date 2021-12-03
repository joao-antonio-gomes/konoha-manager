package br.com.konoha.config;

import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


@Configuration
public class KonohaConfiguration {

    private static Double budget = 5000.0;

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        KonohaConfiguration.budget = budget;
    }
}
