package nikhil.springframework.petclinic.controllers;


import nikhil.springframework.petclinic.model.Owner;
import nikhil.springframework.petclinic.model.Pet;
import nikhil.springframework.petclinic.model.PetType;
import nikhil.springframework.petclinic.services.OwnerService;
import nikhil.springframework.petclinic.services.PetService;
import nikhil.springframework.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.util.StringUtils.hasLength;


@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {
    private static final String VIEWS_PETS_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerRepository, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerRepository;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    //get all pettypes when controller is called
    //Model attribute creates a model called "types" everytime the controller is called
    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    //populate the owner for the pet
    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findByID(ownerId);
    }

    //Init binder to prevent users from setting up the ID value, as it may mess up the DB
    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder webDataBinder)
    {
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String getCreationForm(Owner owner, Model model)
    {
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet",pet);
        return VIEWS_PETS_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult result, Model model)
    {
        //do not add if its a duplicate
        if(hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true)!=null){
            result.rejectValue("name", "duplicate", "value already exists");
        }
        owner.getPets().add(pet);
        if(result.hasErrors())
        {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_OR_UPDATE_FORM;
        }
        else{
            pet.setOwner(owner);//for some reason this is null and not passed on
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{petId}/edit")
    public String getUpdateForm(Owner owner, @PathVariable("petId") Long petId, Model model)
    {
        Pet pet = petService.findByID(petId);
        model.addAttribute("pet", pet);
        return VIEWS_PETS_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(@Validated Pet pet, BindingResult result, Owner owner, Model model)
    {
        if(result.hasErrors())
        {
            pet.setOwner(owner); //why? as this value already exists
            model.addAttribute("pet", pet);
            return VIEWS_PETS_OR_UPDATE_FORM;
        }
        else
        {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
