package com.br.api.favorites.adapters.outbound.mongo.projection;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FavoritesGroupedProjectionDto {
    private String clientId;
    private List<String> skus;

    public FavoritesGroupedProjectionDto(String clientId, List<String> skus) {
        this.clientId = clientId;
        this.skus = skus;
    }

}
