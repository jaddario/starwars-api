package br.com.addario.starwarsapi.service;

import br.com.addario.starwarsapi.model.Planet;
import br.com.addario.starwarsapi.model.PlanetDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PlanetService {

    void insert(PlanetDTO planet);

    List<Planet> listPlanets();

    Optional<PlanetDTO> findPlanetById(Long id);

    Optional<PlanetDTO> findPlanetByName(String name);

    Optional<PlanetDTO> updatePlanetName(String oldName, String newName);

    void deletePlanetById(Long id);

    void deletePlanetByName(String name);
}
