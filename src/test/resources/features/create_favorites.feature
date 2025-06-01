Feature: Create a favorites entry

  Scenario: Create new favorites document
    Given a client with ID "6651f9e3d42bfe6cb3ed442a" has no favorites
    When the client adds SKU "SKU-123" to favorites
    Then the favorites list should contain "SKU-123"

  Scenario: Add to existing favorites
    Given a client with ID "6651f9e3d42bfe6cb3ed442a" already has favorites
    When the client adds SKU "SKU-456" to favorites
    Then the favorites list should include the new SKU "SKU-456"
