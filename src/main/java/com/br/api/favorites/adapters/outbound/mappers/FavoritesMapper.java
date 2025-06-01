package com.br.api.favorites.adapters.outbound.mappers;

import com.br.api.favorites.data.models.*;
import com.br.api.favorites.domain.entities.Favorites;
import com.br.api.favorites.domain.entities.FavoritesPage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FavoritesMapper {

    public FavoritesPageDocumentModel toFavoritesPageModel(FavoritesPage page) {
        if (page == null) return null;

        FavoritesPageDocumentModel model = new FavoritesPageDocumentModel();
        model.setClientId(page.getClientId());
        model.setPage(page.getPage());
        return model;
    }

}
