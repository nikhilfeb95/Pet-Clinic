package nikhil.springframework.petclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "specialty")
public class Specialty extends BaseEntity {

    @Column(name = "description")
    private String description;
}
