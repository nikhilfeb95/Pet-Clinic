package nikhil.springframework.petclinic.services;

import nikhil.springframework.petclinic.model.Owner;

import java.util.List;


public interface OwnerService extends CrudService<Owner,Long>{
    Owner findbyLastName(String lasName);

    List<Owner> findByLastNameLike(String lastName);
}
