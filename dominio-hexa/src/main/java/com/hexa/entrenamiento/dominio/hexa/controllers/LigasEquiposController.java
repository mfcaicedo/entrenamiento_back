package com.hexa.entrenamiento.dominio.hexa.controllers;

import com.hexa.entrenamiento.dominio.Ligas;
import com.hexa.entrenamiento.dominio.LigasEquipos;
import com.hexa.entrenamiento.dominio.Paises;
import com.hexa.entrenamiento.dominio.repositories.LigasEquiposRepository;
import com.hexa.entrenamiento.dominio.repositories.LigasRepository;
import com.hexa.entrenamiento.dominio.repositories.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping("/api/ligas-equipos")
public class LigasEquiposController {
    @Autowired
    private LigasEquiposRepository repository;
    @Autowired
    private PaisesRepository paisesRepository;
    @Autowired
    private LigasRepository ligasRepository;

    /**
     * Listar todos las ligasEquipos registrados GET
     * @return Lista de ligasEquipos
     */
    @RequestMapping(method = RequestMethod.GET)
    List<LigasEquipos> findAll() {
        return repository.findAll();
    }
    /**
     * Buscar una ligaEquipo registrada GET
     * @param id Identificador de la ligaEquipo a buscar
     * @return Objeto ligaEquipo encontrada
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    LigasEquipos findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }
    /**
     * Crear una ligaEquipo POST
     * @param o Objeto ligaEquipo a crear
     * @return Objeto ligaEquipo creado
     */
    @RequestMapping(method = RequestMethod.POST)
    LigasEquipos create(@RequestBody LigasEquipos o) {
        //validar que la llave primaria sea única y no exista
        repository.findById(o.getIdLigaEquipo()).ifPresent((ligasEquipos) -> {
            throw new RuntimeException("La ligaEquipo ya existe");
        });
        //sino existe se crea la ligaEquipo
        o = repository.save(o);
        return o;
    }
    /**
     * Actualizar una ligaEquipo PUT
     * @param o Objeto ligaEquipo a actualizar
     * @return Objeto ligaEquipo actualizada
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    LigasEquipos update(@RequestBody LigasEquipos o, @PathVariable Integer id) {
        //valido que exista la ligaEquipo
        if(repository.findById(id) == null){
            throw new RuntimeException("La ligaEquipo que deseas actualizar no existe");
        }
        //actualizo la ligaEquipo si existe
        o = repository.save(o);
        return o;
    }
    /**
     * Crear - Actualizar multiples ligasEquipos POST
     * @param listLigasEquipos Lista de ligasEquipos a crear - actualizar
     * @return Lista de ligasEquipos creadas - actualizadas
     */
    @RequestMapping(method = RequestMethod.POST, path = "/all")
    List<LigasEquipos> createAll(List<LigasEquipos> listLigasEquipos) {
        //validar que la llave primaria sea única y no exista
        for(LigasEquipos ligsEquipos : listLigasEquipos){
            repository.findById(ligsEquipos.getIdLigaEquipo()).ifPresent((ligasEquipos) -> {
                throw new RuntimeException("La ligaEquipo ya existe");
            });
        }
        //Solo si todos las ligasEquipos NO existen se crean
        List<LigasEquipos> ligasEquipos = repository.saveAll(listLigasEquipos);
        return ligasEquipos;
    }

    /**
     * Cantidad de equipos por cada liga
     * @return Lista clave valor con la cantidad de equipos por cada liga con orden descendente
     */
    @RequestMapping(method = RequestMethod.GET, path = "/ligas/cantidad-equipos")
    List<HashMap<String, Long>> findAllByEquiposLigas() {
        HashMap<String, Long> map = new HashMap<>();
        List<HashMap<String, Long>> listMap = new ArrayList<>();
        long cantidadEquipos = 0;
        //Consultar las ligas existentes
        List<Ligas> ligas =  ligasRepository.findAll();
        //Quito ligas repetidas con filter
        List<Ligas> ligasSinDuplicar = ligas.stream()
                .collect(Collectors.toMap(Ligas::getNombre, liga -> liga, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toList());
        //Consulto la cantidad de equipos por cada liga
        for(Ligas liga : ligasSinDuplicar){
            //Consulto la cantidad de equipos por cada liga
            cantidadEquipos = repository.countByLigas_IdLigaOrderByEquiposDesc(liga.getIdLiga());
            //Agrego a la lista el nombre de la liga y la cantidad de equipos
            map.put(liga.getNombre(), cantidadEquipos);
        }
        listMap.add(map);
        return listMap;
    }
    /**
     * Cantidad de equipos por cada pais
     * @return Lista clave valor con la cantidad de equipos por cada pais
     */
    @RequestMapping(method = RequestMethod.GET, path = "/paises/cantidad-equipos")
    List<HashMap<String, Long>> findAllByEquiposPaises() {
        long cantidadEquipos = 0;
        //Consultar los paises existentes
        List<Paises> paises =  paisesRepository.findAll();
        //Quito paises repetidos con filter
        List<Paises> paisesSinDuplicar = paises.stream()
                .collect(Collectors.toMap(Paises::getNombre, pais -> pais, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toList());
        HashMap<String, Long> map = new HashMap<>();
        List<HashMap<String, Long>> listMap = new ArrayList<>();
        //Consultar la cantidad de equipos por cada pais
        for (Paises pais : paisesSinDuplicar) {
            cantidadEquipos = repository.countByLigas_Paises_IdPaisOrderByLigas_Paises_NombreAsc(pais.getIdPais());
            map.put(pais.getNombre(), cantidadEquipos);
        }
        listMap.add(map);
        return listMap;
    }

}
