package com.br.api.favorites.domain.repositories;


import com.br.api.favorites.data.models.FavoritesDocumentModel;
import com.br.api.favorites.data.models.FavoritesPageDocumentModel;
import com.br.api.favorites.data.models.FavoritesProductModel;
import com.br.api.favorites.infrastructure.mongo.document.FavoritesDocument;

import java.util.List;
import java.util.Optional;

public interface IFavoritesDatasourceLocal {
	void save(FavoritesDocument favoritesDocument);

	Optional<FavoritesDocument> findByClientId(String clientId);

	boolean productExistInFavoritesByClientId(String clientId, String sku);



}
