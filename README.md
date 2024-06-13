# ms-springboot
Repo para hacer seguimiento a curso de ms con springboot


### 3 [Instalacion JDK Java y Variables de entorno](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372900#content)

### 4 [Instalación SpringBoot y configuración workspace](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372904#overview)

  *    Maven viene embebido en eclipse

### 5 - [Introduccion MS](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372908#overview) 

  * Conjunto componentes pequeños y autonomos y que colaboran entre si.
  * Se registra cada puerto y nombre de ms en un server
  * Si requiere mas carga se pueden desplegar mas instncias
  * Tolerancia a fallos para evitar error en cascada, crea caminos alternativos
  * Balanceo de cargas (Ribbon)
  * Configuración centralizada (Spring cloud config) para centralizar configuraciones de cada ms
  * Reduccion de costos

### 6 [Actualizacion estrucutura para seleccionar las dependencias sprinboot](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372910#overview)

* Antes  =====> Ahora
* DevTools ===> Developer Tools
* Web ======> Web > Spring web starter

### 7 [Creacion ms productos](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372914#content)

 - Opcion 1 Atajo Crear Nuevo starter project 
 - Opcion 1 file > new > Spring Starter Proyect
 - Nombre => nombre ms
 - Type Maven (Dependencias)
 - Packaging Jar (Tipo Empaquetado), porque STS tiene embebido el servidor Tomcat
 - Java Version Ultima
 - Group similar a lo que es un package de java  com.productos
Siguiente
  - Ultima Version
  - Spring Boot Dev Tools
  - Spring Data JPA
  - Spring web
  - H2 BD (BD Memoria virtual prueba)
Finalizar y Listo

Package principal
@SpringBootApplication Arranca la configuración Spring
MsProductosApplication

Control cick en @SpringBootApplication y se puede ver lo sigte
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan escane todo los componentes de spring

### 8 [Añnadiendo Clase Entity producto](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372918#overview)

Configuracion minima del aplication.properties  para ms
   - spring.application.name=ms-productos   (Minuscula, sin caracteres especiales y con guion medio)
   - server.port=8001 (Por defecto spring usa 8080)
En el pom.xml se deben tener las dependencias

``` xml

  <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>

```

Dentro del package principal  com.app.productos
 - Click Derecho, > new package > Name = com.app.productos.models.entity

En el nuevo package new class > nombre = Producto
 - En la nueva clase se agrega la notacion de @Entity y @Table PPARA USAR JPA
 - Se crean 4 variables con sus repectivos getter and setter (click derecho source > generate getter)
 - Se indica cual es ka key primaria
 - En caso que no la coluna tenga nombre diferente al atributo , se indica el nombre con @colum
 - Implementar interface serializable para convertir la clase en bites (opcional)
 - Generate serial versionId

### 9 [Creando Repositorio JPA productos](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372920#overview)
  
 



