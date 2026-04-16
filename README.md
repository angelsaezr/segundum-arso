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