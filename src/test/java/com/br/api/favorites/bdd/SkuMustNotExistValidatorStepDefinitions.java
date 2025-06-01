package com.br.api.favorites.bdd;

import com.br.api.favorites.domain.exceptions.SkuAlreadyInFavoritesException;
import com.br.api.favorites.domain.repositories.IFavoritesAggregationRepository;
import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import com.br.api.favorites.domain.validators.sku.SkuMustNotExistValidator;
import com.br.api.favorites.adapters.outbound.mongo.projection.FavoritesGroupedProjectionDto;
import io.cucumber.java.en.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SkuMustNotExistValidatorStepDefinitions {

    private IFavoritesAggregationRepository aggregationRepository;
    private SkuMustNotExistValidator validator;
    private Exception caughtException;

    @Given("the SKU {string} already exists for client {string}")
    public void the_sku_already_exists_for_client(String sku, String clientId) {
        aggregationRepository = mock(IFavoritesAggregationRepository.class);
        validator = new SkuMustNotExistValidator(aggregationRepository);

        FavoritesGroupedProjectionDto dto = new FavoritesGroupedProjectionDto(clientId, List.of(sku));
        when(aggregationRepository.findGroupedByClientId(clientId)).thenReturn(List.of(dto));

        try {
            validator.validate(new FavoritesFindValidationParams(clientId, sku));
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @When("validating that the SKU does not exist")
    public void validating_that_the_sku_does_not_exist() {
    }

    @Then("a SkuAlreadyInFavoritesException should be thrown")
    public void a_sku_already_in_favorites_exception_should_be_thrown() {
        assertNotNull(caughtException);
        assertTrue(caughtException instanceof SkuAlreadyInFavoritesException);
    }
}
