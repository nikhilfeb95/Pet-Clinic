package nikhil.springframework.petclinic.services.springdatajpa;

import nikhil.springframework.petclinic.model.Owner;
import nikhil.springframework.petclinic.repositories.OwnerRepository;
import nikhil.springframework.petclinic.repositories.PetRepository;
import nikhil.springframework.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner returnOwner;
    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName("Smith").build();
    }

    @Test
    void findbyLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

        Owner smith = ownerSDJpaService.findbyLastName("Smith");

        assertEquals(returnOwner.getId(),smith.getId());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
         owners.add(Owner.builder().id(1L).build());
            owners.add(Owner.builder().id(2L).build());


        when(ownerRepository.findAll()).thenReturn(owners);

        assertEquals(2, ownerSDJpaService.findAll().size());
    }

    @Test
    void findByID() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner smith = ownerSDJpaService.findByID(1L);

        assertEquals(returnOwner.getId(), smith.getId());
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(returnOwner);

        Owner smith = ownerSDJpaService.save(ownerToSave);

        assertNotNull(smith);
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);

        verify(ownerRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1L);

        verify(ownerRepository,times(1)).deleteById(anyLong());
    }
}