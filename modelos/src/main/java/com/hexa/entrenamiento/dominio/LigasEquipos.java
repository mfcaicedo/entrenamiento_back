package com.hexa.entrenamiento.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ligasequipos", catalog = "hexa.dbo")
public class LigasEquipos {
    private Integer idLigaEquipo;
    //Relacion con la tabla ligas * a 1
    private Integer idLiga;
    private Ligas ligas;
    private String codigoLiga;
    private String nombreLiga;
    //Relacion con la tabla equipos * a 1
    private Integer idEquipo;
    private Equipos equipos;
    private String codigoEquipo;
    private String nombreEquipo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLigaEquipo", nullable = false)
    public Integer getIdLigaEquipo() {
        return idLigaEquipo;
    }
    public void setIdLigaEquipo(Integer idLigaEquipo) {
        this.idLigaEquipo = idLigaEquipo;
    }
    //relacion con la tabla ligas
    @Basic
    @Column(name = "IdLiga", nullable = false)
    public Integer getIdLiga() {
        return idLiga;
    }
    public void setIdLiga(Integer idLiga) {
        this.idLiga = idLiga;
    }
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "IdLiga", referencedColumnName = "IdLiga", nullable = false, insertable = false, updatable = false)
    public Ligas getLigas() {
        return ligas;
    }
    public void setLigas(Ligas ligas) {
        this.ligas = ligas;
    }
    @Transient
    public String getCodigoLiga() {
        return ligas != null ? ligas.getCodigo() : null;
    }
    @Transient
    public String getNombreLiga() {
        return ligas != null ? ligas.getNombre() : null;
    }
    //relacion con la tabla equipos
    @Basic
    @Column(name = "IdEquipo", nullable = false)
    public Integer getIdEquipo() {
        return idEquipo;
    }
    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "IdEquipo", referencedColumnName = "IdEquipo", nullable = false, insertable = false, updatable = false)
    public Equipos getEquipos() {
        return equipos;
    }
    public void setEquipos(Equipos equipos) {
        this.equipos = equipos;
    }
    @Transient
    public String getCodigoEquipo() {
        return equipos != null ? equipos.getCodigo() : null;
    }
    @Transient
    public String getNombreEquipo() {
        return equipos != null ? equipos.getNombre() : null;
    }

}
