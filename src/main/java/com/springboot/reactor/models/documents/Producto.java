package com.springboot.reactor.models.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok genera constructores, getters y setters automáticamente
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "productos") // Indica que esta clase es un documento de MongoDB
public class Producto {

  @Id // Identificador único en MongoDB
  private String id;
  private String nombre;
  private Double precio;
  private Date createAt;

  // Constructor para crear productos con nombre y precio
  public Producto(String nombre, Double precio) {
    this.nombre = nombre;
    this.precio = precio;
  }
}
