package com.example.alphafeigncurrency.feign.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Original {
    @JsonProperty("height")
    private String height;
    @JsonProperty("width")
    private String width;
    @JsonProperty("size")
    private String size;
    @JsonProperty("url")
    private String url;
    @JsonProperty("mp4_size")
    private String mp4Size;
    @JsonProperty("mp4")
    private String mp4;
    @JsonProperty("webp_size")
    private String webpSize;
    @JsonProperty("webp")
    private String webp;
    @JsonProperty("frames")
    private String frames;
    @JsonProperty("hash")
    private String hash;
}
