package br.com.addario.starwarsapi.service;

import br.com.addario.starwarsapi.model.PlanetDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlanetService {

    void insert(PlanetDTO planet);

    List<PlanetDTO> listPlanets();

    PlanetDTO findPlanetById(Long id);

    PlanetDTO findPlanetByName(String name);

    void updatePlanetName(String oldName, String newName);

    void deletePlanetById(Long id);

    void deletePlanetByName(String name);
}
