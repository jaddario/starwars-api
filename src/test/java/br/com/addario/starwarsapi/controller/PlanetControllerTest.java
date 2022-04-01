package br.com.addario.starwarsapi.controller;

import br.com.addario.starwarsapi.model.PlanetDTO;
import br.com.addario.starwarsapi.service.PlanetServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PlanetController.class)
class PlanetControllerTest {

    @MockBean
    private PlanetServiceImpl service;

    @InjectMocks
    private PlanetController controller;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void shouldFindAllAndReturnAListOfPlanets() throws Exception {
        var firstPlanet = createPlanet(1L, "planet1");
        var secondPlanet = createPlanet(2L, "planet2");

        List<PlanetDTO> planets = List.of(firstPlanet, secondPlanet);
        when(service.listPlanets()).thenReturn(planets);

        mockMvc.perform(get("/planets"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", Matchers.hasSize(2)))
               .andExpect(jsonPath("$[0].name", Matchers.is("planet1")))
               .andExpect(jsonPath("$[1].name", Matchers.is("planet2")));
    }

    @Test
    void shouldFindByIdAndReturnAPlanet() throws Exception {
        var firstPlanet = createPlanet(1L, "planet1");

        when(service.findPlanetById(1L)).thenReturn(firstPlanet);

        mockMvc.perform(get("/planets/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", Matchers.aMapWithSize(5)))
               .andExpect(jsonPath("$.name", Matchers.is("planet1")))
               .andExpect(jsonPath("$.terrain", Matchers.is("terrain")))
               .andExpect(jsonPath("$.weather", Matchers.is("weather")))
               .andExpect(jsonPath("$.movieAppearances", Matchers.is(1)));
    }

    private PlanetDTO createPlanet(Long id, String planetName) {
        return PlanetDTO.builder()
                        .id(id)
                        .name(planetName)
                        .terrain("terrain")
                        .weather("weather")
                        .movieAppearances(1)
                        .build();
    }
}