package br.com.addario.starwarsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class StarWarsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWarsApiApplication.class, args);
    }

}
