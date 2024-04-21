Feature: To check library books functionality
  Scenario: verify post api for addBook in library
    Given addBook for users in library
    When user call "addBookAPI" with http post request
    Then api will give response with status code 200
    And "status" in response body is "OK"

  Scenario: verify delete api for deleteBook from library
    Given deleteBook for users from library
    When user call "deleteBookAPI" with http delete request
    Then api will give response with status code 200
    And "status" in response body is "OK"
    And "msg" in Delete response body is "book is successfully deleted"