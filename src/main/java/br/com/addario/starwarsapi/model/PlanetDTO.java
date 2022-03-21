package br.com.addario.starwarsapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanetDTO {
    private Long id;
    private String name;
    private String weather;
    private String terrain;
    private int movieAppearances;

    public static PlanetDTO from(Planet entity) {
        return PlanetDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .weather(entity.getWeather())
                .terrain(entity.getTerrain())
                .movieAppearances(entity.getMovieAppearances())
                .build();
    }
}
