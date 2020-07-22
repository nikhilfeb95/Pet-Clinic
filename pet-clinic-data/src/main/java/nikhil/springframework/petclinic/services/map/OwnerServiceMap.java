package nikhil.springframework.petclinic.services.map;

import nikhil.springframework.petclinic.model.Owner;
import nikhil.springframework.petclinic.model.Pet;
import nikhil.springframework.petclinic.services.OwnerService;
import nikhil.springframework.petclinic.services.PetService;
import nikhil.springframework.petclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findByID(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object!=null){
            //owner has pets
            if(object.getPets() != null){
                object.getPets().forEach(pet ->{
                    if(pet.getPetType()!=null)
                    {
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                    }
                    else{
                        throw new RuntimeException("Pet type is required");
                    }
                    //ensuring the id is set (because of id auto-generation)
                    if(pet.getId()!= null){
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
        return super.save(object);
        }
        else{
            //cant save a null object
            return null;
        }
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findbyLastName(String lasName) {
        return null;
    }
}
