package com.br.api.favorites.domain.validators.sku;

import com.br.api.favorites.domain.entities.FavoritesProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SkyValidationParams {
    private String clientId;
    private String sku;
    private boolean existsFavorites;
    private List<FavoritesProduct> listProducts;

}
