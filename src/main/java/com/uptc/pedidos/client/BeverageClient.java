package com.uptc.pedidos.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.uptc.pedidos.model.Drink;

import java.util.List;

@Component
public class BeverageClient {
    private final RestTemplate restTemplate;
    private static final String DRINKS_API_URL = "http://3.148.205.36:8080/drinks";

    public BeverageClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean checkDrinkExists(String drinkName, String size) {
        try {
            List<Drink> drinks = getAvailableDrinks();
            return drinks.stream()
                .anyMatch(d -> d.getName().equalsIgnoreCase(drinkName)
                    && d.hasSize(size));
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    public List<Drink> getAvailableDrinks() {
        try {
            ResponseEntity<List<Drink>> response = restTemplate.exchange(
                DRINKS_API_URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); 
        }
    }
}
