package com.hexa.entrenamiento.negocio.hexa.controllers;

import com.hexa.entrenamiento.negocio.Ligas;
import com.hexa.entrenamiento.negocio.hexa.Utilities.GeneralUtilitiesHexa;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.hexa.entrenamiento.utilities.GeneralUtilities.domainHexa;

@RestController
@RequestMapping("/api/ligas")
public class LigasController {
    private final String domain = domainHexa + "ligas/";
    @RequestMapping(method = RequestMethod.GET)
    List<Ligas> findAll() {
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.getForObject(domain, Ligas[].class)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    Ligas findById(@PathVariable String id) {
        return GeneralUtilitiesHexa.restTemplate.getForObject(domain + id, Ligas.class);
    }

    @RequestMapping(method = RequestMethod.POST)
    Ligas create(@RequestBody Ligas o) {
        return GeneralUtilitiesHexa.restTemplate.postForObject(domain, o, Ligas.class);
    }

    @RequestMapping(method = RequestMethod.PUT)
    Ligas update(@RequestBody Ligas o) {
        return create(o);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/all")
    List<Ligas> createAll(@RequestBody List<Ligas> listLigas){
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.postForObject(domain + "all",listLigas,Ligas[].class)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/paisById/{idPais}")
    List<Ligas> findByIdPais(@PathVariable Integer idPais) {
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.getForObject(domain + "paisById/" + idPais, Ligas[].class)));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/pais/{nombrePais}")
    List<Ligas> findByPais(@PathVariable String nombrePais) {
        return Arrays.asList(Objects.requireNonNull(GeneralUtilitiesHexa.restTemplate.getForObject(domain + "pais/" + nombrePais, Ligas[].class)));
    }
}
