package cz.cvut.fel.stankmic.wa2.data.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "actor")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Actor implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Column(name = "firstname")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "lastname")
    private String lastName;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Staging.class)
    private Set<Staging> stagings;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = Theatre.class)
    private Set<Theatre> theatres;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, targetEntity = ActorRating.class, mappedBy = "actor")
    private Set<ActorRating> actorRatings;

    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
