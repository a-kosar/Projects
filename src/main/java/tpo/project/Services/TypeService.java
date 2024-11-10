package tpo.project.Services;

import org.springframework.stereotype.Service;
import tpo.project.DTO.TypeDto;
import tpo.project.Models.DogType;
import tpo.project.Repositories.TypeRepository;

import java.util.*;

@Service
public class TypeService extends AbstractService<DogType, Long> {
    private final MyMapper mapper;

    public TypeService(TypeRepository repository, MyMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    public TypeDto getEntity(Long id) {
        Optional<DogType> optional = repository.findById(id);
        return optional.map(mapper::typeToDto).orElse(null);
    }

    public boolean updateEntity(TypeDto entity, Long id) {
        Optional<DogType> entityOptional = repository.findById(id);
        if (entityOptional.isPresent()) {
            repository.save(mapper.dtoToTypeUpdate(entity, entityOptional.get()));
            return true;
        }
        return false;
    }

    public List<TypeDto> getAllEntities() {
        Iterable<DogType> types = repository.findAll();
        List<TypeDto> dtos = new ArrayList<>();

        for (DogType type : types) {
            dtos.add(mapper.typeToDto(type));
        }

        return dtos;
    }

    public void addEntity(TypeDto typeDto) {
        DogType type = mapper.dtoToType(typeDto);
        repository.save(type);
    }

    public boolean exists(Long typeId) {
        return repository.existsById(typeId);
    }
}
