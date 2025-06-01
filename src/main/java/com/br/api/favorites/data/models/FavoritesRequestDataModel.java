package com.br.api.favorites.data.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class FavoritesRequestDataModel {

    @NotBlank(message = "ClientId is required")
    @Size(min = 24, max = 24, message = "The size of clientId should be 24 characters")
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "clientId must be a valid 24-character ObjectId")
    private String clientId;

    @Size(min = 1, max = 20, message = "You must provide between 1 and 20 SKUs")
    private List<
            @NotBlank(message = "SKU is required")
            @Size(min = 24, max = 24, message = "Each SKU must have exactly 24 characters")
            @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "Each SKU must be a valid 24-character ObjectId")
                    String
            > skus;

    public FavoritesRequestDataModel() {
    }

    public FavoritesRequestDataModel(String clientId, List<String> skus) {
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
