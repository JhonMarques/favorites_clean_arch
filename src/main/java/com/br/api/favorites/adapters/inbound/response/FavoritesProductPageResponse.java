package com.br.api.favorites.adapters.inbound.response;

import java.util.List;

public class FavoritesProductPageResponse {

    private List<String> skus;

    public FavoritesProductPageResponse(List<String> skus) {
        this.skus = skus;
    }

}
