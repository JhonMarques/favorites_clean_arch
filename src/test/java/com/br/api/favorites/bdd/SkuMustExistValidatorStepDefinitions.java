
package com.br.api.favorites.bdd;

import com.br.api.favorites.domain.exceptions.SkuNotFoundInFavoritesException;
import com.br.api.favorites.domain.repositories.IFavoritesAggregationRepository;
import com.br.api.favorites.domain.validators.params.FavoritesFindValidationParams;
import com.br.api.favorites.domain.validators.sku.SkuMustExistValidator;
import com.br.api.favorites.adapters.outbound.mongo.projection.FavoritesGroupedProjectionDto;
import io.cucumber.java.en.*;

        import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class SkuMustExistValidatorStepDefinitions {

    private IFavoritesAggregationRepository aggregationRepository;
    private SkuMustExistValidator validator;
    private Exception caughtException;

    @Given("the SKU {string} is not present for client {string}")
    public void the_sku_is_not_present_for_client(String sku, String clientId) {
        aggregationRepository = mock(IFavoritesAggregationRepository.class);
        validator = new SkuMustExistValidator(aggregationRepository);

        FavoritesGroupedProjectionDto dto = new FavoritesGroupedProjectionDto(clientId, List.of("OTHER-SKU"));
        when(aggregationRepository.findGroupedByClientId(clientId)).thenReturn(List.of(dto));

        try {
            validator.validate(new FavoritesFindValidationParams(clientId, sku));
        } catch (Exception e) {
            caughtException = e;
        }
    }

    @When("validating that the SKU exists")
    public void validating_that_the_sku_exists() {
    }

    @Then("a SkuNotFoundInFavoritesException should be thrown")
    public void a_sku_not_found_in_favorites_exception_should_be_thrown() {
        assertNotNull(caughtException);
        assertTrue(caughtException instanceof SkuNotFoundInFavoritesException);
    }
}
