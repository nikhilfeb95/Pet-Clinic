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
@Table(name = "types")
public class PetType extends BaseEntity{
    @Column(name = "name ")
    private  String name;
}
