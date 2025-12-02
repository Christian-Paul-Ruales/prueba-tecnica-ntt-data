## EJERCICIO PRACTICO

### TECNOLOGIAS UTILIZADAS
1. JAVA 25 (Ultima versión LTS)
2. Spring boot 4.0.0 (Spring 7)
3. MariaDB (Docker compose)
4. PostgreSQL (Docker compose)
5. Docker Desktop - Docker Compose
6. Intellij IDEA
7. Postman

### EXPLICACION ARQUITECTURA GENERAL
1. El proyecto cuenta con un servidor de configuraciones centralizado con dos perfiles
   * default: Utilizado para ejecucion en un entorno de pruebas local
   * docker: que funciona dentro de la red de docker

2. Cuenta con un servidor de registro para microservicios (Eureka Server) llamado Discovery server
3. Cuenta con una puerta de entrada, API GATEWAY
4. Ambos microservicios, 

   * **PERSON-USER-MS:** Servicios de cliente y clase persona, conecta a una base de datos MariaDB
   * **TRANSACTION-ACCOUNT-MS:** Servicio de manejo de cuentas y transacciones, se conecta a una base de datos PostgreSQL

5. Cada microservicio presenta una arquitectura de puertos y adaptadores
6. Los test unitarios y de integracion se encuentran en el proyecto **TRANSACTION-ACCOUNT-MS**

### INDICACIONES PREVIAS
* Gateway starter server esta obsoleto en la actual version, la herramienta actual es reactiva (webflux) 
con configuraciones distintas, por eso cada endpoint tiene el nombre de su servicio como primer prefijo
```html
   transaction-account-ms/api/v1/movements/1
```
* Las pruebas fueron realizadas en postman, su respaldo se encuentra dentro de el directorio postman, es una collection 2.1
* Se utiliza el modelo de desarrollo codigo primero, la generaciòn de entidades se hace automaticamente
* La creación de las bases de datos es **create-drop**, al reiniciar los datos anteriormente guardados se perderan, si quieres datos de prueba puedes insertar los que se encuentran en la carpeta resources/inserts.sql
* Se paciente, lo ultimo en ejecutarse es el api-gateway, este espera a que todo este iniciado, por lo cual se demora en responder
* El proyecto es extenso, si quieres ver el desarrollo, con errores cometidos en el proceso, puedes ver el historico y las ramas del git

### EJECUCION  
#### Docker desktop 
1. Cada proyecto maneja su Dockerfile, asi que para procesar todo ejecutamos
```shell
docker compose up -d --build
```

2. Se paciente, el api-gateway espera a que todos los proyectos arranquen para ejecutarse, por lo cual sera el ultimo en iniciar
3. Al compilar todo requieres un buen espacio en el disco

