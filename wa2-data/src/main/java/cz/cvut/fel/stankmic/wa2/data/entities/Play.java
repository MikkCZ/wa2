package cz.cvut.fel.stankmic.wa2.data.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "play")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Play implements Serializable {

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
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, targetEntity = PlayRating.class, mappedBy = "play")
    private Set<PlayRating> playRatings;

}
