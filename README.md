# Automation-fabrica-escuela

# Flujo del programa:

### El flujo sigue estos 5 niveles:

* Se comienza con el archivo `.feature` este archivo contienecontiene en lenguaje `Gherkin` la descripción de lo que realizará la prueba.
  Estos se conectan con los StepDefinitions, buscando frases que coincidan con las especificaciones del `.feature`.

* Los StepDefinitions, su proposito es el de traducir el lenguje humano del `.feature` a unas acciones que un 'Actor' va realizar. Sirven de puente entre Gherkin y el código del ScreenPlay.
  Cada método anotado con `@Given`, `@When` o `@Then` se enlaza a una frase del `.feature`. Dentro de estos métodos, se le dice al Actor que intente (`attemptsTo`) realizar una Tarea (`Task`).

* las tareas o `Tasks`, representan un objetivo de negocio o un proceso de alto nivel. Una Tarea no se preocupa por los detalles de cómo hacer clic o escribir, sino que orquesta una secuencia de acciones     para lograr un objetivo. Se lee como un verbo de negocio.

* Las interacciones y las clases nativas de `Serenity` como `Click`, `Enter` y `SelectFromOptions`. Representan la acción más básica y de bajo nivel. Es la interección direacta con un elemento de una     interfaz. Las interacciones utilizan un `Localizador` o `Target` con esto saben con que elemento de la página debe actuar.

* El localizador actúa como un mapa o diccionario de la págia web. En este se definen dónde se encontrarán los elementos como boutones, campos de texto , etc. Acá se usan selectores como Xpath o css.
