package com.br.api.favorites.domain.repositories;

import com.br.api.favorites.domain.entities.FavoritesPage;
import com.br.api.favorites.domain.entities.FavoritesProduct;

import java.util.List;

public interface IFavoritesRepository {
    boolean productExistInFavoritesByClientId(String clientId, String sku);

}
