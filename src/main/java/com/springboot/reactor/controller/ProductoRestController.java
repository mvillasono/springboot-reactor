package com.springboot.reactor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.reactor.models.dao.ProductoDao;
import com.springboot.reactor.models.documents.Producto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

  private static final Logger log = LoggerFactory.getLogger(ProductoRestController.class);

  @Autowired
  private ProductoDao productoDao;

  // Devuelve todos los productos en formato JSON de manera reactiva
  @GetMapping
  public Flux<Producto> index() {

    Flux<Producto> productos = productoDao.findAll()
        .map(producto -> {
          producto.setNombre(producto.getNombre().toUpperCase());
          return producto;
        })
        .doOnNext(prod -> log.info(prod.getNombre()));

    return productos;
  }

  // Devuelve un producto por su id
  @GetMapping("/{id}")
  public Mono<Producto> show(@PathVariable String id) {

    /* Mono<Producto> producto = dao.findById(id); */

    Flux<Producto> productos = productoDao.findAll();

    Mono<Producto> producto = productos
        .filter(p -> p.getId().equals(id))
        .next()
        .doOnNext(prod -> log.info(prod.getNombre()));

    return producto;
  }
}
