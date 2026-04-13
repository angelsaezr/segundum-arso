# Tarea 7 (COMPLETADA)
- La duda era si al modificar un producto había que avisar al microservicio de compraventas, pero no hace falta. El único atributo que comparten es el precio, y en compraventas se debe guardar el precio al que se hizo la operación, no el nuevo.
- Si confirmamos que el microservicio Productos no tiene que notificar ningún evento, habría que eliminar el código de publicar eventos de productos y de consumir eventos de productos en el resto de microservicios.

---

# Tarea 8
- `[DONE]` Microservicio Usuarios: añadir operación pública de login para la pasarela
- `[DONE]` Pasarela: implementar el login con usuario/contraseña
- `[ToDo]` Pasarela: implementar login con GitHub OAuth2
- `[ToDo]` Pasarela: validar el JWT en cada petición y reenviarlo a los microservicios
- `[ToDo]` Microservicios Productos y Compraventas: solo autorización, no autenticación
- `[ToDo]` Productos y Compraventas: implementar control de autorización por operación
- `[ToDo]` (Opcional) Cookie http-only

Notas:
- `[ToDo]` Productos: El usuario que da de alta un producto debe ser el propietario.
- `[ToDo]` Productos: El usuario que modifica un producto debe ser el propietario.

---

# Tarea 9
- Todo por hacer

---

# Operaciones pendientes en APIS:
(Las 2 siguientes operaciones no sé si deberían de ir en `CategoriasController`)
- Productos: Recuperar categorías raíz. Pública.
- Productos: Recuperar descendientes de una categoría. Pública.

---

# Otros
- Crear logs
- Revisar Retrofit si funciona bien, si coge bien los datos y si DTO está bien
- [Angel] Actualizar bd mysql‎

---

# Dudas pendientes
- ¿Buscar productos debería ser pageable?
- Preguntar qué operaciones debe tener cada API.
- Eliminar métodos de servicios/repositorios que no estén siendo utilizados por las APIs.
- ¿Lo de que la id de los productos sea UUID está bien? (ver /productos/src/main/java/segundum/productos/modelo/Producto.java)
- Por qué está productoResumenMensual en servicio
- ¿La consulta a la api de usuarios "getNombreUsuario" debería devolver Nombre + Apellidos?

---

# Respuestas a dudas
- Sobre el microservicio Productos, sería mejor que las categorías fueran gestionadas con su propio controlador (CategoriasController) y que el path de acceso sea "categorias".
- En los servicios implementados con Spring Boot, los listados deberían ser paginados.
- Por último, la propiedad "nombre" de un usuario debería corresponder con el nombre completo, esto es, nombre y apellidos, ya que forma parte de la transacción de compraventa.
