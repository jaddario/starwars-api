package br.com.addario.starwarsapi.controller;

import br.com.addario.starwarsapi.dao.PlanetDAO;
import br.com.addario.starwarsapi.model.Planet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

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

    @GetMapping("/planets")
    public List<Planet> listPlanets() {
        return planetDAO.listPlanets();
    }

    @GetMapping("/planets/{id}")
    public ResponseEntity<Planet> getPlanetById(@PathVariable(value = "id") long id) {
        var planet = planetDAO.findPlanetById(id);
        return planet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
