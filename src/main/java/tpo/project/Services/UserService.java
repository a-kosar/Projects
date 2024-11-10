package tpo.project.Services;

import org.springframework.stereotype.Service;
import tpo.project.DTO.LoginUserDto;
import tpo.project.DTO.ProfileUserDto;
import tpo.project.DTO.SignupUserDto;
import tpo.project.Models.DogBreed;
import tpo.project.Models.RegisteredUser;
import tpo.project.Repositories.BreedRepository;
import tpo.project.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BreedRepository breedRepository;
    private final MyMapper userMapper;

    public UserService(UserRepository repository, BreedRepository breedRepository, MyMapper userMapper) {
        this.userRepository = repository;
        this.breedRepository = breedRepository;
        this.userMapper = userMapper;
    }

    public Long register(SignupUserDto userDto) {
        RegisteredUser user = userMapper.dtoToUser(userDto);
        userRepository.save(user);
        return user.getId();
    }

    public Long login(LoginUserDto userDto) {
        Optional<RegisteredUser> optionalUser = userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        return optionalUser.map(RegisteredUser::getId).orElse(null);

    }

    public void addFavourite(Long userId, long breedId) {
        RegisteredUser user = userRepository.findById(userId).get();
        DogBreed breed = breedRepository.findById(breedId).get();
        user.addFavourite(breed);
    }

    public List<DogBreed> getFavorites(Long userId) {
        return userRepository.findFavouritesByUserId(userId);
    }

    public ProfileUserDto findById(Long id) {
        Optional<RegisteredUser> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()){
            ProfileUserDto user = userMapper.userToProfileDto(userOptional.get());
            return user;
        }
        return null;
    }
}
