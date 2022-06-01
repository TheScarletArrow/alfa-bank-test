package com.example.alphafeigncurrency.feign.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResponseGiphy {
    List<GiphyData> data;
}
