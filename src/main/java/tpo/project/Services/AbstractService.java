package tpo.project.Services;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public abstract class AbstractService<T, ID> {

    protected CrudRepository<T, ID> repository;

    public AbstractService(CrudRepository<T, ID> repository) {
        this.repository = repository;
    }

    public boolean updateEntity(T entity, ID id) {
        Optional<T> entityOptional = repository.findById(id);
        if (entityOptional.isPresent()) {
            repository.save(entity);
            return true;
        }
        return false;
    }

    public boolean deleteEntity(ID id) {
        Optional<T> entityOptional = repository.findById(id);
        if (entityOptional.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
