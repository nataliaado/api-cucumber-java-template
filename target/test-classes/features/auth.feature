@auth
Feature: Autenticação

  Scenario: Gerar token com sucesso
    Given que possuo credenciais validas
    When solicito a geracao do token
    Then o token deve ser gerado com sucesso
