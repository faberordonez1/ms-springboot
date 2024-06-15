# ms-springboot
Repo para hacer seguimiento a curso de ms con springboot


### 3 [Instalacion JDK Java y Variables de entorno](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372900#content)

### 4 [Instalación SpringBoot y configuración workspace](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372904#overview)

  *    Maven viene embebido en Eclipse

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

En el nuevo package new class > nombre = Producto //Enlazar file Producto implements Serializable
 - En la nueva clase se agrega la notacion de @Entity y @Table PPARA USAR JPA
 - Se crean 4 variables con sus repectivos getter and setter (click derecho source > generate getter)
 - Se indica cual es ka key primaria
 - En caso que no la coluna tenga nombre diferente al atributo , se indica el nombre con @colum
 - Implementar interface serializable para convertir la clase en bites (opcional)
 - Generate serial versionId

### 9 [Creando Repositorio JPA productos](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372920#overview)

- Esta interface ya tiene construido los metodos de CRUD, en la doc se puede ver la estructura [Doc](https://docs.spring.io/spring-data/jpa/reference/repositories/core-concepts.html)
- Usando [@Query](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.at-query) para crear consultas SQL  custom
- Se crea la clase implementando la interface JpaRepository o CrudRepository, la diferencia JPA extiende de CrudRepository, pero agrega propiedades adicionales como paginador etc.
- En el package orincipal creamos un nuevo packe dentro de model nombre =com.app.productos.models.dao
- En el nuevo package se crea una nueva interface ProductoDao que extiende de CrudRepository, como primerargumento se pasa la entity creada en el punto anterior

### 10 [Creando Service para Productos](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372922#overview)

- Se crea un nuevo package nombre =com.app.productos.models.service
- En el nuevo package se crea una interface IProductoService y se implementan los sigtes metodos
	- findAll()
 	- findById(Long id)
- En el mismo package, se crea una clase IProductoServiceImpl y se agrega el @Service
	- A los métodos se le agrega @Transactional(readOnly = true) para que estE sincronzado con BD, SE IMPORTA de spring framwork
	- Se inyecta el ProductoDao @Autowired y se usa en los métodos

### 11 [Controlador Rest Productos](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372924#overview)

- Se crea un nuevo paquete controllers y dentro de este se crea la Clase ProductoController y se agrega el decorador @RestController (Transforma a JSON lo que retorna los metodos handler findAll) 
  	- Se inyecta productoService CON @Autowired
  	- Se crean 2 metodos get, para listar todos y listar por id
  	- Con esto ya se puede ver la API en el navegador [local](http://localhost:8001/)

``` java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.app.productos.models.entity.Producto;
import com.app.productos.models.service.IProductoService;

@RestController //Transforma a JSON lo que retorna los metodos handler findAll
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;//Tipo a convertir a JSON
	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return this.productoService.findAll();
	}
	
	@GetMapping("/listar/{id}")
	public Producto detalle(@PathVariable Long id){//PathVariable es para obtener el Id en la URL
		return this.productoService.findById(id);
	}

}
```

### 12 [Probando API en Postman](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372926#overview)

- En src > resource se crea un nueo archivo import.sql, sera para insertar datos de prueba
- En el aplication.properties activar el parametro spring.sql.init.mode=always
- Al lentar de nuevo el MS, se auto importa el script, debe tener el nombre indicado 

``` sql
INSERT INTO productos (nombre,precio,create_at) VALUES ('Panasonic',800, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Sony',700, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Apple',1000, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Sony Notebook',1000, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Hewlett-Packard',500, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Bianchi',600, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Nike',100, NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES ('Adidas',200,NOW())
INSERT INTO productos (nombre,precio,create_at) VALUES ('Reebok',300,NOW())
-- create_at debe ser igual a @Column de la clase Producto
-- Se omite id porque es autoincremental

```
### 13 [Creando MS Items](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372928#questions)

- Se crea un nuevo [ms item](ms-item) 
	-	nombre = ms-item 
 	- group y package = com.app.item
	- Se agrega dependencias DevTools y Web
- Se crean 1 nuevo paquete com.app.item.models
	- Se crea una clase [Producto](ms-item/src/main/java/com/app/item/model/Producto.java) igual a la creada en ms-productos, para almacenar los datos con la misma estructura

```java
package com.app.item.model;

import java.util.Date;

public class Producto {
	
	private Long id;
	private String nombre;
	private Double precio;
	private Date createAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
```
- Se crea otra clase [Item](ms-item/src/main/java/com/app/item/model/Item.java) 
	- Se crean las propiedaes producto y cantidad con sus getter and setter
	- Se crea un constructor vacio y un constructor con argumentos
	- Se crea un metodo getTotal()

```java
package com.app.item.model;

public class Item {

	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Item() {
	}

	private Producto producto;
	private Integer cantidad;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public Double getTotal() {
		return producto.getPrecio()*cantidad.doubleValue();
	}

}

```

### 14 [Creando Service Items y configurando el puerto](https://www.udemy.com/course/microservicios-con-spring-boot-y-spring-cloud/learn/lecture/15372932#questions)

Cofigurando puerto en el aplication.properties

```properties
spring.application.name=ms-item
server.port=8002
```


Se  configura  RestTemplate (para acceder a otros ms a traves de rest) que quede registrado como componente de spring, para ello se crea una clase de configuración

 - en el paquete principal se crea una nueva clase [AppConfig o RestTemplateConfig](/ms-item/src/main/java/com/app/item/AppConfig.java)
 	- Se anota clase con @Configuration (Permite crear objetos, componentes de spring Bean y se pueden registrar en el contenedor de spring)
	- Se crea registra el Bean RestTemplate y se le da una nombre @Bean("clienteRest"), este registro nos permite consumir otro ms a traves de rest
	- Ahora ya se puede injectar el RestTemplate en otra clase

```java
package com.app.item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean("clienteRest")
	public RestTemplate registarRestTemplate() {
		return new RestTemplate();
	}

}


```


Se crean 1 nuevo paquete [com.app.item.models.service](/ms-item/src/main/java/com/app/item/model/service/).  


* Se crea interface [ItemService](/ms-item/src/main/java/com/app/item/model/service/ItemService.java).  



```java
package com.app.item.model.service;
import java.util.List;

import com.app.item.model.Item;

public interface ItemService {
	public List<Item> findAll();
	public Item findById(Long id, Integer cantidad);

}
```

 - Se crea la clase que implementa la interface anterior [ItemServiceImpl](/ms-item/src/main/java/com/app/item/model/service/ItemServiceImpl.java)
	 - Se agrega el @Service (Fachada para acceder a los datos)
 	 - Se inyecta el RestTemplate con @Autowired

```java
package com.app.item.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.item.model.Item;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		// TODO Auto-generated method stub
		return null;
	}

}


```



