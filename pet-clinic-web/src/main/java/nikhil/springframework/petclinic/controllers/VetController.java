package nikhil.springframework.petclinic.controllers;

import nikhil.springframework.petclinic.model.Vet;
import nikhil.springframework.petclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {
    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model){
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    //an api that returns Vets
    @RequestMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson()
    {
        return vetService.findAll();
    }
}
