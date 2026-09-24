@partialUpdateBooking
Feature: Atualizar parcialmente um booking

  Scenario: Atualizar parcialmente um booking existente

    Given que existe um booking cadastrado
    And solicito a geracao do token
    When realizo a atualizacao parcial do booking
    Then a API deve retornar status code 200
    And deve retornar os dados parcialmente atualizados