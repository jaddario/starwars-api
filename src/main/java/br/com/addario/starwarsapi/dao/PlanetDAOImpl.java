package br.com.addario.starwarsapi.dao;

import br.com.addario.starwarsapi.exceptions.PlanetNotFoundException;
import br.com.addario.starwarsapi.model.Planet;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
@Component
public class PlanetDAOImpl implements PlanetDAO {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public PlanetDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void insert(Planet planet) {
        entityManager.persist(planet);
    }

    @Override
    public List<Planet> listPlanets() {
        final var sql = """
                        SELECT *
                            FROM PLANET
                        """;

        return (List<Planet>) entityManager.createNativeQuery(sql, Planet.class).getResultList();
    }

    @SneakyThrows
    @Override
    public Optional<Planet> findPlanetById(Long id) {
        final var sql = """
                        SELECT *
                            FROM PLANET
                            WHERE ID = :id
                        """;

        return Optional.ofNullable((Planet) entityManager.createNativeQuery(sql, Planet.class)
                                                         .setParameter("id", id)
                                                         .getResultList()
                                                         .stream()
                                                         .findFirst()
                                                         .orElseThrow(() -> new PlanetNotFoundException(id)));
    }

    @SneakyThrows
    @Override
    public Optional<Planet> findPlanetByName(String name) {
        final var sql = """ 
                        SELECT *
                            FROM PLANET
                            WHERE NAME = :name
                        """;
        return Optional.ofNullable((Planet) entityManager.createNativeQuery(sql, Planet.class)
                                                         .setParameter("name", name)
                                                         .getResultList()
                                                         .stream()
                                                         .findFirst()
                                                         .orElseThrow(() -> new PlanetNotFoundException(name)));
    }

    @Override
    public Optional<Planet> updatePlanetName(String oldName, String newName) {
        throw new UnsupportedOperationException("This operation is not supported yet!");
    }

    @Override
    @Transactional
    public void deletePlanetById(Long id) {
        final var sql = """
                        DELETE FROM PLANET
                        WHERE ID = :id
                        """;
        final int deletedRows =
                entityManager.createNativeQuery(sql, Planet.class)
                             .setParameter("id", id)
                             .executeUpdate();

        if (deletedRows == 0)
            throw new PlanetNotFoundException(id);

    }

    @Override
    @Transactional
    public void deletePlanetByName(String name) {
        final var sql = """
                        DELETE FROM PLANET
                        WHERE NAME = :name
                        """;
        final int deletedRows =
                entityManager.createNativeQuery(sql, Planet.class)
                             .setParameter("name", name)
                             .executeUpdate();

        if (deletedRows == 0)
            throw new PlanetNotFoundException(name);
    }
}
