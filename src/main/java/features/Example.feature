@BrunoFraga
Feature: Example of cucumber usage with selenium
  I want to connect to a website and then change url

  @First @Scenario
  Scenario: Valido acesso da homepage
    Given que acesso a homepage do google
    And valido se a pagina acessada e a homepage do google

  @Last @Scenario
  Scenario: Valido acesso ao youtube
    Given que acesso a pagina do youtube
    Then valido se a pagina acessa e a pagina do youtube
