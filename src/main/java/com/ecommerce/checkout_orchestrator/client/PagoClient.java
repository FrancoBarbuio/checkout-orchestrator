package com.ecommerce.checkout_orchestrator.client;

import com.ecommerce.checkout_orchestrator.dto.PagoRequestDTO;
import com.ecommerce.checkout_orchestrator.dto.PagoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pago-api", url = "https://mi-servidor-de-pagos.com/api/v1")
public interface PagoClient {

    @PostMapping("/procesar")
    PagoResponseDTO procesarPago(@RequestBody PagoRequestDTO request);

}