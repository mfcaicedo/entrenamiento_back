package com.hexa.entrenamiento.negocio.hexa.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeneralUtilitiesHexa {


    /**
     * Restemplate inyectado para la utilización en ls modelos de negocio
     * en los que no es posible realizar inyección
     */
    public static RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        /*restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build()));*/
        GeneralUtilitiesHexa.restTemplate = restTemplate;
    }

}
