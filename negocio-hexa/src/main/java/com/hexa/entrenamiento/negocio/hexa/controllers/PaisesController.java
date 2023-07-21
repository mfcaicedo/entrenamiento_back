package com.hexa.entrenamiento.negocio.hexa.controllers;

import com.hexa.entrenamiento.negocio.Paises;
import com.hexa.entrenamiento.negocio.hexa.Utilities.GeneralUtilitiesHexa;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hexa.entrenamiento.utilities.GeneralUtilities.domainHexa;

@RestController
@RequestMapping("/api/paises")
public class PaisesController {
    private final String domain = domainHexa + "paises/";

    @RequestMapping(method = RequestMethod.GET)
    List<Paises> findAll() {
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.getForObject(domain, Paises[].class)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Paises findById(@PathVariable String id) {
        return GeneralUtilitiesHexa.restTemplate.getForObject(domain + id, Paises.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    Paises create(@RequestBody Paises o) {
        return GeneralUtilitiesHexa.restTemplate.postForObject(domain, o, Paises.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    Paises update(@RequestBody Paises o) {
        return create(o);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/all")
    List<Paises> createAll(@RequestBody List<Paises> listPaises){
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.postForObject(domain + "all",listPaises,Paises[].class)));
    }
}
