package com.br.api.favorites.domain.validators.params;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FavoritesFindValidationParams {
    private final String clientId;
    private final String sku;
}
