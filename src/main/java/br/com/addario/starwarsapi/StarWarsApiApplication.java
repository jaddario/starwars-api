package br.com.addario.starwarsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@EnableFeignClients
public class StarWarsApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarWarsApiApplication.class, args);
    }

}
