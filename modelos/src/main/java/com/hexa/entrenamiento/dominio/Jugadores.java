package com.hexa.entrenamiento.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "jugadores", catalog = "hexa.dbo")
public class Jugadores {
    private Integer idJugador;
    private String codigo;
    private String nombres;
    private String camiseta;
    private String campeonatos;
    private Integer idEquipo;
    private Equipos equipos;
    private String codigoEquipo;
    private String nombreEquipo;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdJugador", nullable = false)
    public Integer getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Integer idJugador) {
        this.idJugador = idJugador;
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
    @Column(name = "Nombres", nullable = false)
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Basic
    @Column(name = "Camiseta", nullable = false)
    public String getCamiseta() {
        return camiseta;
    }

    public void setCamiseta(String camiseta) {
        this.camiseta = camiseta;
    }

    @Basic
    @Column(name = "Campeonatos", nullable = false)
    public String getCampeonatos() {
        return campeonatos;
    }

    public void setCampeonatos(String campeonatos) {
        this.campeonatos = campeonatos;
    }

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
    @JoinColumn(name = "IdEquipo", referencedColumnName = "IdEquipo", insertable = false, updatable = false)
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

    public void setCodigoEquipo(String codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }

    @Transient
    public String getNombreEquipo() {
        return equipos != null ? equipos.getNombre() : null;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }
}
