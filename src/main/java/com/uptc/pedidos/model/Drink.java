package com.uptc.pedidos.model;

import lombok.Data;

@Data
public class Drink {
    private String name;
    private String availableSizes;

    public boolean hasSize(String requestedSize) {
        if (availableSizes == null) return false;
        String[] sizes = availableSizes.split(",");
        for (String s : sizes) {
            if (s.trim().equalsIgnoreCase(requestedSize)) {
                return true;
            }
        }
        return false;
    }
}
