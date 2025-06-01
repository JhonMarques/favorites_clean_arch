package com.br.api.favorites.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorites {
    private String clientId;
    private String sku;
    private List<String> skus;

    public Favorites(String clientId, String sku) {
        this.clientId = clientId;
        this.sku = sku;
    }
}
