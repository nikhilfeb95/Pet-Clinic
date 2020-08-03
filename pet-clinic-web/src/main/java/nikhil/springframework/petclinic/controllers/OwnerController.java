package nikhil.springframework.petclinic.controllers;

import nikhil.springframework.petclinic.model.Owner;
import nikhil.springframework.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /*@RequestMapping({"","/","/index", "/index.html"})
    public String listOwner(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return  "owners/index";
    } */

    @RequestMapping({"/find"})
    public String findOwners(Model model){
        Owner owner = Owner.builder().build();
        model.addAttribute("owner" , owner);
        return "owners/findOwners";
    }

    @GetMapping()
    public String processFindForm(Owner owner, BindingResult result, Model model){

        if(owner.getLastName() == null)
            owner.setLastName("");

        //adding the wildcard --> underneath hibernate makes sql calls using the wildcard
        List<Owner> results = ownerService.findByLastNameLike("%" + owner.getLastName() + "%");

        //if no owner found then redirect back to the search page
        if(results.isEmpty())
        {
            result.rejectValue("lastName", "NotFound", "not found");
            return "owners/findOwners";
        }
        //if only one found then just display the owners detail page
        else if(results.size() == 1){
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        //else show the list of all owners
        else{
            model.addAttribute("selections", results);
            return "owners/ownerList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId)
    {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findByID(ownerId));
        return mav;
    }

    //calls the form to add a new owner
    @GetMapping("/new")
    public String getCreationForm(Model model)
    {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwner";
    }

    //Binding result is used to attach errors or exceptions with the view
    @PostMapping("/new")
    public String processCreationForm(@Validated Owner owner, BindingResult result)
    {
        if(result.hasErrors()) {
            return "owners/createOrUpdateOwner";
        }
        else{
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String getUpdateForm(@PathVariable("ownerId") Long ownerId, Model model)
    {
        Owner owner = ownerService.findByID(ownerId);
        model.addAttribute("owner",owner);
        return "owners/createOrUpdateOwner";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(@Validated Owner owner, BindingResult result, @PathVariable("ownerId") Long ownerId)
    {
        if(result.hasErrors()){
            return "owners/createOrUpdateOwner";
        }
        else
        {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }
}
