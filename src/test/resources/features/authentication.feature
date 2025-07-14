# language: en
@authentication @HU005
Feature: User Authentication

  As a system user
  I want to authenticate myself
  To access my assigned profile features

  Background:
    Given the user is on the login page

  Scenario Outline: Successful login with valid credentials
    When he enters the valid credentials "<user>" and "<password>"
    Then he should be redirected to the schedules page

    Examples:
      | user                       | password   |
      | carlos.santana@example.com | medico1234 |

  Scenario Outline: System rejects invalid credentials
    When he enters the invalid credentials "<user>" and "<password>"
    Then the system should show an invalid login error message
    And he should remain on the login page

    Examples:
      | user                       | password          |
      | carlos.santana@example.com | contrasenaErronea |
      | usuario.no.existe@mail.com | cualquierPass     |
      | pepo@mail.com              | incorrecta        |
      |                            | 123456            |
      | carlos.santana@example.com |                   |