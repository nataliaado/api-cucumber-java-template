@getBookingIDS
Feature: Consulta IDS de uma lista de bookings

  Scenario: Consultar IDS de uma lista de bookings
    Given que possuo acesso a API de bookings
    When realizo a consulta de todos os bookings
    Then a API deve retornar status code 200
    And deve retornar uma lista de bookings
