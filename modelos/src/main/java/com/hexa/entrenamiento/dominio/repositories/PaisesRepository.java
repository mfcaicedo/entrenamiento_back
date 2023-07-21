package com.hexa.entrenamiento.dominio.repositories;

import com.hexa.entrenamiento.dominio.Paises;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface PaisesRepository extends JpaRepository<Paises, Integer> {

    Paises findByNombre(String nombre);
}
