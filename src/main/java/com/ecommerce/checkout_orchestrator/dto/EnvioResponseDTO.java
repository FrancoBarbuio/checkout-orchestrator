package com.ecommerce.checkout_orchestrator.dto;

public record EnvioResponseDTO(
        String codigoSeguimiento,
        String fechaEstimadaEntrega,
        String estadoEnvio
) {}