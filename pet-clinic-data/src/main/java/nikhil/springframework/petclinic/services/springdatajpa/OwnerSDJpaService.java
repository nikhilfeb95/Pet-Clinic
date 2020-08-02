package nikhil.springframework.petclinic.services.springdatajpa;

import nikhil.springframework.petclinic.model.Owner;
import nikhil.springframework.petclinic.repositories.OwnerRepository;
import nikhil.springframework.petclinic.repositories.PetRepository;
import nikhil.springframework.petclinic.repositories.PetTypeRepository;
import nikhil.springframework.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, PetRepository petRepository,
                             PetTypeRepository petTypeRepository)
    {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Owner findbyLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findByLastNameLike(String lastName) {
        return ownerRepository.findByLastNameLike(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners :: add);
        return owners;
    }

    @Override
    public Owner findByID(Long id) {
        Optional<Owner> optionalOwner =  ownerRepository.findById(id);
        if(optionalOwner.isPresent())
            return optionalOwner.get();
        else
            return null;
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
