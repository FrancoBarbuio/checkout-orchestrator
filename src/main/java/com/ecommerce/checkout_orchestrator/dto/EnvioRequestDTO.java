package com.ecommerce.checkout_orchestrator.dto;

public record EnvioRequestDTO(
        String producto,
        Integer cantidad,
        String direccionDestino
) {}