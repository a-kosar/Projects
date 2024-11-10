package tpo.project.Controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tpo.project.DTO.BreedDto;
import tpo.project.DTO.UpdateBreedDto;
import tpo.project.Services.BreedService;

import java.util.List;

@Controller
@RequestMapping("/admin/breeds")
public class AdminBreedController {

    private final BreedService breedService;

    public AdminBreedController(BreedService breedService) {
        this.breedService = breedService;
    }

    @GetMapping
    public String showBreedAdmin(@RequestParam(required = false) String action,
                                Model model) {
        model.addAttribute("action", action);
        return "admin-views/admin-breeds";
    }

    @PostMapping
    public String handleBreedAction(@RequestParam String action, Model model) {
        model.addAttribute("action", action);
        model.addAttribute("breedDto", new BreedDto());
        model.addAttribute("updateDto", new BreedDto());
        return "admin-views/admin-breeds";
    }

    @GetMapping("/add")
    public String showAddBreedForm(Model model) {
        model.addAttribute("breedDto", new BreedDto());  // Initialize an empty BreedDto for the form
        return "admin-views/admin-breeds";  // Return the form view
    }

    @PostMapping("/add")
    public String addBreed(@Valid @ModelAttribute("breedDto") BreedDto breedDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getAllErrors());
            model.addAttribute("error", "Error adding breed.");
            return "admin-views/admin-breeds";
        }
        if (breedService.addEntity(breedDto)) {
            return "redirect:/admin/breeds";
        }
        else {
            model.addAttribute("error", "Error adding breed.");
            return "admin-views/admin-breeds";
        }
    }

    @GetMapping("/put")
    public String updateBreed(@RequestParam Long id, @ModelAttribute UpdateBreedDto updateDto, Model model) {
        if (breedService.updateEntity(updateDto, id)) {
            model.addAttribute("success", "Breed updated successfully.");
        } else {
            model.addAttribute("error", "Error updating breed.");
        }
        return "admin-views/admin-breeds";
    }

    @GetMapping("/delete")
    public String deleteBreed(@RequestParam Long id, Model model) {
        if (breedService.deleteEntity(id)) {
            model.addAttribute("success", "Breed deleted successfully.");
        } else {
            model.addAttribute("error", "Error deleting breed.");
        }
        return "admin-views/admin-breeds";
    }

    @GetMapping("/get")
    public String getBreedById(@RequestParam Long id, Model model) {
        BreedDto dto = breedService.getEntity(id);
        if (dto == null) {
            model.addAttribute("error", "Breed doesn't exist.");
        } else {
            model.addAttribute("breed", dto);
        }
        return "admin-views/admin-breeds";
    }

    @GetMapping("/all")
    public String getAllBreeds(Model model) {
        List<BreedDto> dtos = breedService.getAllEntities();
        model.addAttribute("breedDtos", dtos);
        return "admin-views/admin-breeds";
    }
}

