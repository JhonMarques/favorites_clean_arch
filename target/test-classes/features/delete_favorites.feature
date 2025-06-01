Feature: Delete a SKU from favorites

  Scenario: Delete existing SKU from favorites
    Given a client with ID "6651f9e3d42bfe6cb3ed442a" has favorites including "6651f9e3d42bfe6cb3ed4421"
    When the client deletes SKU "6651f9e3d42bfe6cb3ed4421" from favorites
    Then the favorites list should not contain "6651f9e3d42bfe6cb3ed4421"

  Scenario: Attempt to delete SKU for non-existent client
    Given a client with ID "000000000000000000000000" does not exist
    When the client deletes SKU "6651f9e3d42bfe6cb3ed4333" from favorites
    Then an error should be thrown
