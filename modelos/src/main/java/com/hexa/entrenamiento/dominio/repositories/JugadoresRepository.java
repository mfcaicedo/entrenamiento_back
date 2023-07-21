package com.hexa.entrenamiento.dominio.repositories;

import com.hexa.entrenamiento.dominio.Jugadores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadoresRepository extends JpaRepository<Jugadores, Integer> {

    Jugadores findFirstByCodigo(String codigo);

}
