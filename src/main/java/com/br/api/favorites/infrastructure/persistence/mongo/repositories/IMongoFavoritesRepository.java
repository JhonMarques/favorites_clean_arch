package com.br.api.favorites.infrastructure.persistence.mongo.repositories;

import com.br.api.favorites.infrastructure.mongo.document.FavoritesDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IMongoFavoritesRepository extends MongoRepository<FavoritesDocument, String> {

	Optional<FavoritesDocument> findByClientId(String clientId);

	boolean existsByClientId(String clientId);

	boolean existsByClientIdAndSkusContains(String clientId, String sku);


}
