package cz.cvut.fel.stankmic.wa2.data.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "theatrerating")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TheatreRating {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name="ratedBy", nullable = false)
    private User ratedBy;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Theatre.class)
    @JoinColumn(name="theatre", nullable = false)
    private Theatre theatre;

    @Getter
    @Setter
    @Column(name = "comment")
    private String comment;

}
