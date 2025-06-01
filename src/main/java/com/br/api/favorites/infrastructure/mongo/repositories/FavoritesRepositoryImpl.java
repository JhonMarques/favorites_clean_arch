package com.br.api.favorites.infrastructure.mongo.repositories;

import com.br.api.favorites.adapters.outbound.mappers.FavoritesMapper;
import com.br.api.favorites.domain.repositories.IFavoritesDatasourceLocal;
import com.br.api.favorites.domain.repositories.IFavoritesRepository;
import org.springframework.stereotype.Component;


@Component
public class FavoritesRepositoryImpl implements IFavoritesRepository {

	private final IFavoritesDatasourceLocal datasource;
	private final FavoritesMapper favoritesMapper;

	public FavoritesRepositoryImpl(
			IFavoritesDatasourceLocal datasource,
			FavoritesMapper favoritesMapper
	) {
		this.datasource = datasource;
		this.favoritesMapper = favoritesMapper;
	}

	@Override
	public boolean productExistInFavoritesByClientId(String clientId, String sku) {
		return datasource.productExistInFavoritesByClientId(clientId, sku);
	}


}
