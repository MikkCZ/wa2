package cz.cvut.fel.stankmic.wa2.data.entities;

import com.sun.istack.internal.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "users")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @NotNull
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Getter
    @Setter
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Setter
    @NotNull
    @Column(name = "enabled", nullable = false, columnDefinition = "boolean default true")
    private boolean enabled = true;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ActorRating.class, mappedBy = "ratedBy")
    private Set<ActorRating> actorRatings;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = PlayRating.class, mappedBy = "ratedBy")
    private Set<PlayRating> playRatings;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = StagingRating.class, mappedBy = "ratedBy")
    private Set<StagingRating> stagingRatings;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = TheatreRating.class, mappedBy = "ratedBy")
    private Set<TheatreRating> theatreRatings;

    public User(String email) {
        this.email = email;
    }

}
