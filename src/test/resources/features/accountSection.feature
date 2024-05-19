Feature: Account module verification
  This feature covers all the areas provided by themoviedb account module

  Background:
    Given User sets the specification

  @getAccountDetails
  Scenario: Get an account details
    When User hits the 'GETACCOUNTDETAILS' endpoint for the http get account detail method
    Then User validates if the status code is equal 200
    And 'username' in response body is equal 'mk90'
    And 'iso_639_1' in response body is equal 'pl'

  @getFavouriteMovies
  Scenario: Get favourite movies
    When User hits the 'GETFAVORITEMOVIE' endpoint for the http get favorite movie method
    Then User validates if the status code is equal 200

  @addFavouriteMovies
  Scenario: Add favourite movieó
    When User hits the 'ADDFAVORITEMOVIE' endpoint for the http add favorite movie method
    Then User validates if the status code is equal 201

