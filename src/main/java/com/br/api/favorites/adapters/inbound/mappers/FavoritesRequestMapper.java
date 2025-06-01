package com.br.api.favorites.adapters.inbound.mappers;

import com.br.api.favorites.adapters.inbound.request.FavoritesRequest;
import com.br.api.favorites.domain.entities.Favorites;
import org.springframework.stereotype.Component;

@Component
public class FavoritesRequestMapper {

    public Favorites toDomain(FavoritesRequest request) {
        if (request == null) return null;

        Favorites favorites = new Favorites();
        favorites.setClientId(request.getClientId());

        if (request.getSku() != null && !request.getSku().isEmpty()) {
            favorites.setSku(request.getSku());
        }

        return favorites;
    }

}

