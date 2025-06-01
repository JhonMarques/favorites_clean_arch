package com.br.api.favorites.bdd;

import com.br.api.favorites.adapters.outbound.mongo.projection.FavoritesGroupedProjectionDto;
import com.br.api.favorites.application.usecases.GetFavoritesProjectionByClientId;
import com.br.api.favorites.domain.repositories.IFavoritesAggregationRepository;
import com.br.api.favorites.domain.validators.sku.SkuMustNotExistValidator;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetFavoritesProjectionStepDefinitions {

    private IFavoritesAggregationRepository aggregationRepository;
    private SkuMustNotExistValidator skuMustNotExistValidator;
    private GetFavoritesProjectionByClientId getFavoritesProjectionByClientId;
    private List<FavoritesGroupedProjectionDto> result;

    @Given("a client with ID {string} has grouped favorites")
    public void a_client_with_id_has_grouped_favorites(String clientId) {
        aggregationRepository = mock(IFavoritesAggregationRepository.class);
        skuMustNotExistValidator = mock(SkuMustNotExistValidator.class);
        getFavoritesProjectionByClientId = new GetFavoritesProjectionByClientId(aggregationRepository, skuMustNotExistValidator);

        List<FavoritesGroupedProjectionDto> grouped = new ArrayList<>();
        grouped.add(new FavoritesGroupedProjectionDto(clientId, List.of("SKU-1", "SKU-2")));
        grouped.add(new FavoritesGroupedProjectionDto(clientId, List.of("SKU-3")));

        when(aggregationRepository.findGroupedByClientId(clientId)).thenReturn(grouped);
        doNothing().when(skuMustNotExistValidator).validate(any());
    }

    @When("the system retrieves the favorites projection for the client")
    public void the_system_retrieves_the_favorites_projection_for_the_client() {
        result = getFavoritesProjectionByClientId.call("6651f9e3d42bfe6cb3ed442a");
    }

    @Then("the projection result should contain {int} groups")
    public void the_projection_result_should_contain_groups(Integer expectedGroups) {
        assertNotNull(result);
        assertEquals(expectedGroups, result.size());
    }
}
