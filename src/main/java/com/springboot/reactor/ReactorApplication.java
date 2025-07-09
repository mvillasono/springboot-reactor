package com.springboot.reactor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.reactor.models.Usuario;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactorApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(ReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Lista de nombres completos a procesar de manera reactiva
		List<String> nombresList = List.of("Juan Valdez", "Claudio Sono", "Martin Villa", "Maria Ratamanche");
		// Se crea un Flux a partir de la lista de nombres
		Flux<String> nombres = Flux.fromIterable(nombresList);

		// Se transforma el Flux de String a Flux de Usuario, aplicando operaciones
		// reactivas
		Flux<Usuario> usuarios = nombres
				// Se separa el nombre y apellido, y se convierten a mayúsculas
				.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(), nombre.split(" ")[1].toUpperCase()))
				// Se filtra solo el usuario cuyo nombre sea "martin" (ignorando
				// mayúsculas/minúsculas)
				.filter(usuario -> usuario.getNombre().toLowerCase().equals("martin"))
				// Se ejecuta una acción secundaria por cada elemento emitido (logging y
				// validación)
				.doOnNext(usuario -> {
					if (usuario.getNombre().isEmpty()) {
						throw new RuntimeException("El nombre no puede estar vacío");
					}
					System.out.println("Nombre recibido: " + usuario.getNombre());
					System.out.println("Apellido recibido: " + usuario.getApellido());
				})
				// Se transforma el nombre a minúsculas y se valida un caso de error específico
				.map(usuario -> {
					if (usuario.getNombre().equals("Claudioooo")) {
						throw new RuntimeException("Error procesando el nombre Claudio");
					}
					String nombre = usuario.getNombre().toLowerCase();
					usuario.setNombre(nombre);
					return usuario;
				});

		// Se suscribe al flujo y se manejan los eventos de siguiente elemento, error y
		// finalización
		usuarios.subscribe(e -> log.info(e.toString()),
				error -> log.error("Error: " + error.getMessage()),
				new Runnable() {
					@Override
					public void run() {
						log.info("Proceso completado");
					}
				});
	}
}
