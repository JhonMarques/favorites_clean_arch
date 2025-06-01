    package com.br.api.favorites.infrastructure.persistence;

    import com.br.api.favorites.data.models.FavoritesDocumentModel;
    import com.br.api.favorites.data.models.FavoritesPageDocumentModel;
    import com.br.api.favorites.data.models.FavoritesProductModel;
    import com.br.api.favorites.domain.repositories.IFavoritesDatasourceLocal;
    import com.br.api.favorites.infrastructure.mongo.document.FavoritesDocument;
    import com.br.api.favorites.infrastructure.persistence.mongo.repositories.IMongoFavoritesRepository;
    import org.springframework.data.mongodb.core.MongoTemplate;
    import org.springframework.data.mongodb.core.aggregation.Aggregation;
    import org.springframework.data.mongodb.core.aggregation.AggregationResults;
    import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
    import org.springframework.data.mongodb.core.query.Criteria;
    import org.springframework.data.mongodb.core.query.Query;
    import org.springframework.stereotype.Component;

    import java.util.List;
    import java.util.Optional;

    @Component
    public class FavoritesDatasourceLocal implements IFavoritesDatasourceLocal {

        private final IMongoFavoritesRepository mongoFavoritesRepository;
        private final MongoTemplate mongoTemplate;

        public FavoritesDatasourceLocal(IMongoFavoritesRepository mongoFavoritesRepository,
                                        MongoTemplate mongoTemplate) {
            this.mongoFavoritesRepository = mongoFavoritesRepository;
            this.mongoTemplate = mongoTemplate;
        }

        @Override
        public void save(FavoritesDocument favoritesDocument) {
            mongoFavoritesRepository.save(favoritesDocument);
        }


        @Override
        public Optional<FavoritesDocument> findByClientId(String clientId) {
            Query query = new Query(Criteria.where("clientId").is(clientId));
            FavoritesDocument document = mongoTemplate.findOne(query, FavoritesDocument.class, "favorites");
            return Optional.ofNullable(document);
        }


        @Override
        public boolean productExistInFavoritesByClientId(String clientId, String sku) {
            return mongoFavoritesRepository.existsByClientIdAndSkusContains(clientId, sku);
        }

    }
