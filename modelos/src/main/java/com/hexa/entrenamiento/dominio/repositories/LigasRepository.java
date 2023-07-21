package com.hexa.entrenamiento.dominio.repositories;

import com.hexa.entrenamiento.dominio.Ligas;
import com.hexa.entrenamiento.dominio.Paises;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigasRepository extends JpaRepository<Ligas, Integer> {

    List<Ligas> findAllByPaises_Nombre(String nombrePais);

    List<Ligas> findAllByPaises_IdPais(Integer idPais);

}
