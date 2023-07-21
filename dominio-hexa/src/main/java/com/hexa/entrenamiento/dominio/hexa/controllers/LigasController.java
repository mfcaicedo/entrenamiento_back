package com.hexa.entrenamiento.dominio.hexa.controllers;

import com.hexa.entrenamiento.dominio.Ligas;
import com.hexa.entrenamiento.dominio.Paises;
import com.hexa.entrenamiento.dominio.repositories.LigasRepository;
import com.hexa.entrenamiento.dominio.repositories.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/ligas")
public class LigasController {

    @Autowired
    private LigasRepository repository;
    @Autowired
    private PaisesRepository repositoryPaises;
    /**
     * Listar todos las ligas registradas GET
     * @return Lista de ligas
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Ligas> findAll() {
        return repository.findAll();
    }
    /**
     * Buscar una liga registrada GET
     * @param id Identificador de la liga a buscar
     * @return Objeto liga encontrada
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Ligas findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }
    /**
     * Crear una liga POST
     * @param o Objeto liga a crear
     * @return Objeto liga creado
     */
    @RequestMapping(method = RequestMethod.POST)
    Ligas create(@RequestBody Ligas o) {
        //validar que la llave primaria sea única y no exista
        repository.findById(o.getIdLiga()).ifPresent((ligas) -> {
            throw new RuntimeException("La liga ya existe");
        });
        //sino existe se crea la liga
        o = repository.save(o);
        return o;
    }
    /**
     * Actualizar una liga PUT
     * @param o Objeto liga a actualizar
     * @return Objeto liga actualizado
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    Ligas update(@RequestBody Ligas o, @PathVariable Integer id) {
        //valido que exista el pais
        if(repository.findById(id).isPresent() == false){
            throw new RuntimeException("La liga que deseas actualizar no existe");
        }
        //actualizo la liga si existe
        o = repository.save(o);
        return o;
    }
    /**
     * Crear - Actualizar multiples ligas POST
     * @param listLigas Lista de ligas a crear - actualizar
     * @return Lista de ligas creadas - actualizadas
     */
    @RequestMapping(method = RequestMethod.POST, path = "/all")
    List<Ligas> createAll(List<Ligas> listLigas) {
        //validar que la llave primaria sea única y no exista
        for(Ligas ligs : listLigas){
            repository.findById(ligs.getIdLiga()).ifPresent((ligas) -> {
                throw new RuntimeException("La liga ya existe");
            });
        }
        //Solo si todos las ligas no existen se crean
        List<Ligas> ligas = repository.saveAll(listLigas);
        return ligas;
    }

    /**
     * Buscar ligas que pertenecen a un pais que se pasa como parametro "id" GET
     * @param idPais Identificador del pais a buscar sus ligas
     * @return Lista de ligas encontradas del Pais correspondiente
     */
    @RequestMapping(method = RequestMethod.GET, path = "/paisById/{idPais}")
    List<Ligas> findByIdPais(@PathVariable Integer idPais) {
        Paises pais = repositoryPaises.findById(idPais).orElse(null);
        if (pais == null) {
            throw new RuntimeException("El pais no existe");
        }
        return repository.findAllByPaises_IdPais(idPais);
    }


    /**
     * Buscar ligas que pertenecen a un pais que se pasa como parametro "Cadeda de texto" GET
     * @param nombrePais Nombre del pais a buscar sus ligas
     * @return Lista de ligas encontradas del Pais correspondiente
     */
    @RequestMapping(method = RequestMethod.GET, path = "/pais/{nombrePais}")
    List<Ligas> findByPais(@PathVariable String nombrePais) {
        return repository.findAllByPaises_Nombre(nombrePais);
    }

}
