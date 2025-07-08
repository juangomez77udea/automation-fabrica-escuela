# language: en

@login @smoke
Feature: User Authentication

  As a system user
  I want to be able to log in with my credentials
  To access the features corresponding to my profile

  Background:
    Given that Juan wants to log in to the system

  Scenario: Successful login with valid credentials of a registered user
    Given that Juan is on the login page
    When he enters the valid credentials
      | user                       | password   |
      | carlos.santana@example.com | medico1234 |
    Then he should see the main page of the application

  Scenario: Attempt to log in with an incorrect password
    Given that Juan is on the login page
    When he enters an incorrect password for a registered user
      | user                       | password          |
      | carlos.santana@example.com | contrasenaErronea |
    # CORREGIDO para coincidir con el localizador de la UI
    Then he should see an error message that says "¡LOGIN NO VÁLIDO!"
    And he should remain on the login page

  Scenario: Attempt to log in with a non-existent user
    Given that Juan is on the login page
    When he enters credentials for an unregistered user
      | user                       | password      |
      | usuario.no.existe@mail.com | cualquierPass |
    # CORREGIDO para coincidir con el localizador de la UI
    Then he should see an error message that says "¡LOGIN NO VÁLIDO!"
    And he should remain on the login page