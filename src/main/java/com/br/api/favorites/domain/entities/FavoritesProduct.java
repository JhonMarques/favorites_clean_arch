package com.br.api.favorites.domain.entities;

public class FavoritesProduct {
    private String sku;

    public FavoritesProduct(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
}

