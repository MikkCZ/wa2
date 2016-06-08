package cz.cvut.fel.stankmic.wa2.data.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "theatre")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Theatre implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, targetEntity = Staging.class)
    private Set<Staging> stagings;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Actor.class)
    private Set<Actor> actors;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, targetEntity = TheatreRating.class, mappedBy = "theatre")
    private Set<TheatreRating> theatreRatings;

    public Theatre(String name) {
        this.name = name;
    }

}
