# segundum-arso
Proyecto ARSO 25/26

## ToDos:
#### - Repositorio y servicio Categoria

`getCategoriasRaiz`: en lugar de recuperar todas las categorías y filtrar, sería más eficiente construir una consulta JPQL que hiciera la consulta.

`getDescendientesCategoria`: en lugar de tener un método recursivo para obtener los descendientes, será más eficiente hacer una consulta JPQL que aprovechara la existencia del campo `ruta`.

#### - Repositorio y servicio Usuarios

Hay que comprobar que el email del usuario no existe ya antes de crearlo.

#### - Repositorio y servicio productos

La creación primera del producto no tiene que recibir el lugar de recogida. Además, debería ser el producto el que cree su lugar de recogida.

Obtener el resumen mensual debería ser una consulta JPQL, más eficiente que recuperar todos los productos, iterar y filtrar.

Obtener las categorías y sus descendientes se puede simplificar usando el campo `ruta`.

#### - Tarea 1

Los proyectos (microservicios) tendrán esquemas de base de datos independientes.
