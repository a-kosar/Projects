package tpo.project.Repositories;

import org.springframework.data.repository.CrudRepository;
import tpo.project.Models.Colour;

public interface ColourRepository extends CrudRepository<Colour, Long> {
    Colour findByName(String name);
}
