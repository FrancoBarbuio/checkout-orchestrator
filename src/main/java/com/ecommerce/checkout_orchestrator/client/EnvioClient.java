package com.ecommerce.checkout_orchestrator.client;

import com.ecommerce.checkout_orchestrator.dto.EnvioRequestDTO;
import com.ecommerce.checkout_orchestrator.dto.EnvioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "envio-api", url = "https://mi-servidor-de-logistica.com/api/v1")
public interface EnvioClient {

    @PostMapping("/coordinar")
    EnvioResponseDTO coordinarEnvio(@RequestBody EnvioRequestDTO request);

}