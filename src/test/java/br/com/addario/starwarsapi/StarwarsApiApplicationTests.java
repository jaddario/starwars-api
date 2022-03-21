package br.com.addario.starwarsapi;

import br.com.addario.starwarsapi.controller.PlanetController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StarwarsApiApplicationTests {

    @Autowired
    private PlanetController planetController;

    @Test
    void contextLoads() {
        assertThat(planetController).isNotNull();
    }

}
