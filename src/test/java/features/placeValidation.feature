Feature:validate the create user api
  Scenario: verify the create user api
    Given create user payload
    When user call "createUserApi" with post http request
    Then the API  call get success with status code 201
    And "status" in response body "Created"



