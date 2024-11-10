package tpo.project.Controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tpo.project.DTO.TypeDto;
import tpo.project.Services.TypeService;

import java.util.List;

@Controller
@RequestMapping("/admin/types")
public class AdminTypeController {

    private final TypeService typeService;

    public AdminTypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public String showTypeAdmin(@RequestParam(required = false) String action,
                                Model model) {
        model.addAttribute("action", action);
        return "admin-views/admin-types";
    }

    @PostMapping
    public String handleTypeAction(@RequestParam String action, Model model) {
        model.addAttribute("action", action);
        model.addAttribute("typeDto", new TypeDto());
        model.addAttribute("updateDto", new TypeDto());
        return "admin-views/admin-types";
    }

    @PostMapping("/add")
    public String addDogType(@Valid @ModelAttribute("typeDto") TypeDto typeDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
           model.addAllAttributes(bindingResult.getAllErrors());
            return "admin-views/admin-types";
        }
        else {
            typeService.addEntity(typeDto);
            return "redirect:/admin/types";
        }
    }

    @GetMapping("/put")
    public String updateDogType(@RequestParam Long id, @ModelAttribute TypeDto updateDto, Model model) {
        if (typeService.updateEntity(updateDto, id)) {
            model.addAttribute("success", "DogType updated successfully.");
        } else {
            model.addAttribute("error", "Error updating dog type.");
        }
        return "admin-views/admin-types";
    }

    @GetMapping("/delete")
    public String deleteDogType(@RequestParam Long id, Model model) {
        if (typeService.deleteEntity(id)) {
            model.addAttribute("success", "DogType deleted successfully.");
        } else {
            model.addAttribute("error", "Error deleting dog type.");
        }
        return "admin-views/admin-types";
    }

    @GetMapping("/get")
    public String getDogTypeById(@RequestParam Long id, Model model) {
        TypeDto dto = typeService.getEntity(id);
        if (dto == null) {
            model.addAttribute("error", "DogType doesn't exist.");
        } else {
            model.addAttribute("type", dto);
        }
        return "admin-views/admin-types";
    }

    @GetMapping("/all")
    public String getAllDogTypes(Model model) {
        List<TypeDto> dtos = typeService.getAllEntities();
        model.addAttribute("typeDtos", dtos);
        return "admin-views/admin-types";
    }
}

