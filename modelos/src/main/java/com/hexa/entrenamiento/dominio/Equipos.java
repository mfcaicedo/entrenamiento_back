package com.hexa.entrenamiento.dominio;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipos", catalog = "hexa.dbo")
public class Equipos {
    private Integer idEquipo;
    private String codigo;
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEquipo", nullable = false)
    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Basic
    @Column(name = "Codigo", nullable = false)
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "Nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipos equipos = (Equipos) o;
        return Objects.equals(idEquipo, equipos.idEquipo) && Objects.equals(codigo, equipos.codigo) && Objects.equals(nombre, equipos.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEquipo, codigo, nombre);
    }
}

