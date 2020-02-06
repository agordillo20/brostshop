package com.gordillo.adrian.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "especificaciones")
public class Especificaciones implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(mappedBy = "especificaciones")
    private Producto producto;
    private int memoriaRam;
    private String procesador;
    private String sistemaOperativo;
    private int almacenamientoInterno;
    private String resolucionPantalla;

    public Especificaciones() {
    }
}
