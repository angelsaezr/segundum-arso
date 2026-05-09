# Tarea 8
- `[ToDo]` Probar autorizacion compraventas y productos, por operacion y reenvio de JWT a microservicios

---

# Otros ToDos
- Probar, revisar y exportar pruebas de Postman para la entrega (usar variable de entorno para el token, ids, etc para que sea más sencillo). Clonar repo y probar
- Al iniciar sesion con OAuth2, el githubId se pone en el campo nombre‎

---

# Dudas pendientes
- Preguntar qué operaciones debe tener cada API.
- Eliminar métodos de servicios/repositorios que no estén siendo utilizados por las APIs.
- ¿Lo de que la id de los productos sea UUID está bien? (ver `/productos/src/main/java/segundum/productos/modelo/Producto.java`)

---

## Feedback entrega adelantada

### Usuarios
**Servicio**
  - `[DONE]` En el alta, cuando se produce una colisión con el email de un usuario, la excepción que mejor lo identifica es "IllegalState". Posteriormente en REST habría que introducir un manejador que la transforme en "conflict".

**Controlador REST**
  - `[DONE]` La operación de listado debe ofrecer enlaces para recuperar la información completa de cada uno de los usuarios. Podéis consultar el ejemplo que se hizo en clase (Bookle).
  - `[DONE]` Las últimas operaciones tienen algunos tratamientos de error que no son necesarios, ya que actúan los manejadores globales de error.

### Productos
**REST**
  - `[DONE]` openapi: también hay que documentar los DTO de entrada.
  - `[DONE]` openapi: habría que documentar también las respuestas y los parámetros.
  - `[DONE]` En el otro microservicio se ha aplicado openapi del mismo modo.

### Compraventas
**Servicio**
  - `[TODO]` getCompraventaById: si no existe, habría que lanzar una excepción "not found".

**Eventos**
  - `[TODO]` RabbitMQConfig: no debería ser estática en el código, sino definida en una propiedad de application.properties.
  
---

## Cómo desplegar desde docker y probar

1. docker-compose up -d --build
2. docker-compose logs -f usuarios (esperar a que despliegue, es el que más tarda)
3. Desde Postman crear 2 usuarios (al menos uno de los dos debería ser admin, para probar posteriormente los métodos de ADMINISTRADOR)
4. Crear al menos un producto (solo se puede crear con token con el id del vendedor)
5. Probar el resto de métodos (revisar si se necesita mandar el token del comprador, vendedor, etc, o si hace falta que el usuario sea ADMINISTRADOR)

Comandos útiles para ver BBDD desde docker:

- mysql:

	docker exec -it segundum-arso_mysql_1 mysql -u root -ppracticas usuarios
	docker exec -it segundum-arso_mysql_1 mysql -u root -ppracticas productos

- mongo:
	
	TODO

---

# Correo profesor
La propuesta fue que en el formulario de alta se establecían dos alternativas para el proceso de autenticación: login/contraseña o GitHub (identificador). El formulario solo permite introducir una de las dos. Por tanto, en la base de datos uno de los dos campos (contraseña, GitHubId) tendrá un valor vacío. Esto es lo que nos permite discriminar posteriormente la autenticación.

Tampoco habría inconveniente en permitir introducir a la vez los dos campos. Esto implicaría que un usuario puede autenticarse por dos vías alternativas.

El proceso de alta con GitHub sería más complicado. Primero requiere conceder los permisos a la aplicación y después la aplicación debe capturar los datos que no le ofrece GitHub a través de un formulario. Esto implica una coordinación entre el frontend y el backend. Por esto se optó por simplificarlo en un solo formulario.

Por último, el GitHubId es único para cada usuario.







