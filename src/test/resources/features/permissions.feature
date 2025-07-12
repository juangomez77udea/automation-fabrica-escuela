# language: en
@permissions @HU006
Feature: Role-based Access Control

  As an administrator
  I want to ensure that only authorized users see sensitive options
  To maintain system security and order

  Scenario Outline: Verify visibility of schedule management options by role
    Given the user "<user>" with password "<password>" logs in to the system
    Then he <visibility> see the modification options in the schedule table

    Examples: Authorized Roles
      | user                       | password   | visibility |
      | carlos.santana@example.com | medico1234 | should     |

    Examples: Unauthorized Roles
      | user          | password   | visibility  |
      | juan@mail.com | 123456     | should not|
      | pepo@mail.com | 123456     | should not|