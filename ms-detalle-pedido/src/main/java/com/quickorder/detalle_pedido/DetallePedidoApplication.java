package com.quickorder.detalle_pedido;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DetallePedidoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DetallePedidoApplication.class, args);
	}

}
