package com.hexa.entrenamiento.negocio.hexa.controllers;

import com.hexa.entrenamiento.negocio.Jugadores;
import com.hexa.entrenamiento.negocio.hexa.Utilities.GeneralUtilitiesHexa;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hexa.entrenamiento.utilities.GeneralUtilities.domainHexa;

@RestController
@RequestMapping("/api/jugadores")
public class JugadoresController {
    private final String domain = domainHexa + "jugadores/";

    @RequestMapping(method = RequestMethod.GET)
    List<Jugadores> findAll() {
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.getForObject(domain, Jugadores[].class)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Jugadores findOne(@PathVariable String id) {
        return GeneralUtilitiesHexa.restTemplate.getForObject(domain + id, Jugadores.class);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/codigo/{codigo}")
    Jugadores findByCodigo(@PathVariable String codigo) {
        return GeneralUtilitiesHexa.restTemplate.getForObject(domain + "codigo/" + codigo, Jugadores.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    Jugadores create(@RequestBody Jugadores o) {
        return GeneralUtilitiesHexa.restTemplate.postForObject(domain, o, Jugadores.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    Jugadores update(@RequestBody Jugadores o) {
        return create(o);
    }
}
