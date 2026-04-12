# Tarea 7 (COMPLETADA)
- La duda era si al modificar un producto había que avisar al microservicio de compraventas, pero no hace falta. El único atributo que comparten es el precio, y en compraventas se debe guardar el precio al que se hizo la operación, no el nuevo.
- Si confirmamos que el microservicio Productos no tiene que notificar ningún evento, habría que eliminar el código de publicar eventos de productos y de consumir eventos de productos en el resto de microservicios.

---

# Tarea 8
- [ToDo] Productos: El usuario que da de alta un producto debe ser el propietario.
- [ToDo] Productos: El usuario que modifica un producto debe ser el propietario.

---

# Tarea 9
- Todo por hacer

---

# Operaciones pendientes en APIS:
(Las 2 siguientes operaciones no sé si deberían de ir en `CategoriasController`)
- Productos: Recuperar categorías raíz. Pública.
- Productos: Recuperar descendientes de una categoría. Pública.

# Otros
- Crear logs
- Revisar Retrofit si funciona bien, si coge bien los datos y si DTO está bien
- [Angel] Actualizar bd mysql‎

---

# Dudas pendientes
- ¿Hay que implementar `CategoriasController` en el microservicio `productos`?
- ¿Buscar productos debería ser pageable?
- Preguntar qué operaciones debe tener cada API.
- Eliminar métodos de servicios/repositorios que no estén siendo utilizados por las APIs.
- ¿Lo de que la id de los productos sea UUID está bien? (ver /productos/src/main/java/segundum/productos/modelo/Producto.java)
- Por qué está productoResumenMensual en servicio
- ¿La consulta a la api de usuarios "getNombreUsuario" debería devolver Nombre + Apellidos?
