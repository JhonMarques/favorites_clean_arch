Feature: Check if a product is in the client's favorites

  Scenario: Product is in favorites
    Given a client with ID "6651f9e3d42bfe6cb3ed442a" has product "SKU-123" in favorites
    When the system checks if the product is in the client's favorites
    Then the result should be true

  Scenario: Product is not in favorites
    Given a client with ID "6651f9e3d42bfe6cb3ed442a" does not have product "SKU-999" in favorites
    When the system checks if the product is in the client's favorites
    Then the result should be false
