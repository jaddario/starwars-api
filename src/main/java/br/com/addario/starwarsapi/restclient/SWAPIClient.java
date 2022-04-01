package br.com.addario.starwarsapi.restclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "${feign.name}", url = "${feign.url}")
public interface SWAPIClient {

    @RequestMapping(method = RequestMethod.GET, value = "/planets")
    List<PlanetResponseDTO> getPlanets();
}
