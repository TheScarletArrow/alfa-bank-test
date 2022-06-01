package com.example.alphafeigncurrency.feign.FeignClients;

import com.example.alphafeigncurrency.feign.entity.ResponseGiphy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "api.giphy.com/v1/gifs/", name = "GiphyClient")
public interface GiphyClient {
    @GetMapping("/search")
    ResponseEntity<ResponseGiphy> search(@RequestParam String q, @RequestParam String api_key);
}
