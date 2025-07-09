# Proyecto Reactor Spring Boot

Este proyecto es un ejemplo básico de programación reactiva utilizando Spring Boot y Project Reactor.

## Descripción

El proyecto demuestra cómo trabajar con flujos reactivos (`Flux`) en Java para procesar una lista de nombres, transformarlos en objetos de tipo `Usuario`, aplicar filtros, transformaciones y manejar eventos de error y finalización.

### Flujo principal (`ReactorApplication`)

- Se define una lista de nombres completos.
- Se crea un `Flux` a partir de la lista.
- Se transforma cada nombre en un objeto `Usuario` (nombre y apellido en mayúsculas).
- Se filtra el flujo para obtener solo el usuario cuyo nombre sea "martin".
- Se realizan validaciones y se imprime información de cada usuario procesado.
- Se transforma el nombre a minúsculas.
- Se maneja la suscripción al flujo, mostrando logs para cada usuario, errores y cuando el proceso finaliza.

## Ejecución

Para ejecutar el proyecto:

1. Clona el repositorio o descarga el código fuente.
2. Asegúrate de tener Java y Maven instalados.
3. Ejecuta el proyecto con:

   ```
   mvn spring-boot:run
   ```

## Dependencias principales

- Spring Boot
- Project Reactor

## Estructura del proyecto

- `ReactorApplication.java`: Clase principal con la lógica reactiva.
- `models/Usuario.java`: Clase modelo para los usuarios.

## Créditos

Ejemplo desarrollado para fines educativos sobre programación reactiva en Java.
