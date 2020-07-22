package nikhil.springframework.petclinic.controllers;

import nikhil.springframework.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/owners","/owners/index", "owners/index.html"})
    public String listOwner(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return  "owners/index";
    }

    @RequestMapping({"/owners/find", "/find"})
    public String findOwners(){
        return "notImplemented";
    }
}
