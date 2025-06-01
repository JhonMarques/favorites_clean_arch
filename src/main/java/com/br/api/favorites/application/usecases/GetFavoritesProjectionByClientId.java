package com.br.api.favorites.application.usecases;

import com.br.api.favorites.adapters.outbound.mongo.projection.FavoritesGroupedProjectionDto;
import com.br.api.favorites.domain.repositories.IFavoritesAggregationRepository;
import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import com.br.api.favorites.domain.validators.sku.SkuMustNotExistValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetFavoritesProjectionByClientId {

    private final IFavoritesAggregationRepository aggregationRepository;
    private final SkuMustNotExistValidator skuMustNotExistValidator;

    public GetFavoritesProjectionByClientId(IFavoritesAggregationRepository aggregationRepository, SkuMustNotExistValidator skuMustNotExistValidator) {
        this.aggregationRepository = aggregationRepository;
        this.skuMustNotExistValidator = skuMustNotExistValidator;
    }

    public List<FavoritesGroupedProjectionDto> call(String clientId) {
        skuMustNotExistValidator.validate(new FavoritesFindValidationParams(clientId, null));
        return aggregationRepository.findGroupedByClientId(clientId);
    }


}

