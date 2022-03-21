package br.com.addario.starwarsapi.service;

import br.com.addario.starwarsapi.dao.PlanetDAO;
import br.com.addario.starwarsapi.model.Planet;
import br.com.addario.starwarsapi.model.PlanetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private final PlanetDAO planetDAO;

    @Override
    public void insert(PlanetDTO planetDTO) {
        var planet = Planet.from(planetDTO);
        planetDAO.insert(planet);
    }

    @Override
    public List<Planet> listPlanets() {
        return planetDAO.listPlanets();
    }

    @Override
    public Optional<PlanetDTO> findPlanetById(Long id) {
        var entity = planetDAO.findPlanetById(id);
        return Optional.of(PlanetDTO.from(entity.get()));
    }

    @Override
    public Optional<PlanetDTO> findPlanetByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<PlanetDTO> updatePlanetName(String oldName, String newName) {
        return Optional.empty();
    }

    @Override
    public void deletePlanetById(Long id) {

    }

    @Override
    public void deletePlanetByName(String name) {

    }
}
