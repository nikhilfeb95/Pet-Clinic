package nikhil.springframework.petclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "visits")
public class Visit extends BaseEntity {

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    //A pet can have many visits to the vet
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

}
