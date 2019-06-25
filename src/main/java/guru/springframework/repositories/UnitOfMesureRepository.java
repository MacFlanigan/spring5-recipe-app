package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMesure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitOfMesureRepository extends CrudRepository<UnitOfMesure, Long> {
    Optional<UnitOfMesure> findByDescription(String description);
}
