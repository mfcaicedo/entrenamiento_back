package com.hexa.entrenamiento.negocio.hexa.controllers;
import com.hexa.entrenamiento.negocio.LigasEquipos;
import com.hexa.entrenamiento.negocio.hexa.Utilities.GeneralUtilitiesHexa;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.hexa.entrenamiento.utilities.GeneralUtilities.domainHexa;

@RestController
@RequestMapping("/api/ligas-equipos")
public class LigasEquiposController {

    private final String domain = domainHexa + "ligas-equipos/";
    @RequestMapping(method = RequestMethod.GET)
    List<LigasEquipos> findAll() {
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.getForObject(domain, LigasEquipos[].class)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    LigasEquipos findById(@PathVariable String id) {
        return GeneralUtilitiesHexa.restTemplate.getForObject(domain + id, LigasEquipos.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    LigasEquipos create(@RequestBody LigasEquipos o) {
        return GeneralUtilitiesHexa.restTemplate.postForObject(domain, o, LigasEquipos.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    LigasEquipos update(@RequestBody LigasEquipos o) {
        return create(o);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/all")
    List<LigasEquipos> createAll(@RequestBody List<LigasEquipos> listLigasEquipos){
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.postForObject(domain + "all",listLigasEquipos,LigasEquipos[].class)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/ligas/cantidad-equipos")
    List<HashMap<String, Long>> findAllByEquiposLigas() {
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.getForObject(domain + "ligas/cantidad-equipos", HashMap[].class)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/paises/cantidad-equipos")
    List<HashMap<String, Long>> findAllByEquiposPaises() {
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.getForObject(domain + "paises/cantidad-equipos", HashMap[].class)));
    }

}
