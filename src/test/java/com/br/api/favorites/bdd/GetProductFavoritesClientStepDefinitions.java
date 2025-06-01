package com.br.api.favorites.bdd;

import com.br.api.favorites.application.usecases.GetProductFavoritesClient;
import com.br.api.favorites.domain.repositories.IFavoritesRepository;
import com.br.api.favorites.domain.validators.sku.SkuMustNotExistValidator;
import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import io.cucumber.java.en.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetProductFavoritesClientStepDefinitions {

    private IFavoritesRepository favoritesRepository;
    private SkuMustNotExistValidator skuMustNotExistValidator;
    private GetProductFavoritesClient getProductFavoritesClient;
    private boolean result;

    @Given("a client with ID {string} has product {string} in favorites")
    public void a_client_has_product_in_favorites(String clientId, String sku) {
        favoritesRepository = mock(IFavoritesRepository.class);
        skuMustNotExistValidator = mock(SkuMustNotExistValidator.class);
        getProductFavoritesClient = new GetProductFavoritesClient(favoritesRepository, skuMustNotExistValidator);

        when(favoritesRepository.productExistInFavoritesByClientId(clientId, sku)).thenReturn(true);
        doNothing().when(skuMustNotExistValidator).validate(any(FavoritesFindValidationParams.class));
    }

    @Given("a client with ID {string} does not have product {string} in favorites")
    public void a_client_does_not_have_product_in_favorites(String clientId, String sku) {
        favoritesRepository = mock(IFavoritesRepository.class);
        skuMustNotExistValidator = mock(SkuMustNotExistValidator.class);
        getProductFavoritesClient = new GetProductFavoritesClient(favoritesRepository, skuMustNotExistValidator);

        when(favoritesRepository.productExistInFavoritesByClientId(clientId, sku)).thenReturn(false);
        doNothing().when(skuMustNotExistValidator).validate(any(FavoritesFindValidationParams.class));
    }

    @When("the system checks if the product is in the client's favorites")
    public void the_system_checks_product_in_favorites() {
        result = getProductFavoritesClient.call("6651f9e3d42bfe6cb3ed442a", "SKU-123");
    }

    @Then("the result should be true")
    public void the_result_should_be_true() {
        assertTrue(result);
    }

    @Then("the result should be false")
    public void the_result_should_be_false() {
        assertFalse(result);
    }
}
