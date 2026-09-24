@updateBooking
Feature: Atualizar booking

  Scenario: Atualizar booking existente

    Given que existe um booking cadastrado
    And solicito a geracao do token
    When realizo a atualizacao do booking
    Then a API deve retornar status code 200
    And deve retornar os dados atualizados