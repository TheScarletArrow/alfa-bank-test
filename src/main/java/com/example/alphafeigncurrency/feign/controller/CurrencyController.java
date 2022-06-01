package com.example.alphafeigncurrency.feign.controller;

import com.example.alphafeigncurrency.feign.FeignClients.CurrencyClient;
import com.example.alphafeigncurrency.feign.FeignClients.GiphyClient;
import com.example.alphafeigncurrency.feign.entity.DownSized;
import com.example.alphafeigncurrency.feign.entity.ResponseGetLatestCurrency;
import com.example.alphafeigncurrency.feign.entity.GiphyData;
import com.example.alphafeigncurrency.feign.entity.ResponseGiphy;
import com.example.alphafeigncurrency.feign.exception.NoSuchCurrencyException;
import com.example.alphafeigncurrency.feign.service.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Random;


@RestController
@RequestMapping("/")
public class CurrencyController {
    @Value("${giphy}")
    String giphy_key;
    @Value("${currency}")
    String currency;
    public final CurrencyClient currencyClient;
    public final GiphyClient giphyClient;

    public final CurrencyService currencyService;

    public CurrencyController(CurrencyClient currencyClient, GiphyClient giphyClient, CurrencyService currencyService) {
        this.currencyClient = currencyClient;
        this.giphyClient = giphyClient;
        this.currencyService = currencyService;
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getAllCurrencies()  {

        final ResponseEntity<ResponseGetLatestCurrency> currencies = currencyService.getCurrency();

        System.out.println(currencies.getBody());
        return currencies;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<?> get() throws NoSuchCurrencyException {

        final Object bodyToday =  currencyService.getCurrencyToday(currency).getBody();


        final Object bodyYesterday = currencyService.getCurrencyYesterday(currency);


        double costToday = Double.parseDouble(bodyToday.toString().substring(bodyToday.toString().indexOf("rates="), bodyToday.toString().indexOf("}", bodyToday.toString().indexOf("rates="))).substring(11));
        double costYesterday = Double.parseDouble(bodyYesterday.toString().substring(bodyYesterday.toString().indexOf("rates="), bodyYesterday.toString().indexOf("}", bodyYesterday.toString().indexOf("rates="))).substring(11));


        if (costYesterday < costToday) {
            ResponseGiphy rich = giphyClient.search("rich", giphy_key).getBody();
            Random random = new Random();
            assert rich != null;
            final GiphyData data = rich.getData().get(random.nextInt(rich.getData().size()));
            final DownSized downSized = data.getImages().downSized;

            return ResponseEntity.ok()
                    .body(downSized.getUrl());

        } else {
            ResponseGiphy broke = giphyClient.search("broke", giphy_key).getBody();
            Random random = new Random();
            assert broke != null;
            final GiphyData data = broke.getData().get(random.nextInt(broke.getData().size()));
            final String url = data.getImages().downSized.getUrl();
            return ResponseEntity.ok()
                    .body(url);
        }
    }

}
