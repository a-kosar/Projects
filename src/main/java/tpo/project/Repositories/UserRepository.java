package tpo.project.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tpo.project.DTO.BreedDto;
import tpo.project.Models.DogBreed;
import tpo.project.Models.RegisteredUser;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<RegisteredUser, Long> {
    @Query("SELECT u FROM RegisteredUser u WHERE u.username = :username AND u.password = :password")
    Optional<RegisteredUser> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u.favourites FROM RegisteredUser u WHERE u.id = :userId")
    List<DogBreed> findFavouritesByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(*) > 0 FROM RegisteredUser u WHERE u.username = :username")
    boolean existsByUsername(String username);

    List<RegisteredUser> findAll();
}
