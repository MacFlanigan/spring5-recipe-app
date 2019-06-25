package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMesure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMesureRepositoryIT {

    @Autowired
    UnitOfMesureRepository unitOfMesureRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMesure> uomOptional = unitOfMesureRepository.findByDescription("Teaspoon");
        uomOptional.ifPresent(unitOfMesure -> assertEquals("Teaspoon", unitOfMesure.getDescription()));
    }

    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMesure> uomOptional = unitOfMesureRepository.findByDescription("Cup");
        uomOptional.ifPresent(unitOfMesure -> assertEquals("Cup", unitOfMesure.getDescription()));

    }
}