@getBooking
Feature: Consultar booking específico por ID

  Scenario: Consultar booking específico por ID
    Given que existe um booking cadastrado
    When realizo a consulta de booking por id
    Then a API deve retornar status code 200
    And deve retornar o booking do ID mencionado
