package com.br.api.favorites.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "favorites")
public class FavoritesDocumentModel {

    @Id
    private String clientId;

    private List<String> skus;

    public FavoritesDocumentModel() {}

    public FavoritesDocumentModel(String clientId, List<String> skus) {
        this.clientId = clientId;
        this.skus = skus;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public List<String> getSkus() {
        return skus;
    }

}
