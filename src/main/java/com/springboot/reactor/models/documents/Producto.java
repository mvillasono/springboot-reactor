package com.springboot.reactor.models.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "productos")
public class Producto {

  @Id
  private String id;
  private String nombre;
  private Double precio;
  private Date createAt;

  public Producto(String nombre, Double precio) {
    this.nombre = nombre;
    this.precio = precio;
  }
}
