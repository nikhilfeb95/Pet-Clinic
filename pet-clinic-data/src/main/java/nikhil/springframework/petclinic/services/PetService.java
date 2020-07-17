package nikhil.springframework.petclinic.services;

import nikhil.springframework.petclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findbyId(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
