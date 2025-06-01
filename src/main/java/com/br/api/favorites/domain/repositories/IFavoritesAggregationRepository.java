package com.br.api.favorites.domain.repositories;


import com.br.api.favorites.adapters.outbound.mongo.projection.FavoritesGroupedProjectionDto;

import java.util.List;

public interface IFavoritesAggregationRepository {
    List<FavoritesGroupedProjectionDto> findGroupedByClientId(String clientId);
}
