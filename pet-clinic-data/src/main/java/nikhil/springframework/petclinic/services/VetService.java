package nikhil.springframework.petclinic.services;

import nikhil.springframework.petclinic.model.Vet;

import java.util.Set;

public interface VetService{

    Vet findbyId(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
