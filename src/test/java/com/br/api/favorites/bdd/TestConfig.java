package com.br.api.favorites.bdd;

import com.br.api.favorites.domain.repositories.IFavoritesRepository;
import com.br.api.favorites.domain.validators.add.FavoritesAddValidatorComposite;
import com.br.api.favorites.infrastructure.persistence.FavoritesDatasourceLocal;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public IFavoritesRepository favoritesRepository() {
        return Mockito.mock(IFavoritesRepository.class);
    }

    @Bean
    public FavoritesDatasourceLocal favoritesDatasourceLocal() {
        return Mockito.mock(FavoritesDatasourceLocal.class);
    }

    @Bean
    public FavoritesAddValidatorComposite favoritesAddValidatorComposite() {
        return Mockito.mock(FavoritesAddValidatorComposite.class);
    }

}
