package br.com.addario.starwarsapi.exceptions;

public class PlanetNotFoundException extends RuntimeException {
    public PlanetNotFoundException(Long id) {
        super(String.format("There's no Planet with id %d", id));
    }

    public PlanetNotFoundException(String name) {
        super(String.format("There's no Planet with name %s", name));
    }
}
