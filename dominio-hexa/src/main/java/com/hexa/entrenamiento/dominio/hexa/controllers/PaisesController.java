package com.hexa.entrenamiento.dominio.hexa.controllers;

import com.hexa.entrenamiento.dominio.Paises;
import com.hexa.entrenamiento.dominio.repositories.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/paises")
public class PaisesController {

    @Autowired
    private PaisesRepository repository;
    /**
     * Listar todos los paises registrados GET
     * @return Lista de paises
     */
    @RequestMapping(method = RequestMethod.GET)
    List<Paises> findAll() {
        return repository.findAll();
    }
    /**
     * Buscar un paies registrado GET
     * @param id Identificador del pais a buscar
     * @return Objeto pais encontrado
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Paises findById(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }
    /**
     * Crear un pais POST
     * @param o Objeto pais a crear
     * @return Objeto pais creado
     */
    @RequestMapping(method = RequestMethod.POST)
    Paises create(@RequestBody Paises o) {
        //validar que la llave primaria sea única y no exista
        repository.findById(o.getIdPais()).ifPresent((paises) -> {
            throw new RuntimeException("El pais ya existe");
        });
        //sino existe se crea
        o = repository.save(o);
        return o;
    }
    /**
     * Actualizar un pais PUT
     * @param o Objeto pais a actualizar
     * @return Objeto pais actualizado
     */
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    Paises update(@RequestBody Paises o, @PathVariable Integer id) {
        //valido que exista el pais
        if (!repository.existsById(id)) {
            throw new RuntimeException("El pais no existe");
        }
        //actualizo el pais
        o = repository.save(o);
        return o;
    }
    /**
     * Crear - Actualizar multiples paises POST
     * @param listPaises Lista de paises a crear - actualizar
     * @return Lista de paises creados - actualizados
     */
    @RequestMapping(method = RequestMethod.POST, path = "/all")
    List<Paises> createAll(List<Paises> listPaises) {
        //validar que la llave primaria sea única y no exista
        for(Paises pais : listPaises){
            repository.findById(pais.getIdPais()).ifPresent((paises) -> {
                throw new RuntimeException("El pais ya existe");
            });
        }
        //Solo si todos los paises no existen se crean
        List<Paises> paises = repository.saveAll(listPaises);
        return paises;
    }
}
