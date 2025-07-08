# language: en

@schedule @regression
Feature: Doctor's Schedule Availability Management

  As a doctor,
  I want to be able to add time slots to my own schedule
  To define when I can attend appointments.

  Scenario: Successfully add a new time slot for a doctor
    Given the doctor "carlos.santana@example.com" with password "medico1234" is on the schedules page
    When he clicks on the add schedule button for "Carlos Santana"
    And he adds a new availability with the following data
      | Field       | Value      |
      | Start Date  | 2025-08-01 |
      | End Date    | 2025-08-05 |
      | Start Time  | 09:00      |
      | End Time    | 13:00      |
    Then he should see a success message that says "¡Franja horaria agregada exitosamente!"
    And his schedule in the table should show the new availability
      # =====> CORRECCIÓN FINAL AQUÍ <=====
      # Ajustamos los días para que coincidan con el cálculo real de la aplicación
      # para el rango 1 de Agosto (Viernes) al 5 de Agosto (Martes) de 2025.
      # El orden también importa para la comparación de texto.
      | Days                                    | Schedule      | Date Range              |
      | VIERNES, SÁBADO, DOMINGO, LUNES, MARTES | 09:00 - 13:00 | 2025-08-01 - 2025-08-05 |