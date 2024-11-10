package tpo.project.Services;

import org.springframework.stereotype.Service;
import tpo.project.DTO.*;
import tpo.project.Models.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyMapper {
    public RegisteredUser dtoToUser(SignupUserDto userDto){
        return new RegisteredUser(userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword());
    }

    public TypeDto typeToDto(DogType type){
        return new TypeDto(
                type.getGroup(),
                type.getPurpose(),
                type.isFamilyFriendly()
        );
    }

    public DogType dtoToType(TypeDto type){
        return new DogType(
                type.getGroup(),
                type.getPurpose(),
                type.isFamilyFriendly()
        );
    }

    public DogType dtoToTypeUpdate(TypeDto dto, DogType existingEntity) {
        if (dto.getGroup() != null && !dto.getGroup().isEmpty()) {
            existingEntity.setGroup(dto.getGroup());
        }
        if (dto.getPurpose() != null && !dto.getPurpose().isEmpty()) {
            existingEntity.setPurpose(dto.getPurpose());
        }
        existingEntity.setFamilyFriendly(dto.isFamilyFriendly());

        return existingEntity;
    }

    public ColourDto colourToDto(Colour colour) {
        return new ColourDto(
                colour.getCode(),
                colour.getName()
        );
    }

    public Colour dtoToColourUpdate(ColourDto dto, Colour existingColour) {
        if (dto.getCode() != null && !dto.getCode().isEmpty()) {
            existingColour.setCode(dto.getCode());
        }
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            existingColour.setName(dto.getName());
        }
        return existingColour;
    }

    public Colour dtoToColour(ColourDto colourDto) {
        return new Colour(
                colourDto.getCode(),
                colourDto.getName()
        );
    }

    public static DogBreed.Size stringToSize(String sizeString) {
        switch (sizeString.toLowerCase()) {
            case "small":
                return DogBreed.Size.small;
            case "medium":
                return DogBreed.Size.medium;
            case "large":
                return DogBreed.Size.large;
        }
        return null;
    }

    public BreedDto breedToDto(DogBreed breed){
        return new BreedDto(
                breed.getName(),
                breed.getDescription(),
                breed.getOrigin(),
                breed.getLifespan(),
                breed.getWeight(),
                breed.getHeight(),
                breed.getSize().toString(),
                breed.getAppearance(),
                breed.getColours().stream()
                .map(Colour::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")),
                breed.getType().getId()
        );
    }

    public DogBreed dtoToBreed(BreedDto breedDto, List<Colour> colours, DogType type) {
        return new DogBreed(
                breedDto.getName(),
                breedDto.getDescription(),
                breedDto.getOrigin(),
                breedDto.getLifespan(),
                breedDto.getWeight(),
                breedDto.getHeight(),
                stringToSize(breedDto.getSize()),
                breedDto.getAppearance(),
                colours,
                type
        );
    }

    public DogBreed dtoToBreedUpdate(UpdateBreedDto dto, DogBreed existingBreed) {
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            existingBreed.setName(dto.getName());
        }
        if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
            existingBreed.setDescription(dto.getDescription());
        }
        if (dto.getAppearance() != null && !dto.getAppearance().isEmpty()) {
            existingBreed.setAppearance(dto.getAppearance());
        }
        return existingBreed;
    }

    public AdoptionDto adoptionToDto(AdoptionOption adoptionOption) {
        return new AdoptionDto(
                adoptionOption.getName(),
                adoptionOption.getSex().toString(),
                adoptionOption.getAge(),
                adoptionOption.getWeight(),
                adoptionOption.getDescription(),
                adoptionOption.getAppearance(),
                adoptionOption.isAvailability()
        );
    }

    public AdoptionOption dtoToAdoptionUpdate(AdoptionDto entity, AdoptionOption adoptionOption) {
        adoptionOption.setName(entity.getName());
        adoptionOption.setSex(AdoptionOption.Sex.valueOf(entity.getSex()));
        adoptionOption.setAge(entity.getAge());
        adoptionOption.setWeight(entity.getWeight());
        adoptionOption.setDescription(entity.getDescription());
        adoptionOption.setAppearance(entity.getAppearance());
        adoptionOption.setAvailability(entity.isAvailability());
        return adoptionOption;
    }

    public AdoptionOption dtoToAdoption(AdoptionDto adoptionDto) {
        return new AdoptionOption(
                adoptionDto.getName(),
                AdoptionOption.Sex.valueOf(adoptionDto.getSex()),
                adoptionDto.getAge(),
                adoptionDto.getWeight(),
                adoptionDto.getDescription(),
                adoptionDto.getAppearance(),
                adoptionDto.isAvailability()
        );
    }

    public ProfileUserDto userToProfileDto(RegisteredUser registeredUser) {
        return new ProfileUserDto(
                registeredUser.getUsername(),
                registeredUser.getFavourites()
        );
    }
}
