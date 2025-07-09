package com.springboot.reactor.controller;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

import com.springboot.reactor.models.dao.ProductoDao;
import com.springboot.reactor.models.documents.Producto;

import reactor.core.publisher.Flux;

@Controller
public class ProductoController {

  private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

  @Autowired
  private ProductoDao productoDao;

  @GetMapping({ "/listar", "/" })
  public String listar(Model model) {
    Flux<Producto> productos = productoDao.findAll().map(producto -> {
      producto.setNombre(producto.getNombre().toUpperCase());
      return producto;
    });

    productos.subscribe(prod -> log.info("Producto: " + prod.getNombre() + " - Precio: " + prod.getPrecio()));

    model.addAttribute("productos", productos);
    model.addAttribute("titulo", "Listado de Productos");
    return "listar";
  }

  @GetMapping("/listar-datadriver")
  public String listarDataDriver(Model model) {

    Flux<Producto> productos = productoDao.findAll().map(producto -> {

      producto.setNombre(producto.getNombre().toUpperCase());
      return producto;
    }).delayElements(Duration.ofSeconds(1));

    productos.subscribe(prod -> log.info(prod.getNombre()));

    model.addAttribute("productos", new ReactiveDataDriverContextVariable(productos, 1));
    model.addAttribute("titulo", "Listado de productos");
    return "listar";
  }

  @GetMapping("/listar-full")
  public String listarFull(Model model) {

    Flux<Producto> productos = productoDao.findAll().map(producto -> {

      producto.setNombre(producto.getNombre().toUpperCase());
      return producto;
    }).repeat(5000);

    model.addAttribute("productos", productos);
    model.addAttribute("titulo", "Listado de productos");
    return "listar";
  }

  @GetMapping("/listar-chunked")
  public String listarChunked(Model model) {

    Flux<Producto> productos = productoDao.findAll().map(producto -> {

      producto.setNombre(producto.getNombre().toUpperCase());
      return producto;
    }).repeat(5000);

    model.addAttribute("productos", productos);
    model.addAttribute("titulo", "Listado de productos");
    return "listar-chunked";
  }
}
