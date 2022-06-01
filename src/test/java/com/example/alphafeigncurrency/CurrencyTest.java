package com.example.alphafeigncurrency;

import com.example.alphafeigncurrency.feign.entity.ResponseGetLatestCurrency;
import com.example.alphafeigncurrency.feign.exception.NoSuchCurrencyException;
import com.example.alphafeigncurrency.feign.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CurrencyTest {


    @Autowired
    private CurrencyService currencyService;

    @Value("${currency}")
    private String currency;

    @Test
    public void checkForWrongCurrency() {

        assertThrows(NoSuchCurrencyException.class, () -> currencyService.getCurrencyToday("RUR"));
    }

    @Test
    public void checkAllCurrencies() {
        ResponseGetLatestCurrency responseGetLatestCurrency = currencyService.getCurrency().getBody();
        assertNotNull(responseGetLatestCurrency);
    }

    @Test
    public void checkForRightCurrency() {
        Object bodyToday = null;
        try {
            bodyToday = currencyService.getCurrencyToday("RUB").getBody();
        } catch (NoSuchCurrencyException e) {
        } finally {
            double costToday = Double.parseDouble(bodyToday.toString().substring(bodyToday.toString().indexOf("rates="), bodyToday.toString().indexOf("}", bodyToday.toString().indexOf("rates="))).substring(11));
            assertNotEquals(costToday, 0.0);
        }
    }

    @Test
    public void checkForComparison() {
        Object today = null;
        Object yesterday = null;
        try {
            today = currencyService.getCurrencyToday(currency).getBody();

            yesterday = currencyService.getCurrencyYesterday(currency).getBody();

            assertNotEquals(today, yesterday);
        } catch (NoSuchCurrencyException e) {
        } finally {
            double costToday = Double.parseDouble(today.toString().substring(today.toString().indexOf("rates="), today.toString().indexOf("}", today.toString().indexOf("rates="))).substring(11));
            double costYesterday = Double.parseDouble(yesterday.toString().substring(yesterday.toString().indexOf("rates="), yesterday.toString().indexOf("}", yesterday.toString().indexOf("rates="))).substring(11));

            assertNotEquals(costToday, costYesterday);
        }

    }
}
