Feature: List module verification
  This feature covers all the areas provided by themoviedb list module

  @creationOfMovielist
  Scenario Outline: Creation of the movie list
    Given User sets the specification and create Request Token with 'CREATEREQUESTTOKEN' endpoint
    When User authorize the request token
    And User gets the Access Token with 'ACCESSTOKEN' endpoint
    Then User create session with hitting the 'CREATESESSION' endpoint for the http create session method
    And User sends the payload for the http post method in bellow format
      | name   | description   | language   |
      | <name> | <description> | <language> |
    Then User create the list with the 'CREATELIST' endpoint for the http create list method
    And User validates if the status code is equal 201
    Examples:
      | name        | description         | language |
      | F.F.Coppola | The best of Coppola | en       |

  @addMovieToTheList
  Scenario: Add movie to the list
    Given User sets the specification
    When User hits the 'ADDMOVIE' endpoint for the http add movie method
    Then User validates if the status code is equal 201

  @checkItemStatus
  Scenario: User checks item status
    Given User sets the specification
    When User hits the 'CHECKITEMSTATUS' endpoint for the http check item status method
    Then User validates if the status code is equal 200
    And 'item_present' in response body is equal 'false'

  @getListDetail
  Scenario: Get details of the particular list
    Given User sets the specification
    When User hits the 'GETLISTDETAIL' endpoint for the http get details of the list method
    Then User validates if the status code is equal 200
    And 'created_by' in response body is equal 'mk90'

  @deleteList
  Scenario: Delete particular list
    Given User sets the specification
    When User hits the 'DELETELIST' endpoint for the delete method
    Then User validates if the status code is equal 200
    And 'status_message' in response body is equal 'The item/record was deleted successfully.'