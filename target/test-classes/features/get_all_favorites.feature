Feature: Get favorites projection by client ID

  Scenario: Retrieve grouped favorites for an existing client
    Given a client with ID "6651f9e3d42bfe6cb3ed442a" has grouped favorites
    When the system retrieves the favorites projection for the client
    Then the projection result should contain 2 groups
