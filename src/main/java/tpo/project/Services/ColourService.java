package tpo.project.Services;

import jakarta.transaction.Transactional;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.stereotype.Service;
import tpo.project.DTO.ColourDto;
import tpo.project.DTO.TypeDto;
import tpo.project.Models.Colour;
import tpo.project.Models.DogType;
import tpo.project.Repositories.ColourRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ColourService extends AbstractService<Colour, Long> {
    private final MyMapper mapper;

    public ColourService(ColourRepository repository, MyMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    public ColourDto getEntity(Long id) {
        Optional<Colour> optional = repository.findById(id);
        return optional.map(mapper::colourToDto).orElse(null);
    }

    public boolean updateEntity(ColourDto entity, Long id) {
        Optional<Colour> entityOptional = repository.findById(id);
        if (entityOptional.isPresent()) {
            repository.save(mapper.dtoToColourUpdate(entity, entityOptional.get()));
            return true;
        }
        return false;
    }

    public List<ColourDto> getAllEntities() {
        Iterable<Colour> colours = repository.findAll();
        List<ColourDto> dtos = new ArrayList<>();
        for (Colour colour : colours) {
            dtos.add(mapper.colourToDto(colour));
        }

        return dtos;
    }

    public void addEntity(ColourDto colourDto) {
        Colour colour = mapper.dtoToColour(colourDto);
        repository.save(colour);
    }
}
