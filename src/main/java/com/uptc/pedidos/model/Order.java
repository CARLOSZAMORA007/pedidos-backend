package com.uptc.pedidos.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Order {
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    private String drinkName;
    
    @Pattern(regexp = "small|medium|large", message = "Tamaño inválido")
    private String size;
    
    private boolean accepted;
}


