package com.hexa.entrenamiento.dominio.hexa.controllers;

import com.hexa.entrenamiento.dominio.Jugadores;
import com.hexa.entrenamiento.dominio.repositories.JugadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/api/jugadores")
public class JugadoresController {

    @Autowired
    private JugadoresRepository repository;


    @RequestMapping(method = RequestMethod.GET)
    List<Jugadores> findAll() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Jugadores findOne(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/codigo/{codigo}")
    Jugadores findByCode(@PathVariable String codigo) {
        return repository.findFirstByCodigo(codigo);
    }

    @RequestMapping(method = RequestMethod.POST)
    Jugadores create(@RequestBody Jugadores o) {
        o = repository.save(o);
        return o;
    }

    @RequestMapping(method = RequestMethod.PUT)
    Jugadores update(@RequestBody Jugadores o) {
        return create(o);
    }
}
