package nikhil.springframework.petclinic.formatters;

import nikhil.springframework.petclinic.model.PetType;
import nikhil.springframework.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
//Helps in dealing with formatting issues in thymeleaf
public class PetTypeFormatter implements Formatter<PetType> {

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    private final PetTypeService petTypeService;

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> petTypes = petTypeService.findAll();

        for (PetType petType : petTypes)
        {
            if(petType.getName().equals(text)) {
                return petType;
            }
        }
        throw new ParseException("type not found" + text, 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
