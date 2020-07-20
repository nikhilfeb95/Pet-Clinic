package nikhil.springframework.petclinic.bootstrap;

import nikhil.springframework.petclinic.model.Owner;
import nikhil.springframework.petclinic.model.Vet;
import nikhil.springframework.petclinic.services.OwnerService;
import nikhil.springframework.petclinic.services.VetService;
import nikhil.springframework.petclinic.services.map.OwnerServiceMap;
import nikhil.springframework.petclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;


    public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Nikhil");
        owner1.setLastName("Mollay");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Jotaro");
        owner2.setLastName("Kujo");

        ownerService.save(owner2);

        System.out.println("Loaded the owners");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Jonathan");
        vet1.setLastName("Joestar");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet1.setId(2L);
        vet1.setFirstName("Joseph");
        vet1.setLastName("Joestar");
        vetService.save(vet2);

        System.out.println("Loaded the vets");
    }
}
