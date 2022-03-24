package br.com.addario.starwarsapi.controller;

import br.com.addario.starwarsapi.model.PlanetDTO;
import br.com.addario.starwarsapi.service.PlanetService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PlanetController.class)
class PlanetControllerTest {

    @MockBean
    private PlanetService service;

    @InjectMocks
    private PlanetController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateValidPlanetAndReturn201Code() {
        var request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var testPlanet = createPlanet(1L, "testPlanet");
        var responseEntity = controller.createPlanet(testPlanet);

        verify(service, times(1)).insert(any());
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);
    }

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
               .andExpect(jsonPath("$", Matchers.hasSize(1)))
               .andExpect(jsonPath("$[0].name", Matchers.is("planet1")))
               .andExpect(jsonPath("$[0].terrain", Matchers.is("terrain")))
               .andExpect(jsonPath("$[0].weather", Matchers.is("weather")))
               .andExpect(jsonPath("$[0].movieAppearances", Matchers.is(1)));
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