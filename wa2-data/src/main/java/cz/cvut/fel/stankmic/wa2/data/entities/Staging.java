package cz.cvut.fel.stankmic.wa2.data.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "staging")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Staging implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Play.class)
    @JoinColumn(name = "play_id")
    private Play play;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Theatre.class)
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Actor.class)
    private Set<Actor> actors;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, targetEntity = StagingRating.class, mappedBy = "staging")
    private Set<StagingRating> stagingRatings;

}
