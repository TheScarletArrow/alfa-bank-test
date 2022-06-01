package com.example.alphafeigncurrency.feign.service;

import com.example.alphafeigncurrency.feign.FeignClients.CurrencyClient;
import com.example.alphafeigncurrency.feign.entity.ResponseGetLatestCurrency;
import com.example.alphafeigncurrency.feign.exception.NoSuchCurrencyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class CurrencyService {
    @Value("${app_id}")
    String app_id;
    private final CurrencyClient currencyClient;

    public CurrencyService(CurrencyClient currencyClient) {
        this.currencyClient = currencyClient;
    }

    public ResponseEntity<Object> getCurrencyToday(String currency) throws NoSuchCurrencyException {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String year = String.valueOf(localDate.getYear());
        String month = String.valueOf(localDate.getMonthValue());
        String day = String.valueOf(localDate.getDayOfMonth());
        String date1;
        if (month.length() == 1)
            month = "0" + month;
        if (day.length() == 1)
            day = "0" + day;
        date1 = year + "-" + month + "-" + day;

        final ResponseEntity<Object> exchangeRateByDate = currencyClient.getExchangeRateByDate(app_id, date1, currency);
        if (exchangeRateByDate.toString().indexOf("{", exchangeRateByDate.toString().indexOf("rates=")) == exchangeRateByDate.toString().indexOf("}", exchangeRateByDate.toString().indexOf("rates=")) - 1 )
            throw new NoSuchCurrencyException("No such currency");
        else return  exchangeRateByDate;
    }

    public ResponseEntity<Object> getCurrencyYesterday(String currency) throws NoSuchCurrencyException {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(1);
        String year = String.valueOf(localDate.getYear());
        String month = String.valueOf(localDate.getMonthValue());
        String day = String.valueOf(localDate.getDayOfMonth());
        String date1;
        if (month.length() == 1)
            month = "0" + month;
        if (day.length() == 1)
            day = "0" + day;
        date1 = year + "-" + month + "-" + day;
        final ResponseEntity<Object> exchangeRateByDate = currencyClient.getExchangeRateByDate(app_id, date1, currency);
        if (exchangeRateByDate.toString().indexOf("{", exchangeRateByDate.toString().indexOf("rates=")) == exchangeRateByDate.toString().indexOf("}", exchangeRateByDate.toString().indexOf("rates=")) - 1 )
            throw new NoSuchCurrencyException("No such currency");
        else return  exchangeRateByDate;
    }

    public ResponseEntity<ResponseGetLatestCurrency> getCurrency() {
        return  currencyClient.getCurrencies(app_id);
    }
}
