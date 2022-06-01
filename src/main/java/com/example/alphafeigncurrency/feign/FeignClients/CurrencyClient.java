package com.example.alphafeigncurrency.feign.FeignClients;

import com.example.alphafeigncurrency.feign.entity.ResponseGetLatestCurrency;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(url = "openexchangerates.org/api/", name = "CurrencyClient")
public interface CurrencyClient {

    @GetMapping("/latest.json")
    ResponseEntity<ResponseGetLatestCurrency> getCurrencies(@RequestParam String app_id);


    @GetMapping("/historical/{date}.json?symbols={symbol}")
    ResponseEntity<Object> getExchangeRateByDate(@RequestParam String app_id,
                                                 @PathVariable String date,
                                                 @PathVariable String symbol);
}
