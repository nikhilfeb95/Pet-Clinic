package nikhil.springframework.petclinic.model;

import java.util.Set;

public class Vet extends Person {

    public Set<Specialty> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(Set<Specialty> specialities) {
        this.specialities = specialities;
    }

    private Set<Specialty> specialities;
}
