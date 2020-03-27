package petroleum.application.petroleum;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "distance")
public class Distance {

    @Id
    @SequenceGenerator(name="distance_id_seq", sequenceName="distance_id_seq", allocationSize=1)
    @GeneratedValue(strategy=SEQUENCE, generator="distance_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "distance", nullable = false)
    private Double distance;

    @Column(name = "driver", nullable = false)
    private String driver;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="tank_id", nullable=false)
    private Tank tank;
}



