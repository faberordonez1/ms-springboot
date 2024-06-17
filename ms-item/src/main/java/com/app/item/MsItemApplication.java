package com.app.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //habilitar clientes feign del proyecto y nos habilita la injeccion de dependencias en controladores o componentes de spring
@SpringBootApplication
public class MsItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsItemApplication.class, args);
	}

}
