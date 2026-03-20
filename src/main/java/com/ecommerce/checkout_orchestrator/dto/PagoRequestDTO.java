package com.ecommerce.checkout_orchestrator.dto;

public record PagoRequestDTO(
        String numeroTarjeta,
        Double montoTotal
) {}