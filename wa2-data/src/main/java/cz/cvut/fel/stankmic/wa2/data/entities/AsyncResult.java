package cz.cvut.fel.stankmic.wa2.data.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "async")
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AsyncResult implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Getter
    @Setter
    @Column(name = "email", nullable = true)
    private String result;

    public AsyncResult(String result) {
        this.result = result;
    }

}
