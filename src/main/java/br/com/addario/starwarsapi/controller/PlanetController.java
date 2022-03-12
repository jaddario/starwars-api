package br.com.addario.starwarsapi.controller;

import br.com.addario.starwarsapi.dao.PlanetDAO;
import br.com.addario.starwarsapi.model.Planet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController(value = "/api/v1/starwars")
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetDAO planetDAO;

    @PostMapping("/create/planets")
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet planet) {
        planetDAO.insert(planet);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(planet.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
