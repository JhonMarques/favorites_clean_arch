package com.br.api.favorites.data.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

public class FavoritesProductModel {

    @Field("sku")
    private String sku;

    public FavoritesProductModel(String sku) {
        this.sku = sku;
    }

    public FavoritesProductModel() {
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public FavoritesProductModel(ObjectId sku) {
        this.sku = sku != null ? sku.toHexString() : null;
    }

}
