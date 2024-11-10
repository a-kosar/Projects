package tpo.project.Repositories;

import org.springframework.data.repository.CrudRepository;
import tpo.project.Models.DogType;

public interface TypeRepository extends CrudRepository<DogType, Long> {
    DogType findByPurpose(String purpose);
}
