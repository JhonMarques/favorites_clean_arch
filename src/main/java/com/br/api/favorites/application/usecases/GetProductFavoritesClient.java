package com.br.api.favorites.application.usecases;

import com.br.api.favorites.domain.repositories.IFavoritesRepository;
import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import com.br.api.favorites.domain.validators.sku.SkuMustNotExistValidator;
import org.springframework.stereotype.Service;

@Service
public class GetProductFavoritesClient {

    private final IFavoritesRepository favoritesRepository;
    private final SkuMustNotExistValidator skuMustNotExistValidator;

    public GetProductFavoritesClient(IFavoritesRepository favoritesRepository, SkuMustNotExistValidator skuMustNotExistValidator) {
        this.favoritesRepository = favoritesRepository;
        this.skuMustNotExistValidator = skuMustNotExistValidator;
    }


    public boolean call(String clientId, String sku) {
        skuMustNotExistValidator.validate(new FavoritesFindValidationParams(clientId, null));
        return favoritesRepository.productExistInFavoritesByClientId(clientId, sku);
    }

}
