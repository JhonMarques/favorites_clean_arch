package com.br.api.favorites.domain.validators.sku;

import com.br.api.favorites.adapters.outbound.mongo.projection.FavoritesGroupedProjectionDto;
import com.br.api.favorites.domain.exceptions.SkuAlreadyInFavoritesException;
import com.br.api.favorites.domain.repositories.IFavoritesAggregationRepository;
import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class SkuMustNotExistValidator implements IFavoritesFindValidation {

    private final IFavoritesAggregationRepository aggregationRepository;

    public SkuMustNotExistValidator(IFavoritesAggregationRepository aggregationRepository) {
        this.aggregationRepository = aggregationRepository;
    }

    @Override
    public void validate(FavoritesFindValidationParams params) {

        List<FavoritesGroupedProjectionDto> grouped = aggregationRepository.findGroupedByClientId(params.getClientId());

        boolean skuExists = grouped.stream()
                .flatMap(dto -> dto.getSkus() != null ? dto.getSkus().stream() : Stream.empty())
                .anyMatch(sku -> sku.equals(params.getSku()));

        if (skuExists) {
            throw new SkuAlreadyInFavoritesException("This SKU is already in the list.");
        }
    }
}

