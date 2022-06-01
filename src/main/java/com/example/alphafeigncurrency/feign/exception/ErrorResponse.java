package com.example.alphafeigncurrency.feign.exception;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, Integer status, String error, String message) {
}
