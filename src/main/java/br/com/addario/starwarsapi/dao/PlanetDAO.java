package br.com.addario.starwarsapi.dao;

import br.com.addario.starwarsapi.model.Planet;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetDAO {
    void insert(Planet planet);

    List<Planet> listPlanets();

    Planet findPlanetById(Long id);

    Planet findPlanetByName(String name);

    Planet updatePlanetName(String oldName, String newName);

    void deletePlanetById(Long id);

    void deletePlanetByName(String name);
}
