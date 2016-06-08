package cz.cvut.fel.stankmic.wa2.data.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "actorrating")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ActorRating implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "ratedBy", nullable = false)
    private User ratedBy;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Actor.class)
    @JoinColumn(name = "actor", nullable = false)
    private Actor actor;

    @Getter
    @Setter
    @Column(name = "comment")
    private String comment;

    public ActorRating(User ratedBy, Actor actor) {
        this.ratedBy = ratedBy;
        this.actor = actor;
    }

}
