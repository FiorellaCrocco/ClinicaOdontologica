# Clínica Odontológica
Este proyecto es una aplicación de gestión para una clínica odontológica desarrollada en Java con Spring Boot, empleando el patrón MVC. 
Además de HTML, Bootstrap y JavaScript para la capa de presentación.

## Características
* Gestión de pacientes: registro, actualización y eliminación de información de los pacientes.
* Gestión de odontólogos: registro, actualización y eliminación de información de los odontólogos.
* Gestión de turnos: registro, consulta y eliminación de turnos.
* Persistencia de datos: ORM y H2.
* Seguridad: autenticación y autorización de usuarios con roles de administrador y usuario.
* Logueo de errores: utiliza Apache Log4J.
* Tests: JUnit para los testeos unitarios.
* API documentada: utiliza Springdoc OpenAPI UI para documentar la API REST.

## Requisitos previos
* Java 11 o superior instalado.
* Maven para la gestión de dependencias.
* Docker instalado (Opcional, solo si deseas ejecutar la aplicación en contenedores Docker).

## Instalación
1. Clona el repositorio del proyecto: git clone https://github.com/FiorellaCrocco/clinica-odontologica.git
2. Navega al directorio del proyecto: cd clinica-odontologica
3. Compila y empaqueta el proyecto utilizando Maven: mvn clean package
4. (Opcional) Si deseas utilizar Docker, asegúrate de tener Docker instalado y sigue los pasos de configuración y uso de Docker a continuación.

## Configuración
Antes de ejecutar la aplicación, asegúrate de revisar y configurar adecuadamente los siguientes archivos de configuración:
* application.properties: Archivo de configuración principal que incluye la configuración de la base de datos.
* log4J.properties: Archivo de configuración para el logueo de errores y otros mensajes.

## Uso
1. Ejecuta la aplicación: java -jar target/clinica-odontologica.jar
2. Abre un navegador web y accede a la siguiente URL para explorar la API documentada: http://localhost:8080/swagger-ui.html

## Uso con Docker **(opcional)**
Si deseas ejecutar la aplicación utilizando Docker, sigue los pasos a continuación:
1. Asegúrate de tener Docker instalado y en ejecución.
2. Construye la imagen Docker utilizando el siguiente comando: docker build -t clinica-odontologica .
3. Ejecuta el contenedor Docker utilizando el siguiente comando: docker run -p 8080:8080 clinica-odontologica
4. Abre un navegador web y accede a la siguiente URL para explorar la API documentada: http://localhost:8080/swagger-ui.html
