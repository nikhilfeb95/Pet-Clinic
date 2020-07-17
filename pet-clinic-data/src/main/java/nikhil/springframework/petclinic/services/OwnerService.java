package nikhil.springframework.petclinic.services;

import nikhil.springframework.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService {
    Owner findbyLastName(String lasName);

    Owner findbyId(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
