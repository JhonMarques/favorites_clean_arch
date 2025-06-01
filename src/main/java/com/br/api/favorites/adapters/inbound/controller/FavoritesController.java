package com.br.api.favorites.adapters.inbound.controller;

import com.br.api.favorites.adapters.inbound.mappers.FavoritesRequestMapper;
import com.br.api.favorites.adapters.inbound.request.FavoritesRequest;
import com.br.api.favorites.adapters.outbound.mongo.projection.FavoritesGroupedProjectionDto;
import com.br.api.favorites.application.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/favorites")
@Tag(name = "Favorites", description = "Endpoints for managing favorites")
public class FavoritesController {

	private final FavoritesRequestMapper favoritesRequestMapper;
	private final CreateFavoritesProduct createFavoritesProduct;
	private final DeleteFavoritesProduct deleteFavoritesProduct;
	private final GetProductFavoritesClient getProductFavoritesClient;
	private final GetFavoritesProjectionByClientId getFavoritesProjectionByClientId;

	public FavoritesController(
            FavoritesRequestMapper favoritesRequestMapper,
            CreateFavoritesProduct createFavoritesProduct,
            DeleteFavoritesProduct deleteFavoritesProduct,
            GetProductFavoritesClient getProductFavoritesClient, GetFavoritesProjectionByClientId getFavoritesProjectionByClientId
            ) {
		this.favoritesRequestMapper = favoritesRequestMapper;
		this.createFavoritesProduct = createFavoritesProduct;
		this.deleteFavoritesProduct = deleteFavoritesProduct;
		this.getProductFavoritesClient = getProductFavoritesClient;
        this.getFavoritesProjectionByClientId = getFavoritesProjectionByClientId;
    }

	@Operation(
			summary = "Creates a new favorites or adds a product to an existing one",
			responses = {
					@ApiResponse(description = "Favorites created", responseCode = "201"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PostMapping
	public ResponseEntity<Void> createFavorites(@RequestBody @Valid FavoritesRequest request) {
		var favorites = favoritesRequestMapper.toDomain(request);
		createFavoritesProduct.call(favorites);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Operation(
			summary = "Removes a product from an existing favorites",
			responses = {
					@ApiResponse(description = "No content", responseCode = "204"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@DeleteMapping("{ClientId}/{sku}")
	public ResponseEntity<Void> deleteFavorites(@PathVariable String ClientId, @PathVariable String sku) {
		deleteFavoritesProduct.call(ClientId, sku);
		return ResponseEntity.noContent().build();
	}

	@Operation(
			summary = "Checks if a product exists in the favorites of a client",
			responses = {
					@ApiResponse(description = "Success", responseCode = "200"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping("/{clientId}/product/{sku}")
	public ResponseEntity<Boolean> productExistsInFavorites(@PathVariable String clientId, @PathVariable String sku) {
		return ResponseEntity.ok(getProductFavoritesClient.call(clientId, sku));
	}

	@Operation(
			summary = "Retrieves all favorite SKUs grouped by clientId",
			responses = {
					@ApiResponse(responseCode = "200", description = "Favorites found and grouped by clientId"),
					@ApiResponse(responseCode = "404", description = "No favorites found for this clientId", content = @Content),
					@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
			}
	)
	@GetMapping("/grouped/{clientId}")
	public ResponseEntity<List<FavoritesGroupedProjectionDto>> getFavoritesGrouped(@PathVariable String clientId) {
		return ResponseEntity.ok(getFavoritesProjectionByClientId.call(clientId));
	}

}
