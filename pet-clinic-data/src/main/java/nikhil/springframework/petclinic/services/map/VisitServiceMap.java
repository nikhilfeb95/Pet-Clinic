package nikhil.springframework.petclinic.services.map;

import nikhil.springframework.petclinic.model.Visit;
import nikhil.springframework.petclinic.services.PetService;
import nikhil.springframework.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {

    private final PetService petService;

    public VisitServiceMap(PetService petService) {
        this.petService = petService;
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        if(object.getPet() == null || object.getPet().getOwner() == null
        || object.getPet().getId() == null || object.getPet().getOwner().getId() == null){
            throw  new RuntimeException("Either owners or pets aren't persisted before hand");
        }
        return super.save(object);
    }

    @Override
    public Visit findByID(Long aLong) {
        return super.findById(aLong);
    }
}
