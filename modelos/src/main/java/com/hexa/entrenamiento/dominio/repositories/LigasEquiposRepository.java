package com.hexa.entrenamiento.dominio.repositories;

import com.hexa.entrenamiento.dominio.LigasEquipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.xml.ws.Response;
import java.util.List;

public interface LigasEquiposRepository extends JpaRepository<LigasEquipos, Integer> {

    /**
     * Cantidad de eqipos por cada liga
     */
    /*SELECT hexa.dbo.ligas.nombre, COUNT(hexa.dbo.ligasequipos.idEquipo) AS cantidad_equipos
      FROM hexa.dbo.ligasequipos AS lq
      INNER JOIN hexa.dbo.ligas ON lq.IdLiga = hexa.dbo.ligas.idLiga
      GROUP BY hexa.dbo.ligas.nombre
      ORDER BY cantidad_equipos DESC;*/
    long countByLigas_IdLigaOrderByEquiposDesc(Integer idLiga);

    /**
     * Cantidad de equipos por cada pais
     */
    /*@Query(value = "SELECT hexa.dbo.Paises.nombre, COUNT(hexa.dbo.LigasEquipos.idEquipo) AS cantidad_equipos " +
            "FROM hexa.dbo.LigasEquipos INNER JOIN hexa.dbo.Ligas " +
            "ON hexa.dbo.LigasEquipos.idLiga = hexa.dbo.Ligas.idLiga " +
            "INNER JOIN hexa.dbo.Paises ON hexa.dbo.Ligas.idPais = hexa.dbo.Paises.idPais " +
            "GROUP BY hexa.dbo.Paises.nombre " +
            "ORDER BY cantidad_equipos DESC, hexa.dbo.Paises.nombre ASC", nativeQuery = true)*/
    long countByLigas_Paises_IdPaisOrderByLigas_Paises_NombreAsc(Integer idPais);

}
