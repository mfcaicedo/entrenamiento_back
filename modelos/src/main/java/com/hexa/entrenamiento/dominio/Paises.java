package com.hexa.entrenamiento.dominio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "paises", catalog = "hexa.dbo")
public class Paises {
    private Integer idPais;
    private String codigo;
    private String nombre;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPais", nullable = false)
    public Integer getIdPais() {
        return idPais;
    }
    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }
    @Basic
    @Column(name = "Codigo", nullable = false)
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    @Basic
    @Column(name = "Nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
        return false;
        Paises paises = (Paises) o;
        return Objects.equals(idPais, paises.idPais) && Objects.equals(codigo, paises.codigo) && Objects.equals(nombre, paises.nombre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idPais, codigo, nombre);
    }

}
