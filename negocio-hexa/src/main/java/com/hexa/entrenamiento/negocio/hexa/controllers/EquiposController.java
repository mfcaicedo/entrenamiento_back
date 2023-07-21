package com.hexa.entrenamiento.negocio.hexa.controllers;

import com.hexa.entrenamiento.negocio.Equipos;
import com.hexa.entrenamiento.negocio.hexa.Utilities.GeneralUtilitiesHexa;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hexa.entrenamiento.utilities.GeneralUtilities.domainHexa;

@RestController
@RequestMapping("/api/equipos")
public class EquiposController {

    private final String domain = domainHexa + "equipos/";

    @RequestMapping(method = RequestMethod.GET)
    List<Equipos> findAll() {
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.getForObject(domain, Equipos[].class)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Equipos findOne(@PathVariable String id) {
        return GeneralUtilitiesHexa.restTemplate.getForObject(domain + id, Equipos.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    Equipos create(@RequestBody Equipos o) {
        return GeneralUtilitiesHexa.restTemplate.postForObject(domain, o, Equipos.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    Equipos update(@RequestBody Equipos o) {
        return create(o);
    }
}
