package br.com.konoha.config;

import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class KonohaConfiguration {

    private static BigDecimal budget = new BigDecimal(5000);

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }
}
