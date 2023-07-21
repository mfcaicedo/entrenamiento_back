package com.hexa.entrenamiento.dominio.hexa.controllers;

import com.hexa.entrenamiento.dominio.Equipos;
import com.hexa.entrenamiento.dominio.repositories.EquiposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/equipos")
public class EquiposController {

    @Autowired
    private EquiposRepository repository;


    @RequestMapping(method = RequestMethod.GET)
    List<Equipos> findAll() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Equipos findOne(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    Equipos create(@RequestBody Equipos o) {
        o = repository.save(o);
        return o;
    }

    @RequestMapping(method = RequestMethod.PUT)
    Equipos update(@RequestBody Equipos o) {
        return create(o);
    }
}
