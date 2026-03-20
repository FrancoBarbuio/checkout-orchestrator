package com.ecommerce.checkout_orchestrator.service;

import com.ecommerce.checkout_orchestrator.client.PagoClient;
import com.ecommerce.checkout_orchestrator.dto.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final PagoClient pagoClient;
    private final LogisticaService logisticaService;

    @CircuitBreaker(name = "pago-api", fallbackMethod = "pagoFallback")
    public CheckoutResponseDTO procesarCheckout(CheckoutRequestDTO request) {
        System.out.println("Iniciando checkout orquestado para: " + request.producto());

        PagoRequestDTO pagoReq = new PagoRequestDTO(request.numeroTarjeta(), request.montoTotal());
        PagoResponseDTO pagoRes = pagoClient.procesarPago(pagoReq);

        if ("APROBADO".equals(pagoRes.estadoPago())) {
            EnvioRequestDTO envioReq = new EnvioRequestDTO(request.producto(), request.cantidad(), request.direccionDestino());
            EnvioResponseDTO envioRes = logisticaService.procesarEnvio(envioReq);

            return new CheckoutResponseDTO(
                    "¡Compra y logística procesadas!",
                    pagoRes.idTransaccion(),
                    pagoRes.estadoPago(),
                    envioRes.codigoSeguimiento(),
                    envioRes.fechaEstimadaEntrega(),
                    null
            );
        }
        return new CheckoutResponseDTO("Pago rechazado.", null, "RECHAZADO", null, null, null);
    }

    public CheckoutResponseDTO pagoFallback(CheckoutRequestDTO request, Throwable t) {
        System.err.println("API de Pagos caída.");
        return new CheckoutResponseDTO(
                "Tu pedido ha sido guardado en cola. Los pagos están demorados.",
                "PENDIENTE-" + UUID.randomUUID().toString().substring(0,8),
                "PENDIENTE",
                null,
                null,
                "Intermitencia con procesador de pagos."
        );
    }
}