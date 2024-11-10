package tpo.project.Repositories;

import org.springframework.data.repository.CrudRepository;
import tpo.project.Models.AdoptionOption;

public interface AdoptionRepository extends CrudRepository<AdoptionOption, Long> {
}
