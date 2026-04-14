# Tarea 8
- `[DONE]` Microservicio Usuarios: añadir operación pública de login para la pasarela
- `[DONE]` Pasarela: implementar el login con usuario/contraseña
- `[DONE]` Pasarela: implementar login con GitHub OAuth2
- `[DONE]` Pasarela: validar el JWT en cada petición y reenviarlo a los microservicios
- `[DONE]` Microservicios Productos y Compraventas: solo autorización, no autenticación
- `[DONE]` Productos y Compraventas: implementar control de autorización por operación
- `[DONE]` (Opcional) Cookie http-only
- `[ToDo]` Ver si GitHub da el email porque buscamos el usuario por email
- `[ToDo]` Probar OAuth2

Notas:
- `[DONE]` Productos: El usuario que da de alta un producto debe ser el propietario.
- `[DONE]` Productos: El usuario que modifica un producto debe ser el propietario.

---

# Tarea 9
- Todo por hacer

---

# Operaciones pendientes en APIS:
- `[CategoriasController]` Recuperar categorías raíz. Pública.
- `[CategoriasController]` Recuperar descendientes de una categoría. Pública.

---

# Otros ToDos
- La consulta a la api de usuarios "getNombreUsuario" debería devolver Nombre + Apellidos
- Crear logs
- Revisar Retrofit si funciona bien, si coge bien los datos y si DTO está bien
- [Angel] Actualizar bd mysql‎

---

# Dudas pendientes
- Preguntar qué operaciones debe tener cada API.
- Eliminar métodos de servicios/repositorios que no estén siendo utilizados por las APIs.
- ¿Lo de que la id de los productos sea UUID está bien? (ver /productos/src/main/java/segundum/productos/modelo/Producto.java)
- Por qué está productoResumenMensual en servicio