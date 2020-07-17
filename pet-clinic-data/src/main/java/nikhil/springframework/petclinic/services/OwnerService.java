package nikhil.springframework.petclinic.services;

import nikhil.springframework.petclinic.model.Owner;


public interface OwnerService extends CrudService<Owner,Long>{
    Owner findbyLastName(String lasName);
}
