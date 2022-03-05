package br.com.addario.starwarsapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PLANET")
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "WEATHER")
    private String weather;
    @Column(name = "TERRAIN")
    private String terrain;
    @Column(name = "MOVIE_APPEARANCES")
    private int movieAppearances;

}
