package tpo.project.Controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tpo.project.DTO.*;
import tpo.project.Models.DogBreed;
import tpo.project.Services.AdoptionService;
import tpo.project.Services.BreedService;
import tpo.project.Services.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final BreedService breedService;
    private final AdoptionService adoptionService;

    public UserController (UserService userService, BreedService breedService, AdoptionService adoptionService) {
        this.userService = userService;
        this.breedService = breedService;
        this.adoptionService = adoptionService;
    }

    @GetMapping("/users/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userDTO", new SignupUserDto());
        return "signup";
    }

    @PostMapping("/users/register")
    public String processRegisterForm(@Valid @ModelAttribute("userDTO") SignupUserDto userDTO,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getAllErrors());
            return "signup";
        }
        Long id = userService.register(userDTO);
        return "redirect:/?id=" + id;
    }

    @GetMapping("/users/login")
    public String showLoginForm(Model model) {
        model.addAttribute("userDTO", new LoginUserDto());
        return "login";
    }

    @PostMapping("/users/login")
    public String processLoginForm(@Valid @ModelAttribute("userDTO") LoginUserDto userDTO,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getAllErrors());
            return "login";
        }
        Long id = userService.login(userDTO);
        if (id == null) {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
        return "redirect:/?id=" + id;
    }

    @PostMapping("/users/login/{userDto}")
    public String login(@PathVariable LoginUserDto userDto) {
        Long id = userService.login(userDto);
        if (id != null) {
            return "redirect:/id";
        } else {
            return "login";
        }
    }

    @GetMapping("/users/profile")
    public String userProfile(@RequestParam Long id, Model model) {
        ProfileUserDto dto = userService.findById(id);
        model.addAttribute("username", dto.getUsername());
        model.addAttribute("favourites", dto.getFavourites());
        return "profile";
    }

    @PostMapping("/users/{userId}/favorites/{dogId}")
    public String addFavorite(@PathVariable Long userId, @PathVariable Long dogId) {
        userService.addFavourite(userId, dogId);
        return "redirect:/userId/favourites";
    }

    @GetMapping("/users/{userId}/favorites")
    public String getFavorites(@PathVariable Long userId, Model model) {
        List<DogBreed> favorites = userService.getFavorites(userId);
        model.addAttribute("favourites", favorites);
        return "profile";
    }

    @GetMapping("/breeds/search")
    public String searchBreeds(@RequestParam String pattern, @RequestParam String filter,  Model model) {
        List<DogBreed> breeds = breedService.search(pattern);
        model.addAttribute("breeds", breeds);
        return "breeds";
    }

    @GetMapping("/breeds")
    public String showBreeds(@RequestParam(required = false) String sortBy, Model model) {
        List<BreedDto> breeds;
        if (sortBy != null) {
            breeds = breedService.getAllEntitiesSorted(sortBy);
        }
        else {
            breeds = breedService.getAllEntities();
        }
        if (breeds.size() > 0){
            model.addAttribute("breeds", breeds);
        }
        else {
            model.addAttribute("noResult", true);
        }
        return "breeds";
    }

    @GetMapping("/breeds/get")
    public String getBreedById(@RequestParam Long id, Model model) {
        BreedDto dto = breedService.getEntity(id);
        if (dto != null) {
            model.addAttribute("breed", dto);
        }
        return "dog";
    }

    @GetMapping("/adoption/get")
    public String getAdoptionById(@RequestParam Long id, Model model) {
        AdoptionDto dto = adoptionService.getEntity(id);
        if (dto != null) {
            model.addAttribute("option", dto);
        }
        return "adoption-option";
    }

    @GetMapping("/adoption/form/{userId}")
    public String getAdoptionForm(@PathVariable Long userId, @RequestParam Long id, Model model) {
        AdoptionDto dto = adoptionService.getEntity(id);

        if (dto != null) {
            model.addAttribute("adoption", id);
            model.addAttribute("userId", userId);
            return "adoption-form";
        }

        return "redirect:/";
    }

    @PostMapping("/adoption/submit")
    public String getAdoptionForm(@RequestParam("userId") Long userId, @RequestParam("dogId") Long dogId,
                                  @RequestParam("description") String description) {
        adoptionService.sendAdoptionForm(userId, dogId, description);

        return "redirect:/?id=" + userId;
    }


}
