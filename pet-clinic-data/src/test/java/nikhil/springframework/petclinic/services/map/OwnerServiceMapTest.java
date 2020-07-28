package nikhil.springframework.petclinic.services.map;

import nikhil.springframework.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
    final Long ownerId = 1L;
    final String lastName = "Kujo";
    @BeforeEach
    public void setUp()
    {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());

        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerServiceMap.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findByID() {
        Owner owner = ownerServiceMap.findByID(ownerId);

        assertEquals(ownerId, owner.getId());
    }

    @Test
    void save() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();

        Owner savedOwner = ownerServiceMap.save(owner2);

        assertEquals(id, savedOwner.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findByID(ownerId));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findbyLastName() {
        Owner kujo = ownerServiceMap.findbyLastName(lastName);
        assertNotNull(kujo);
        assertEquals(ownerId,kujo.getId());
    }

    void findByLastNameNotFound(String lastName){
        Owner kujo = ownerServiceMap.findbyLastName("foo");
        assertNull(kujo);
    }
}