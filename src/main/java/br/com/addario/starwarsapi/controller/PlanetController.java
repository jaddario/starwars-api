package br.com.addario.starwarsapi.controller;

import br.com.addario.starwarsapi.model.PlanetDTO;
import br.com.addario.starwarsapi.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController(value = "/api/v1/starwars")
public class PlanetController {

    private final PlanetService service;

    @Autowired
    public PlanetController(PlanetService service) {
        this.service = service;
    }

    @PostMapping("/create/planets")
    public ResponseEntity<PlanetDTO> createPlanet(@RequestBody PlanetDTO planet) {

        service.insert(planet);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(planet.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/planets")
    public List<PlanetDTO> listPlanets() {
        return service.listPlanets();
    }

    @GetMapping("/planets/{id}")
    public ResponseEntity<PlanetDTO> getPlanetById(@PathVariable(value = "id") long id) {
        var planet = service.findPlanetById(id);
        return ResponseEntity.ok(planet);
    }
}
