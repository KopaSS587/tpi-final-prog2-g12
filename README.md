# Trabajo Práctico Integrador - Programación II - Grupo 12

Este repositorio contiene el trabajo práctico integrador final de la materia Programación II, que se dicta en la carrera Tecnicatura Universitaria en Programación, en la UTN sede Resistencia.
El trabajo consiste en un software escrito en el lenguaje de programación Java, y se trata de un programa para gestionar un estacionamiento.
El código fue escrito principalmente en vscode mediante la herramienta colaborativa liveshare, y fue compilado usando IntelliJ y la terminal.

## Integrantes y responsabilidades

Si bien todos los integrantes trabajamos en conjunto en todos los modulos, los integrantes trabajaron mas especialmente en los módulos listados.

Esquivel Gomez, Marcelo (esquivelgomezmarcelo@gmail.com):
TarifaPorHorario.java, VehiculoNoEncontrado.java

Portillo de Uria, Guillermo (guillepdu03@gmail.com):
estacionamiento.java

Romaniuk, Lucas Genaro (romaniuklucas587@gmail.com):
SimpleLinkedList.java, Ticket.java

Kozak Mattar, Viggo (viggokozak@hotmail.com.ar):
Tarifa.java, Auto.java, Camioneta.java, Moto.java, Vehiculo.java

Los archivos estacionamiento.java y especialmente main.java fueron especialmente hechos por todos los integrantes del grupo.

## Compilación y ejecución (Linux y Windows)

Para compilar y ejecutar correctamente el programa desde la terminal, es necesario ubicarse en la carpeta que contiene `src/` (la raíz del repositorio).

### Compilación

#### Linux

```
javac -d out $(find src -name "*.java")
```

#### Windows PowerShell

```
javac -d out $(Get-ChildItem -Recurse -Filter *.java src)
```

### Ejecución

Una vez compilado, ejecutar el programa indicando el paquete de la clase principal (`Main.java`):

```
java -cp out src.Main
```

El parámetro `-cp out` apunta a la carpeta donde se generaron las clases compiladas, y `src.Main` corresponde al paquete y nombre de la clase principal.
