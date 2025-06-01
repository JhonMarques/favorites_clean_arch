package com.br.api.favorites.adapters.inbound.response;

import java.util.List;

public class FavoritesResponse {

    private String clientId;

    public FavoritesResponse(String clientId, List<String> skus) {
        this.clientId = clientId;
    }
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
