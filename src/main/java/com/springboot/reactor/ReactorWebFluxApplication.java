package com.springboot.reactor;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.springboot.reactor.models.dao.ProductoDao;
import com.springboot.reactor.models.documents.Producto;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactorWebFluxApplication implements CommandLineRunner {
	@Autowired
	private ProductoDao productoDao;

	@Autowired
	private ReactiveMongoTemplate reactiveMongoTemplate;

	private static final Logger log = LoggerFactory.getLogger(ReactorWebFluxApplication.class);

	public static void main(String[] args) {
		// Inicia la aplicación Spring Boot
		SpringApplication.run(ReactorWebFluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Elimina la colección 'productos' al iniciar la aplicación
		reactiveMongoTemplate.dropCollection("productos").subscribe();

		// Crea algunos productos y los guarda en la base de datos de forma reactiva
		Flux.just(new Producto("Televisor LG", 450.0),
				new Producto("Refrigerador Samsung", 800.0),
				new Producto("Lavadora LG", 600.0),
				new Producto("Horno Microondas Panasonic", 150.0))
				.flatMap(producto -> {
					log.info("Producto creado: " + producto);
					producto.setCreateAt(new Date());
					return productoDao.save(producto);
				})
				.subscribe(producto -> {
					log.info("Producto procesado: " + producto);
				});
	}
}
