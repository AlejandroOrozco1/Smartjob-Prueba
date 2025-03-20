# REGISTRO API

## Descripción

API para registrar usuarios utilizando Spring Boot con las siguientes características:

- Base de datos en memoria H2.
- Persistencia con JPA y Hibernate.
- Construcción del proyecto con Gradle.
- Java 17.

## Requisitos previos

Asegúrate de tener instalado lo siguiente:

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle](https://gradle.org/install/)

## Configuración y Ejecución

1. Clonar el repositorio y acceder al directorio del proyecto:

2. Construir y ejecutar la aplicación con Gradle:

3. La aplicación estará disponible en: http\://localhost:8080/api/

## Endpoints disponibles

### 1. Registro de usuario

**URL:** `/api/usuario/register`\
**Método:** `POST`\
**Autenticación:** No requerida\
**Request Body:**

**Respuesta exitosa (200 OK):**

### 2. Obtener usuario por ID

**URL:** `/api/usuario/{id}`\
**Método:** `GET`\
**Autenticación:** Requiere token JWT en el header `Authorization: Bearer <jwt-token>`\
**Ejemplo de respuesta exitosa (200 OK):**

## Base de Datos en Memoria H2

Para acceder a la consola de H2 y visualizar los datos:

1. Ingresar a `http://localhost:8080/h2`
2. Configurar los siguientes valores:
    - **JDBC URL:** `jdbc:h2:mem:test`
    - **Usuario:** `sa`
    - **Contraseña:** pass
3. Hacer clic en "Connect".

## Seguridad

- Se usa autenticación mediante `Bearer Token` (JWT) para acceder a los endpoints protegidos.
- Es necesario enviar el token en el `Authorization Header` para obtener información del usuario.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- H2 Database
- JPA (Hibernate)
- Gradle
- OpenAPI 3.0.1
- JWT para autenticación


Lee el artículo [Clean Architecture — Aislando los detalles](https://medium.com/bancolombia-tech/clean-architecture-aislando-los-detalles-4f9530f35d7a)

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no están arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos
genéricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan
basadas en el patrón de diseño [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006)

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## Application

Este módulo es el más externo de la arquitectura, es el encargado de ensamblar los distintos módulos, resolver las dependencias y crear los beans de los casos de use (UseCases) de forma automática, inyectando en éstos instancias concretas de las dependencias declaradas. Además inicia la aplicación (es el único módulo del proyecto donde encontraremos la función “public static void main(String[] args)”.

**Los beans de los casos de uso se disponibilizan automaticamente gracias a un '@ComponentScan' ubicado en esta capa.**
