package com.br.api.favorites.adapters.inbound.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request to add a product to the favorites")
public class FavoritesRequest {

    @Schema(description = "Customer ID", example = "6651f9e3d42bfe6cb3ed442a")
    @NotBlank(message = "ClientId cannot be blank")
    @Size(min = 24, max = 24, message = "The size of clientId should be 24")
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "clientId must be a valid ObjectId")
    private String clientId;

    @Schema(description = "Product SKU", example = "6651f9e3d42bfe6cb3ed4419")
    @NotBlank(message = "Sku cannot be blank")
    @Size(min = 24, max = 24, message = "The size of sku should be 24")
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "sku must be a valid ObjectId")
    private String sku;
}

