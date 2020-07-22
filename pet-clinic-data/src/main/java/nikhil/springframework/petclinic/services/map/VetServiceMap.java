package nikhil.springframework.petclinic.services.map;

import nikhil.springframework.petclinic.model.Specialty;
import nikhil.springframework.petclinic.model.Vet;
import nikhil.springframework.petclinic.services.SpecialtyService;
import nikhil.springframework.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private  final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findByID(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        if(object!=null)
        {
            if(object.getSpecialities().size() > 0){
                object.getSpecialities().forEach(specialty -> {
                    if(specialty.getId() == null){
                        //saving those specialties whose id aren't stored
                        Specialty savedSpecialty = specialtyService.save(specialty);
                        //Defensive coding to prevent concurrrency issues
                        specialty.setId(savedSpecialty.getId());
                    }
                });
            }
        }
        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
