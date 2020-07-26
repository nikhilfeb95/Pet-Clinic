package nikhil.springframework.petclinic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "owners")
public class Owner extends Person {
    //As owner inherits from
    //Owner has pets --> initializing to prevent null exception
    //Owner has many pets --> pet has owner(mapped by Owner owner in Pet class)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String  telephone;

}
