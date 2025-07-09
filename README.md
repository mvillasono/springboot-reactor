# Spring Boot WebFlux - Ejemplo de Programación Reactiva

Este proyecto es un ejemplo de aplicación web reactiva usando **Spring Boot WebFlux** y **MongoDB**. Permite listar productos de manera reactiva y visualizar los datos en diferentes modos usando Thymeleaf.

## Características

- API REST reactiva para productos (`/api/productos`)
- Vistas Thymeleaf para mostrar productos en diferentes modos:
  - Listado simple (`/listar`)
  - Data Driver (`/listar-datadriver`)
  - Listado masivo (`/listar-full`)
  - Chunked (`/listar-chunked`)
- Persistencia reactiva con MongoDB

## Requisitos

- Java 17+
- MongoDB en ejecución en `localhost:27017`
- Maven

## Ejecución

1. Clona el repositorio.
2. Asegúrate de tener MongoDB corriendo.
3. Ejecuta la aplicación:

   ```
   mvn spring-boot:run
   ```

4. Accede a las rutas en tu navegador:

   - [http://localhost:8080/listar](http://localhost:8080/listar)
   - [http://localhost:8080/listar-datadriver](http://localhost:8080/listar-datadriver)
   - [http://localhost:8080/listar-full](http://localhost:8080/listar-full)
   - [http://localhost:8080/listar-chunked](http://localhost:8080/listar-chunked)
   - API REST: [http://localhost:8080/api/productos](http://localhost:8080/api/productos)

## Estructura del Proyecto

- `controller/` - Controladores REST y de vistas
- `models/` - Documentos y DAO de MongoDB
- `templates/` - Vistas Thymeleaf
- `application.properties` - Configuración de la aplicación

## Licencia

MIT
