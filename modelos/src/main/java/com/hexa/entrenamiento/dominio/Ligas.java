package com.hexa.entrenamiento.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ligas", catalog = "hexa.dbo")
public class Ligas {
    private Integer idLiga;
    private String codigo;
    private String nombre;
    private String sponsor;
    //atributos para la relación con Pais.
    private Integer idPais;
    private Paises paises;
    private String codigoPais;
    private String nombrePais;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLiga", nullable = false)
    public Integer getIdLiga() {
        return idLiga;
    }
    public void setIdLiga(Integer idLiga) {
        this.idLiga = idLiga;
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
    @Basic
    @Column(name = "Sponsor", nullable = false)
    public String getSponsor() {
        return sponsor;
    }
    public void setSponsor(String sponsor){
        this.sponsor = sponsor;
    }
    //atributos para la relación con Pais.
    @Basic
    @Column(name = "IdPais", nullable = false)
    public Integer getIdPais() {
        return idPais;
    }
    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "IdPais", referencedColumnName = "IdPais", nullable = false, insertable = false, updatable = false)
    public Paises getPaises() {
        return paises;
    }
    public void setPaises(Paises paises) {
        this.paises = paises;
    }
    @Transient
    public String getCodigoPais() {
        return paises != null ? paises.getCodigo() : null;
    }
    @Transient
    public String getNombrePais() {
        return paises != null ? paises.getNombre() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
        return false;
        Ligas ligas = (Ligas) o;
        return Objects.equals(idLiga, ligas.idLiga) && Objects.equals(codigo, ligas.codigo) && Objects.equals(nombre, ligas.nombre) && Objects.equals(sponsor, ligas.sponsor) && Objects.equals(idPais, ligas.idPais);
    }
    @Override
    public int hashCode() {
        return Objects.hash(idLiga, codigo, nombre, sponsor, idPais);
    }

}
