package br.com.addario.starwarsapi.dao;

import br.com.addario.starwarsapi.exceptions.PlanetNotFoundException;
import br.com.addario.starwarsapi.model.Planet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlanetDAOTest {

    @Autowired
    private PlanetDAO planetDAO;

    @Test
    void shouldInsertAPlanet() {

        final var tatooine = Planet
                .builder()
                .name("Tatooine")
                .weather("arid")
                .terrain("desert")
                .movieAppearances(5)
                .build();

        planetDAO.insert(tatooine);
        assertThat(planetDAO.listPlanets()).hasSize(2).contains(tatooine);
    }

    @Test
    @Sql(scripts = "classpath:/db/insert_planets.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldListAllPlanets() {

        var tatooine = Planet.builder()
                             .id(1L)
                             .name("tatooine")
                             .weather("arid")
                             .terrain("desert")
                             .movieAppearances(6)
                             .build();

        var naboo = Planet.builder()
                          .id(2L)
                          .name("naboo")
                          .weather("umid")
                          .terrain("forest")
                          .movieAppearances(3)
                          .build();

        final List<Planet> expectedPlanets = List.of(tatooine, naboo);
        final List<Planet> foundPlanets = planetDAO.listPlanets();
        assertThat(foundPlanets)
                .hasSize(2)
                .containsAll(expectedPlanets);
    }

    @Test
    @Sql(scripts = "classpath:/db/insert_planets.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldFindTatooineById() {
        var tatooine = Planet.builder()
                             .id(1L)
                             .name("tatooine")
                             .weather("arid")
                             .terrain("desert")
                             .movieAppearances(6)
                             .build();

        final Planet foundPlanet = planetDAO.findPlanetById(1L).get();
        assertThat(foundPlanet).isEqualTo(tatooine);
    }

    @Test
    @Sql(scripts = "classpath:/db/insert_planets.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldFindNabooByName() {

        var naboo = Planet.builder()
                          .id(2L)
                          .name("naboo")
                          .weather("umid")
                          .terrain("forest")
                          .movieAppearances(3)
                          .build();

        final Planet foundPlanet = planetDAO.findPlanetByName("naboo").get();
        assertThat(foundPlanet).isEqualTo(naboo);
    }

    @Test
    @Sql(scripts = "classpath:/db/insert_planets.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldDeletePlanetById() {
        var tatooine = Planet.builder()
                             .id(1L)
                             .name("tatooine")
                             .weather("arid")
                             .terrain("desert")
                             .movieAppearances(6)
                             .build();

        planetDAO.deletePlanetById(1L);
        final List<Planet> foundPlanets = planetDAO.listPlanets();
        assertThat(foundPlanets).hasSize(1).doesNotContain(tatooine);
    }

    @Test
    @Sql(scripts = "classpath:/db/insert_planets.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldDeletePlanetByName() {
        var naboo = Planet.builder()
                          .id(2L)
                          .name("naboo")
                          .weather("umid")
                          .terrain("forest")
                          .movieAppearances(3)
                          .build();
        planetDAO.deletePlanetByName("naboo");
        final List<Planet> foundPlanets = planetDAO.listPlanets();
        assertThat(foundPlanets).hasSize(1).doesNotContain(naboo);
    }

    @Test
    @Sql(scripts = "classpath:/db/insert_planets.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void shouldThrowExceptionTryingDeletingPlanetThatDoesNotExist() {
        final PlanetNotFoundException exception = assertThrows(PlanetNotFoundException.class,
                () -> planetDAO.deletePlanetByName("coruscant"));
        final List<Planet> foundPlanets = planetDAO.listPlanets();
        assertThat(foundPlanets).hasSize(2);
        assertThat(exception.getMessage()).isEqualTo("There's no Planet with name coruscant");
    }
}