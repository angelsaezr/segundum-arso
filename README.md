# Tarea 8
- `[DONE]` Microservicio Usuarios: añadir operación pública de login para la pasarela
- `[DONE]` Pasarela: implementar el login con usuario/contraseña
- `[DONE]` Pasarela: implementar login con GitHub OAuth2
- `[DONE]` Pasarela: validar el JWT en cada petición y reenviarlo a los microservicios
- `[DONE]` Microservicios Productos y Compraventas: solo autorización, no autenticación
- `[DONE]` Productos y Compraventas: implementar control de autorización por operación
- `[DONE]` (Opcional) Cookie http-only
- `[DONE]` Ver si GitHub da el email porque buscamos el usuario por email
- `[DONE]` Probar OAuth2
- `[ToDo]` Probar autorizacion compraventas y productos, por operacion y reenvio de JWT a microservicios

Notas:
- `[DONE]` Productos: El usuario que da de alta un producto debe ser el propietario.
- `[DONE]` Productos: El usuario que modifica un producto debe ser el propietario.

---

# Tarea 9
- Todo por hacer

---

# Otros ToDos
- Al crear un usuario del microservicio usuarios, el nombre se pone en el campo telefono
- Al iniciar sesion con OAuth2, el githubId se pone en el campo nombre
- Crear logs‎

---

# Dudas pendientes
- Preguntar qué operaciones debe tener cada API.
- Eliminar métodos de servicios/repositorios que no estén siendo utilizados por las APIs.
- ¿Lo de que la id de los productos sea UUID está bien? (ver /productos/src/main/java/segundum/productos/modelo/Producto.java)
- Por qué está productoResumenMensual en servicio

---

# Correo profesor
La propuesta fue que en el formulario de alta se establecían dos alternativas para el proceso de autenticación: login/contraseña o GitHub (identificador). El formulario solo permite introducir una de las dos. Por tanto, en la base de datos uno de los dos campos (contraseña, GitHubId) tendrá un valor vacío. Esto es lo que nos permite discriminar posteriormente la autenticación.

Tampoco habría inconveniente en permitir introducir a la vez los dos campos. Esto implicaría que un usuario puede autenticarse por dos vías alternativas.

El proceso de alta con GitHub sería más complicado. Primero requiere conceder los permisos a la aplicación y después la aplicación debe capturar los datos que no le ofrece GitHub a través de un formulario. Esto implica una coordinación entre el frontend y el backend. Por esto se optó por simplificarlo en un solo formulario.

Por último, el GitHubId es único para cada usuario.