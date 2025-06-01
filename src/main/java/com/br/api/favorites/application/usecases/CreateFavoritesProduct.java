    package com.br.api.favorites.application.usecases;
    import com.br.api.favorites.domain.entities.Favorites;
    import com.br.api.favorites.domain.validators.add.FavoritesAddValidatorComposite;
    import com.br.api.favorites.infrastructure.mongo.document.FavoritesDocument;
    import com.br.api.favorites.infrastructure.persistence.FavoritesDatasourceLocal;
    import org.springframework.stereotype.Service;

    import java.util.ArrayList;

    @Service
    public class CreateFavoritesProduct {

        private final FavoritesDatasourceLocal favoritesRepository;
        private final FavoritesAddValidatorComposite addValidator;

        public CreateFavoritesProduct(FavoritesDatasourceLocal favoritesRepository, FavoritesAddValidatorComposite addValidator) {
            this.favoritesRepository = favoritesRepository;
            this.addValidator = addValidator;
        }


        public void call(Favorites favorites) {
            addValidator.validate(favorites);

            FavoritesDocument doc = favoritesRepository.findByClientId(favorites.getClientId())
                    .orElseGet(() -> {
                        FavoritesDocument newDoc = new FavoritesDocument();
                        newDoc.setClientId(favorites.getClientId());
                        newDoc.setSkus(new ArrayList<>());
                        return newDoc;
                    });

            doc.getSkus().add(favorites.getSku());
            favoritesRepository.save(doc);
        }
    }


