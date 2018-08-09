package service;

import client.RateClient;
import entity.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CalculationOfProfit {

    public static double getProfit(Currency to, LocalDate historicalDate, int usdAmount) {
        BigDecimal historicalRate = RateClient.getHistoricalResponse(historicalDate, to).getRates().get(to);
        BigDecimal latestRate = RateClient.getLatestResponse(to).getRates().get(to);
        BigDecimal withSpread = new BigDecimal(1.005);

        //usdAmount * (historicalRate * withSpread - latestRate )
        return new BigDecimal(usdAmount).multiply(historicalRate.multiply(withSpread).subtract(latestRate)).negate().doubleValue();
    }
}

