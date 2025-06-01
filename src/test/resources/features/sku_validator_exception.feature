Feature: SKU must not exist validation

  Scenario: SKU already exists in client's favorites
    Given the SKU "SKU-EXISTENT" already exists for client "6651f9e3d42bfe6cb3ed442a"
    When validating that the SKU does not exist
    Then a SkuAlreadyInFavoritesException should be thrown
