package com.jd.petclinic.services.map;

import com.jd.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    private Long ownerId = 1L;
    private String ownerName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(ownerName).build());
    }

    @Test
    void findAll() {
        Set<Owner> owners = ownerMapService.findAll();
        assertEquals(owners.size(), 1);
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(ownerId);
        assertEquals(owner.getId(), ownerId);
    }

    @Test
    void saveExistingId() {
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        ownerMapService.save(owner2);

        assertEquals(owner2.getId(), id);
    }

    @Test
    void saveNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }
    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));

        assertEquals(ownerMapService.findAll().size(), 0);

    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(ownerMapService.findAll().size(), 0);

    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(ownerName);
        assertNotNull(smith);
        assertEquals(smith.getId(), ownerId);
    }

    @Test
    void findByLastNameNotFound() {
        Owner foo = ownerMapService.findByLastName("Foo");
        assertNull(foo);

    }
}