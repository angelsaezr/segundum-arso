# Otros ToDos
- Postman modificar usuario no funciona
- Exportar pruebas de Postman para la entrega. Clonar repo y probar‎
  
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







