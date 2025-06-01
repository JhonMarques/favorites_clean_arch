package com.br.api.favorites.bdd;

import com.br.api.favorites.application.usecases.DeleteFavoritesProduct;
import com.br.api.favorites.domain.validators.sku.SkuMustExistValidator;
import com.br.api.favorites.infrastructure.mongo.document.FavoritesDocument;
import com.br.api.favorites.infrastructure.persistence.FavoritesDatasourceLocal;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeleteFavoritesStepDefinitions {

    private FavoritesDatasourceLocal repository;
    private SkuMustExistValidator validator;
    private DeleteFavoritesProduct deleteFavoritesProduct;
    private FavoritesDocument document;
    private Exception caughtException;
    private String clientId;

    @Given("a client with ID {string} has favorites including {string}")
    public void a_client_with_id_has_favorites_including(String clientId, String sku) {
        this.clientId = clientId;
        repository = mock(FavoritesDatasourceLocal.class);
        validator = mock(SkuMustExistValidator.class);
        deleteFavoritesProduct = new DeleteFavoritesProduct(repository, validator);

        document = new FavoritesDocument();
        document.setClientId(clientId);
        document.setSkus(new ArrayList<>());
        document.getSkus().add(sku);

        when(repository.findByClientId(clientId)).thenReturn(Optional.of(document));
        doNothing().when(validator).validate(any());

        doAnswer(invocation -> {
            document = invocation.getArgument(0);
            return null;
        }).when(repository).save(any());
    }

    @Given("a client with ID {string} does not exist")
    public void a_client_with_id_does_not_exist(String clientId) {
        this.clientId = clientId;
        repository = mock(FavoritesDatasourceLocal.class);
        validator = mock(SkuMustExistValidator.class);
        deleteFavoritesProduct = new DeleteFavoritesProduct(repository, validator);

        when(repository.findByClientId(clientId)).thenReturn(Optional.empty());
        doNothing().when(validator).validate(any());
    }

    @When("the client deletes SKU {string} from favorites")
    public void the_client_deletes_sku_from_favorites(String sku) {
        try {
            deleteFavoritesProduct.call(clientId, sku);
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @Then("the favorites list should not contain {string}")
    public void the_favorites_list_should_not_contain(String sku) {
        assertNotNull(document, "Document should not be null");
        assertFalse(document.getSkus().contains(sku), "SKU should have been removed from the list");
    }

    @Then("an error should be thrown")
    public void an_error_should_be_thrown() {
        assertNotNull(caughtException, "Expected an exception to be thrown");
        assertTrue(caughtException instanceof IllegalArgumentException, "Expected IllegalArgumentException");
    }
}
