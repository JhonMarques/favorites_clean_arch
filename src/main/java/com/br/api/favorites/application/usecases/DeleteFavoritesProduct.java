package com.br.api.favorites.application.usecases;
import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import com.br.api.favorites.domain.validators.sku.SkuMustExistValidator;
import com.br.api.favorites.infrastructure.mongo.document.FavoritesDocument;
import com.br.api.favorites.infrastructure.persistence.FavoritesDatasourceLocal;
import org.springframework.stereotype.Service;

@Service
public class DeleteFavoritesProduct {

    private final FavoritesDatasourceLocal favoritesRepository;
    private final SkuMustExistValidator skuMustExistValidator;


    public DeleteFavoritesProduct(
            FavoritesDatasourceLocal favoritesRepository, SkuMustExistValidator skuMustExistValidator) {
        this.favoritesRepository = favoritesRepository;
        this.skuMustExistValidator = skuMustExistValidator;
    }

    public void call(String clientId, String sku) {
        skuMustExistValidator.validate(new FavoritesFindValidationParams(clientId, sku));

        FavoritesDocument doc = favoritesRepository.findByClientId(clientId)
                .orElseThrow(() -> new IllegalArgumentException("ClientId not Found"));

        doc.getSkus().remove(sku);
        favoritesRepository.save(doc);
    }



}
