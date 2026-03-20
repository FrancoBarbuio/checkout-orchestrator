package com.ecommerce.checkout_orchestrator.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CheckoutResponseDTO(
        String mensajePrincipal,
        String idTransaccion,
        String estadoPago,
        String codigoSeguimiento,
        String fechaEstimada,
        String errorDetalle
) {}