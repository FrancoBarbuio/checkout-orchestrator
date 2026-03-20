package com.ecommerce.checkout_orchestrator.dto;

public record PagoResponseDTO(
        String idTransaccion,
        String estadoPago
) {}