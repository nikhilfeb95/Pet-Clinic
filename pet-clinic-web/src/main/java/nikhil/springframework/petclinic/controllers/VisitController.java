package nikhil.springframework.petclinic.controllers;

import nikhil.springframework.petclinic.model.Pet;
import nikhil.springframework.petclinic.model.Visit;
import nikhil.springframework.petclinic.services.PetService;
import nikhil.springframework.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {
    private final VisitService visitService;
    private final PetService petService;

    private static final String CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setDisallowedFields(WebDataBinder webDataBinder)
    {
        webDataBinder.setDisallowedFields("id");
    }

    //load all the visits of a pet
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Model model)
    {
        Pet pet = petService.findByID(petId);
        model.addAttribute("pet", pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/new")
    public String getNewVisitForm(@PathVariable("petId") Long petId, Model model)
    {
        return CREATE_OR_UPDATE_VISIT_FORM;
    }

    @PostMapping("/new")
    public String processNewVisitForm(@Validated Visit visit, BindingResult result,
                                      @PathVariable("ownerId") Long ownerId)
    {
        if(result.hasErrors()){
            return CREATE_OR_UPDATE_VISIT_FORM;
        }
        else{
            visitService.save(visit);
            return "redirect:/owners/" + ownerId;
        }
    }
}
