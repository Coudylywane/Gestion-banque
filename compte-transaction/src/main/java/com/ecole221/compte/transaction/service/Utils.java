package com.ecole221.compte.transaction.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Utils {

    public void checkSoldeIsValid(BigDecimal solde) throws Exception {
        BigDecimal minimumBalance = new BigDecimal("5000");
        int comparisonResult = solde.compareTo(minimumBalance);
        if (comparisonResult < 0) {
            throw new Exception("Solde invalide. Minimum 5000.");
        }
    }
}
