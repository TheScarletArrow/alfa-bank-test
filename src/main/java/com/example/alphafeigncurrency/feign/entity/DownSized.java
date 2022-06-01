package com.example.alphafeigncurrency.feign.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DownSized {
    @JsonProperty("height")
    int height;
    @JsonProperty("width")
    int width;
    @JsonProperty("size")
    int size;
    @JsonProperty("url")
    String url;
}
