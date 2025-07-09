package com.springboot.reactor.models.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.springboot.reactor.models.documents.Producto;

// Interfaz DAO para operaciones CRUD reactivas sobre productos
public interface ProductoDao extends ReactiveMongoRepository<Producto, String> {
  // No additional methods are needed as ReactiveMongoRepository provides basic
  // CRUD operations

}
