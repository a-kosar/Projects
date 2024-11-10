package tpo.project.Services;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tpo.project.DTO.BreedDto;
import tpo.project.DTO.TypeDto;
import tpo.project.DTO.UpdateBreedDto;
import tpo.project.Models.Colour;
import tpo.project.Models.DogBreed;
import tpo.project.Models.DogType;
import tpo.project.Repositories.BreedRepository;
import tpo.project.Repositories.ColourRepository;
import tpo.project.Repositories.TypeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BreedService extends AbstractService<DogBreed, Long> {
    private final BreedRepository repository;
    private final ColourRepository colourRepository;
    private final TypeRepository typeRepository;
    private final MyMapper mapper;

    public BreedService(BreedRepository repository, ColourRepository colourRepository, TypeRepository typeRepository, MyMapper mapper) {
        super(repository);
        this.repository = repository;
        this.colourRepository = colourRepository;
        this.typeRepository = typeRepository;
        this.mapper = mapper;
    }

    public List<BreedDto> getAllEntitiesSorted(String sortBy) {
        List<DogBreed> breeds = switch (sortBy) {
            case "name" -> repository.findAllEntitiesSortedByName();
            case "lifespan" -> repository.findAllEntitiesSortedByLifespan();
            case "size" -> repository.findAllEntitiesSortedBySize();
            default -> repository.findAll();
        };

        return breeds.stream()
                .map(mapper::breedToDto)
                .collect(Collectors.toList());
    }

    public List<DogBreed> search(String pattern) {
        return repository.findByNameStartingWith(pattern);
    }

    public BreedDto getEntity(Long id) {
        Optional<DogBreed> optional = repository.findById(id);
        return optional.map(mapper::breedToDto).orElse(null);
    }

    public boolean updateEntity(UpdateBreedDto entity, Long id) {
        Optional<DogBreed> entityOptional = repository.findById(id);
        if (entityOptional.isPresent()) {
            repository.save(mapper.dtoToBreedUpdate(entity, entityOptional.get()));
            return true;
        }
        return false;
    }

    public List<BreedDto> getAllEntities() {
        Iterable<DogBreed> breeds = repository.findAll();
        List<BreedDto> dtos = new ArrayList<>();

        for (DogBreed breed : breeds) {
            dtos.add(mapper.breedToDto(breed));
        }

        return dtos;
    }

    public boolean addEntity(BreedDto breedDto) {
        List<Colour> colours = new ArrayList<>();
        for (Long id: breedDto.getColoursList()){
            Optional<Colour> colour = colourRepository.findById(id);
            if (colour.isPresent()) {
                colours.add(colour.get());
            }
            else {
                return false;
            }
        }

        Optional<DogType> type = typeRepository.findById(breedDto.getType());
        DogType dogType;
        if (type.isPresent()) {
            dogType = type.get();
        }
        else {
            return false;
        }

        DogBreed breed = mapper.dtoToBreed(breedDto, colours, dogType);
        repository.save(breed);
        return true;
    }
}
