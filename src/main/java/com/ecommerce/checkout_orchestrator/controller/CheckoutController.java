package com.ecommerce.checkout_orchestrator.controller;

import com.ecommerce.checkout_orchestrator.dto.CheckoutRequestDTO;
import com.ecommerce.checkout_orchestrator.dto.CheckoutResponseDTO;
import com.ecommerce.checkout_orchestrator.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<CheckoutResponseDTO> realizarCompra(@Valid @RequestBody CheckoutRequestDTO request) {
        CheckoutResponseDTO resultado = checkoutService.procesarCheckout(request);
        return ResponseEntity.ok(resultado);
    }
}