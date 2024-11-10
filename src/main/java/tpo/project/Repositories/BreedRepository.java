package tpo.project.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tpo.project.Models.DogBreed;

import java.util.List;

public interface BreedRepository extends CrudRepository<DogBreed, Long> {
    @Query("SELECT b FROM DogBreed b ORDER BY b.name")
    List<DogBreed> findAllEntitiesSortedByName();

    @Query("SELECT b FROM DogBreed b ORDER BY b.size")
    List<DogBreed> findAllEntitiesSortedBySize();

    @Query("SELECT b FROM DogBreed b ORDER BY b.lifespan")
    List<DogBreed> findAllEntitiesSortedByLifespan();

    @Query("SELECT b FROM DogBreed b WHERE lower(b.name) LIKE lower(concat(:pattern, '%'))")
    List<DogBreed> findByNameStartingWith(@Param("pattern") String pattern);

    List<DogBreed> findAll();
}
