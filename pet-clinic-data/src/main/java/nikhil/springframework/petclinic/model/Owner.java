package nikhil.springframework.petclinic.model;

import java.util.Set;

public class Owner extends Person {

    //Owner has pets
    private Set<Pet> pets;

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
