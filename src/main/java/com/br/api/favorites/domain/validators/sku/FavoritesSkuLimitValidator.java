package com.br.api.favorites.domain.validators.sku;

import com.br.api.favorites.domain.repositories.IFavoritesAggregationRepository;
import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class FavoritesSkuLimitValidator implements IFavoritesFindValidation {

    private final IFavoritesAggregationRepository aggregationRepository;
    private static final int MAX_SKUS = 20;

    public FavoritesSkuLimitValidator(IFavoritesAggregationRepository aggregationRepository) {
        this.aggregationRepository = aggregationRepository;
    }

    @Override
    public void validate(FavoritesFindValidationParams params) {
        long totalSkus = aggregationRepository.findGroupedByClientId(params.getClientId()).stream()
                .flatMap(dto -> dto.getSkus() == null ? Stream.empty() : dto.getSkus().stream())
                .count();

        if (totalSkus >= MAX_SKUS) {
            throw new IllegalStateException("Cannot add more than 20 SKUs for this clientId.");
        }
    }
}

