@createBooking
Feature: Cadastrar booking

  Scenario: Cadastrar booking
    Given que possuo acesso a API de bookings
    When realizo o cadastro de um novo booking
    Then a API deve retornar status code 200
    And deve retornar o booking cadastrado
