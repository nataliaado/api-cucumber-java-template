@deleteBooking
Feature: Deletar booking especifico por ID

  Scenario: Deletar booking especifico por ID
    Given que existe um booking cadastrado
    And solicito a geracao do token
    When realizo a exclusao do booking
    Then a API deve retornar status code 201
    And o booking nao deve mais existir
