package nikhil.springframework.petclinic.bootstrap;

import nikhil.springframework.petclinic.model.*;
import nikhil.springframework.petclinic.services.OwnerService;
import nikhil.springframework.petclinic.services.PetTypeService;
import nikhil.springframework.petclinic.services.SpecialtyService;
import nikhil.springframework.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private  final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0)
        {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        //We save it for persistence --> easy link with hibernate to push data into
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Owner owner1 = new Owner();
        owner1.setFirstName("Nikhil");
        owner1.setLastName("Mollay");
        owner1.setAddress("Hooker Road");
        owner1.setCity("Darjeeling");
        owner1.setTelephone("1234");

        Pet nikhilsPet = new Pet();
        nikhilsPet.setPetType(savedDogPetType);
        nikhilsPet.setName("JOJO");
        nikhilsPet.setOwner(owner1);
        nikhilsPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(nikhilsPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jotaro");
        owner2.setLastName("Kujo");
        owner2.setAddress("Morioh");
        owner2.setCity("Tokyo");
        owner2.setTelephone("12346");

        Pet jotarosPet = new Pet();
        jotarosPet.setPetType(savedCatPetType);
        jotarosPet.setName("Iggy");
        jotarosPet.setOwner(owner2);
        jotarosPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(jotarosPet);
        ownerService.save(owner2);

        System.out.println("Loaded the owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Jonathan");
        vet1.setLastName("Joestar");
        //we're pushing the saved data only to be on the safe side --> no concurrency issues
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Joseph");
        vet2.setLastName("Joestar");
        vet2.getSpecialities().add(savedDentistry);
        vetService.save(vet2);

        System.out.println("Loaded the vets");
    }
}
