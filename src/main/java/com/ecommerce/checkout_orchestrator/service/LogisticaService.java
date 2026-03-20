package com.ecommerce.checkout_orchestrator.service;

import com.ecommerce.checkout_orchestrator.client.EnvioClient;
import com.ecommerce.checkout_orchestrator.dto.EnvioRequestDTO;
import com.ecommerce.checkout_orchestrator.dto.EnvioResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogisticaService {

    private final EnvioClient envioClient;

    @CircuitBreaker(name = "envio-api", fallbackMethod = "logisticaFallback")
    public EnvioResponseDTO procesarEnvio(EnvioRequestDTO request) {
        System.out.println("Contactando a la API de Logística externa...");
        return envioClient.coordinarEnvio(request);
    }

    public EnvioResponseDTO logisticaFallback(EnvioRequestDTO request, Throwable t) {
        System.err.println("API de Logística caída. Motivo: " + t.getMessage());

        return new EnvioResponseDTO(
                "PENDIENTE-ASIGNACION",
                "Por confirmar (Demora en el sistema de correos)",
                "EN_ESPERA_DE_SISTEMA"
        );
    }
}