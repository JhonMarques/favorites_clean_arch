package com.br.api.favorites.domain.validators.add;

import com.br.api.favorites.domain.entities.Favorites;
import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import com.br.api.favorites.domain.validators.sku.FavoritesSkuLimitValidator;
import com.br.api.favorites.domain.validators.sku.SkuMustExistValidator;
import com.br.api.favorites.domain.validators.sku.SkuMustNotExistValidator;
import org.springframework.stereotype.Component;

@Component
public class FavoritesAddValidatorComposite {

    private final SkuMustNotExistValidator skuMustNotExistValidator;
    private final FavoritesSkuLimitValidator favoritesSkuLimitValidator;

    public FavoritesAddValidatorComposite(SkuMustNotExistValidator skuMustNotExistValidator, FavoritesSkuLimitValidator favoritesSkuLimitValidator) {
        this.skuMustNotExistValidator = skuMustNotExistValidator;
        this.favoritesSkuLimitValidator = favoritesSkuLimitValidator;
    }


    public void validate(Favorites favorites) {
        var params = new FavoritesFindValidationParams(favorites.getClientId(), favorites.getSku());

        skuMustNotExistValidator.validate(params);
        favoritesSkuLimitValidator.validate(params);
    }
}
