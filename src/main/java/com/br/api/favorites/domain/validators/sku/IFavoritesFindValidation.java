package com.br.api.favorites.domain.validators.sku;


import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import com.br.api.favorites.domain.validators.sku.SkyValidationParams;

public interface IFavoritesFindValidation {
    void validate(FavoritesFindValidationParams params);
}
