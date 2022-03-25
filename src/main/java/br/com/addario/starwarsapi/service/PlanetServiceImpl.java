package br.com.addario.starwarsapi.service;

import br.com.addario.starwarsapi.dao.PlanetDAO;
import br.com.addario.starwarsapi.exceptions.PlanetNotFoundException;
import br.com.addario.starwarsapi.model.Planet;
import br.com.addario.starwarsapi.model.PlanetDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private final PlanetDAO planetDAO;

    @Override
    public void insert(PlanetDTO planetDTO) {
        var planet = Planet.from(planetDTO);
        planetDAO.insert(planet);
    }

    @Override
    public List<PlanetDTO> listPlanets() {
        List<Planet> planets = planetDAO.listPlanets();
        return planets.stream().map(PlanetDTO::from).toList();
    }

    @SneakyThrows
    @Override
    public PlanetDTO findPlanetById(Long id)  {
        return planetDAO.findPlanetById(id)
                        .map(PlanetDTO::from)
                        .orElseThrow(() -> new PlanetNotFoundException(id));
    }

    @Override
    public PlanetDTO findPlanetByName(String name) {
        var planetEntityOptional = planetDAO.findPlanetByName(name);
        return planetEntityOptional.map(PlanetDTO::from).orElseThrow(() -> new PlanetNotFoundException(name));
    }

    @Override
    public void updatePlanetName(String oldName, String newName) {
        planetDAO.updatePlanetName(oldName, newName);
    }

    @Override
    public void deletePlanetById(Long id) {
        planetDAO.deletePlanetById(id);
    }

    @Override
    public void deletePlanetByName(String name) {
        planetDAO.deletePlanetByName(name);
    }
}
