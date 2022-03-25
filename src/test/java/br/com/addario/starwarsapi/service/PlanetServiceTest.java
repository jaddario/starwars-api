package br.com.addario.starwarsapi.service;

import br.com.addario.starwarsapi.dao.PlanetDAO;
import br.com.addario.starwarsapi.exceptions.PlanetNotFoundException;
import br.com.addario.starwarsapi.model.Planet;
import br.com.addario.starwarsapi.model.PlanetDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlanetServiceTest {

    @Mock
    private PlanetDAO dao;

    @InjectMocks
    private PlanetServiceImpl service;

    private PlanetDTO planetOne;
    private PlanetDTO planetTwo;

    @BeforeEach
    void setUp() {
        planetOne = createPlanetDTO(1L);
        planetTwo = createPlanetDTO(2L);
    }

    @Test
    void shouldInsertAPlanet() {
        PlanetDTO planetDTO = createPlanetDTO(1L);
        service.insert(planetDTO);
        verify(dao, times(1)).insert(any());
    }

    @Test
    void shouldFindAllPlanets() {

        List<Planet> planets = createListOfPlanetsEntities(planetOne, planetTwo);
        when(dao.listPlanets()).thenReturn(planets);

        List<PlanetDTO> listPlanets = service.listPlanets();

        assertThat(listPlanets).hasSize(2).contains(planetOne, planetTwo);
    }

    @Test
    void shouldFindPlanetById() throws Exception {
        var expectedPlanet = createPlanetDTO(1L);

        when(dao.findPlanetById(1L)).thenReturn(Optional.ofNullable(Planet.from(expectedPlanet)));

        PlanetDTO actualPlanet = service.findPlanetById(1L);

        assertThat(actualPlanet).isEqualTo(expectedPlanet);
    }

    @Test
    void shouldFindPlanetByName() {
        var expectedPlanet = createPlanetDTO(1L);

        when(dao.findPlanetByName(expectedPlanet.getName()))
                .thenReturn(Optional.ofNullable(Planet.from(expectedPlanet)));

        PlanetDTO actualPlanet = service.findPlanetByName("planet1");

        assertThat(actualPlanet).isEqualTo(expectedPlanet);
    }

    @Test
    void shouldThrowsExceptionWhenTryUpdate() {
        var expectedPlanet = createPlanetDTO(1L);

        when(dao.updatePlanetName(any(), any()))
                .thenThrow(UnsupportedOperationException.class);

        assertThrows(UnsupportedOperationException.class, () -> service.updatePlanetName(expectedPlanet.getName(),
                "teste"), "This operation is not supported yet!");
    }

    @Test
    void shouldDeletePlanetById() {
        service.deletePlanetById(1L);
        verify(dao, times(1)).deletePlanetById(any());
    }

    @Test
    void shouldDeletePlanetByName() {
        service.deletePlanetByName(anyString());
        verify(dao, times(1)).deletePlanetByName(anyString());
    }

    private List<Planet> createListOfPlanetsEntities(PlanetDTO planetOne, PlanetDTO planetTwo) {
        return Stream.of(planetOne, planetTwo).map(Planet::from).toList();
    }


    private PlanetDTO createPlanetDTO(Long id) {
        return PlanetDTO.builder()
                        .id(id)
                        .name(String.format("planet%d", id))
                        .terrain("terrain")
                        .weather("weather")
                        .movieAppearances(1)
                        .build();
    }

}