package com.ecommerce.checkout_orchestrator.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CheckoutRequestDTO(

        @NotBlank(message = "El producto no puede estar vacío")
        String producto,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "Debes comprar al menos 1 artículo")
        Integer cantidad,

        @NotBlank(message = "El número de tarjeta es obligatorio")
        String numeroTarjeta,

        @NotNull(message = "El monto total es obligatorio")
        @Positive(message = "El monto total debe ser mayor a cero")
        Double montoTotal,

        @NotBlank(message = "La dirección de destino es obligatoria")
        String direccionDestino
) {}