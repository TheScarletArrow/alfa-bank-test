package com.example.alphafeigncurrency.feign.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Images {
    public Original original;
    @JsonProperty("downsized")
    public DownSized downSized;
}
