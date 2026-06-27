# SegundUM — Plataforma de compraventa de segunda mano

Aplicación distribuida basada en microservicios para la compraventa de productos de segunda mano, desarrollada en Java con Spring Boot y JAX-RS.

## Arquitectura

El sistema está compuesto por cuatro componentes principales que se comunican entre sí de forma síncrona (REST/Retrofit) y asíncrona (RabbitMQ):

```
Cliente
  └─► Pasarela (puerto 8090)  ←── JWT / OAuth2 GitHub
        ├─► Usuarios    (puerto 8080)  — JAX-RS + MySQL
        ├─► Productos   (puerto 8081)  — Spring Boot + MySQL
        └─► Compraventas (puerto 8082) — Spring Boot + MongoDB
```

### Microservicios

| Servicio | Tecnología | Base de datos | Descripción |
|---|---|---|---|
| **Usuarios** | JAX-RS (Jersey) + EclipseLink | MySQL | Gestión de usuarios y autenticación |
| **Productos** | Spring Boot + Spring Data JPA | MySQL | Catálogo de productos y categorías |
| **Compraventas** | Spring Boot + Spring Data MongoDB | MongoDB | Registro de transacciones |
| **Pasarela** | Spring Boot + Netflix Zuul | — | Enrutamiento, autenticación JWT y OAuth2 |

## Tecnologías

- **Java 8**, Maven
- **Spring Boot 2.6**, Spring Security, Spring HATEOAS, Spring Data JPA/MongoDB
- **JAX-RS** (Jersey 2.41) con EclipseLink para el microservicio de usuarios
- **JWT** (jjwt) y **OAuth2** con GitHub
- **RabbitMQ** (mensajería asíncrona con topic exchange)
- **Retrofit 2** para comunicación entre microservicios
- **MySQL 8** y **MongoDB 6**
- **Docker** y **Docker Compose**
- **Swagger / OpenAPI** (springdoc) en los microservicios Spring Boot

## Funcionalidades

- Registro, login y modificación de usuarios
- Autenticación mediante usuario/contraseña o cuenta de GitHub (OAuth2)
- Alta, consulta, modificación y búsqueda de productos con filtros (categoría, estado, precio)
- Jerarquía de categorías cargada desde XML
- Historial mensual de productos por vendedor
- Registro de compraventas con validación de disponibilidad del producto
- Consulta de compras y ventas por usuario
- Propagación de eventos entre microservicios vía RabbitMQ (compraventa creada, usuario creado/modificado)
- Control de acceso por roles (`USUARIO`, `ADMINISTRADOR`) con Spring Security y `@PreAuthorize`

## Puesta en marcha

### Requisitos

- Docker y Docker Compose instalados

### Arrancar todo el sistema

```bash
docker compose up --build
```

Los servicios estarán disponibles en:

| Servicio | URL |
|---|---|
| Pasarela | http://localhost:8090 |
| Usuarios | http://localhost:8080/api |
| Productos | http://localhost:8081 |
| Compraventas | http://localhost:8082 |
| RabbitMQ (panel) | http://localhost:15672 |
| Mongo Express | http://localhost:8083 |

Credenciales por defecto de las bases de datos y RabbitMQ: `admin / practicas`.

### Desarrollo local (sin Docker)

Cada microservicio puede arrancarse de forma independiente. Los ficheros `application.properties` admiten variables de entorno para sobreescribir la configuración por defecto (host de base de datos, RabbitMQ, etc.).

## API REST

Los microservicios **Productos** y **Compraventas** exponen documentación interactiva con Swagger UI:

- Productos: http://localhost:8081/swagger-ui.html
- Compraventas: http://localhost:8082/swagger-ui.html

La carpeta `postman/` contiene colecciones y un entorno Postman listos para usar con las operaciones principales de cada microservicio.

## Estructura del proyecto

```
├── usuarios/          # Microservicio Usuarios (JAX-RS)
├── productos/         # Microservicio Productos (Spring Boot)
├── compraventas/      # Microservicio Compraventas (Spring Boot)
├── pasarela/          # Pasarela API (Spring Boot + Zuul)
├── postman/           # Colecciones y entorno Postman
├── init.sql           # Inicialización de bases de datos MySQL
└── docker-compose.yml
```
