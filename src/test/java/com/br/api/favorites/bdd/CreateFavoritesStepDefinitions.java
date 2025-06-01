package com.br.api.favorites.bdd;

import com.br.api.favorites.domain.entities.Favorites;
import com.br.api.favorites.domain.validators.add.FavoritesAddValidatorComposite;
import com.br.api.favorites.infrastructure.mongo.document.FavoritesDocument;
import com.br.api.favorites.infrastructure.persistence.FavoritesDatasourceLocal;
import com.br.api.favorites.application.usecases.CreateFavoritesProduct;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateFavoritesStepDefinitions {

    private FavoritesDatasourceLocal repository;
    private FavoritesAddValidatorComposite validator;
    private CreateFavoritesProduct createFavoritesProduct;
    private FavoritesDocument document;
    private Favorites favorites;

    @Given("a client with ID {string} has no favorites")
    public void a_client_with_id_has_no_favorites(String clientId) {
        repository = mock(FavoritesDatasourceLocal.class);
        validator = mock(FavoritesAddValidatorComposite.class);
        createFavoritesProduct = new CreateFavoritesProduct(repository, validator);

        document = new FavoritesDocument();
        document.setClientId(clientId);
        document.setSkus(new ArrayList<>());

        when(repository.findByClientId(clientId)).thenReturn(Optional.empty());

        doAnswer(invocation -> {
            FavoritesDocument savedDoc = invocation.getArgument(0);
            document = savedDoc;
            return null;
        }).when(repository).save(any(FavoritesDocument.class));
    }


    @Given("a client with ID {string} already has favorites")
    public void a_client_with_id_already_has_favorites(String clientId) {
        repository = mock(FavoritesDatasourceLocal.class);
        validator = mock(FavoritesAddValidatorComposite.class);
        createFavoritesProduct = new CreateFavoritesProduct(repository, validator);

        document = new FavoritesDocument();
        document.setClientId(clientId);
        document.setSkus(new ArrayList<>());
        document.getSkus().add("EXISTING-SKU");

        when(repository.findByClientId(clientId)).thenReturn(Optional.of(document));

        doAnswer(invocation -> {
            FavoritesDocument savedDoc = invocation.getArgument(0);
            document = savedDoc;
            return null;
        }).when(repository).save(any(FavoritesDocument.class));
    }


    @When("the client adds SKU {string} to favorites")
    public void the_client_adds_sku_to_favorites(String sku) {
        favorites = new Favorites(document.getClientId(), sku);
        createFavoritesProduct.call(favorites);
    }

    @Then("the favorites list should contain {string}")
    public void the_favorites_list_should_contain(String expectedSku) {
        assertTrue(document.getSkus().contains(expectedSku));
    }

    @Then("the favorites list should include the new SKU {string}")
    public void the_favorites_list_should_include_the_new_sku(String expectedSku) {
        assertTrue(document.getSkus().contains(expectedSku));
    }


}
