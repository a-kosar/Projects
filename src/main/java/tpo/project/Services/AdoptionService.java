package tpo.project.Services;

import org.springframework.stereotype.Service;
import tpo.project.DTO.AdoptionDto;
import tpo.project.Models.AdoptionForm;
import tpo.project.Models.AdoptionOption;
import tpo.project.Models.RegisteredUser;
import tpo.project.Repositories.AdoptionRepository;
import tpo.project.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdoptionService extends AbstractService<AdoptionOption, Long>{
    private final UserRepository userRepository;
    private final MyMapper mapper;
    private List<AdoptionForm> adoptionForms = new ArrayList<>();

    public AdoptionService(AdoptionRepository adoptionRepository, UserRepository userRepository, MyMapper mapper) {
        super(adoptionRepository);
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public void sendAdoptionForm(Long userId, Long dogId, String description) {
        adoptionForms.add(new AdoptionForm(userId, dogId, description));
    }

    public AdoptionDto getEntity(Long id) {
        Optional<AdoptionOption> optional = repository.findById(id);
        return optional.map(mapper::adoptionToDto).orElse(null);
    }

    public boolean updateEntity(AdoptionDto entity, Long id) {
        Optional<AdoptionOption> entityOptional = repository.findById(id);
        if (entityOptional.isPresent()) {
            repository.save(mapper.dtoToAdoptionUpdate(entity, entityOptional.get()));
            return true;
        }
        return false;
    }

    public List<AdoptionDto> getAllEntities() {
        Iterable<AdoptionOption> adoptions = repository.findAll();
        List<AdoptionDto> dtos = new ArrayList<>();
        for (AdoptionOption adoption : adoptions) {
            dtos.add(mapper.adoptionToDto(adoption));
        }
        return dtos;
    }

    public void addEntity(AdoptionDto adoptionDto) {
        AdoptionOption adoption = mapper.dtoToAdoption(adoptionDto);
        repository.save(adoption);
    }
}
