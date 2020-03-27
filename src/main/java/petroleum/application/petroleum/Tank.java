package petroleum.application.petroleum;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "tank")
public class Tank {

    @Id
    @SequenceGenerator(name="tank_id_seq", sequenceName="tank_id_seq", allocationSize=1)
    @GeneratedValue(strategy=SEQUENCE, generator="tank_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "closing_date", nullable = true)
    private LocalDateTime closingDate;

    @Column(name = "volume", nullable = false)
    private Double volume;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    @Column(name = "payor", nullable = false)
    private String payor;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "tank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Distance> distances;

    @Column(name = "amount_due")
    private Double amountDue;

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void deactivateTank(){
        setActive(false);
    }

}
