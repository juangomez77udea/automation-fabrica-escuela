# language: en
@schedule @HU001 @HU002
Feature: Doctor's Schedule Availability Management

  As a Doctor or Coordinator
  I want to manage the availability schedules for doctors
  To keep the appointment system up to date

  Scenario: Successfully add a new time slot for a doctor
    Given the doctor "carlos.santana@example.com" with password "medico1234" is on the schedules page
    When he adds a new availability for "Carlos Santana" with the following data:
      | StartDate  | EndDate    | StartTime | EndTime |
      | 2025-08-01 | 2025-08-01 | 09:00     | 10:00   |
    Then the new schedule from "09:00" to "10:00" on "2025-08-01" should be visible for "Carlos Santana"