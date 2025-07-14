# language: en
@schedule @HU001 @HU002
Feature: Doctor's Schedule Availability Management

  As a Doctor or Coordinator
  I want to manage the availability schedules for doctors
  To keep the appointment system up to date

  Scenario Outline: Successfully add a new time slot for a doctor
    Given the doctor "<user>" with password "<password>" is on the schedules page

    When he adds a new availability for "<doctorName>" with the following data:
      | StartDate    | EndDate      | StartTime   | EndTime   |
      | <startDate>  | <endDate>    | <startTime> | <endTime> |

    Then the new schedule from "<startTime>" to "<endTime>" on "<startDate>" should be visible for "<doctorName>"

    Examples:
      | user                       | password   | doctorName      | startDate  | endDate    | startTime | endTime |
      | carlos.santana@example.com | medico1234 | Carlos Santana  | 2025-08-01 | 2025-08-01 | 09:00     | 10:00   |