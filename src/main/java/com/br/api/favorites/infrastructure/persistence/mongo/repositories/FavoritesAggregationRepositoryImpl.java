package com.br.api.favorites.infrastructure.persistence.mongo.repositories;

import com.br.api.favorites.adapters.outbound.mongo.projection.FavoritesGroupedProjectionDto;
import com.br.api.favorites.domain.repositories.IFavoritesAggregationRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoritesAggregationRepositoryImpl implements IFavoritesAggregationRepository {

    private final MongoTemplate mongoTemplate;

    public FavoritesAggregationRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<FavoritesGroupedProjectionDto> findGroupedByClientId(String clientId) {
        MatchOperation match = Aggregation.match(Criteria.where("clientId").is(clientId));
        Aggregation aggregation = Aggregation.newAggregation(match);
        AggregationResults<FavoritesGroupedProjectionDto> results =
                mongoTemplate.aggregate(aggregation, "favorites", FavoritesGroupedProjectionDto.class);
        return results.getMappedResults();
    }
}
