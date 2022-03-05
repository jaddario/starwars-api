package br.com.addario.starwarsapi.exceptions;

public class DeletedPlanetException extends RuntimeException {
    public DeletedPlanetException(Long id) {
        super(String.format("There's no Planet with id %d", id));
    }

    public DeletedPlanetException(String name) {
        super(String.format("There's no Planet with name %s", name));
    }
}
