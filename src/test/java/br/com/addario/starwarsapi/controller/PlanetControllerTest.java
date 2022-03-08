package br.com.addario.starwarsapi.controller;

import br.com.addario.starwarsapi.dao.PlanetDAO;
import br.com.addario.starwarsapi.model.Planet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlanetControllerTest {

    @InjectMocks
    private PlanetController controller;

    @Mock
    private PlanetDAO planetDAO;

    @Test
    void shouldCreateValidPlanetAndReturn201Code() {
        var request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        var testPlanet = Planet.builder()
                .id(1L)
                .name("TestPlanet")
                .terrain("terrain")
                .weather("weather")
                .movieAppearances(1)
                .build();

        var responseEntity = controller.createPlanet(testPlanet);

        verify(planetDAO, times(1)).insert(any());
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);
    }
}