package tpo.project.Controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tpo.project.DTO.AdoptionDto;
import tpo.project.Services.AdoptionService;

import java.util.List;

@Controller
@RequestMapping("/admin/adoptions")
public class AdminAdoptionController {

    private final AdoptionService adoptionService;

    public AdminAdoptionController(AdoptionService adoptionService) {
        this.adoptionService = adoptionService;
    }

    @GetMapping
    public String showAdoptionAdmin(@RequestParam(required = false) String action,
                                    Model model) {
        model.addAttribute("action", action);
        return "admin-views/admin-adoptions";
    }

    @PostMapping
    public String handleAdoptionAction(@RequestParam String action, Model model) {
        model.addAttribute("action", action);
        model.addAttribute("adoptionDto", new AdoptionDto());
        model.addAttribute("updateDto", new AdoptionDto());
        return "admin-views/admin-adoptions";
    }

    @PostMapping("/add")
    public String addAdoption(@Valid @ModelAttribute("adoptionDto") AdoptionDto adoptionDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getAllErrors());
            return "admin-views/admin-adoptions";
        } else {
            adoptionService.addEntity(adoptionDto);
            return "redirect:/admin/adoptions";
        }
    }

    @GetMapping("/put")
    public String updateAdoption(@RequestParam Long id, @ModelAttribute AdoptionDto updateDto, Model model) {
        if (adoptionService.updateEntity(updateDto, id)) {
            model.addAttribute("success", "Adoption updated successfully.");
        } else {
            model.addAttribute("error", "Error updating adoption.");
        }
        return "admin-views/admin-adoptions";
    }

    @GetMapping("/delete")
    public String deleteAdoption(@RequestParam Long id, Model model) {
        if (adoptionService.deleteEntity(id)) {
            model.addAttribute("success", "Adoption deleted successfully.");
        } else {
            model.addAttribute("error", "Error deleting adoption.");
        }
        return "admin-views/admin-adoptions";
    }

    @GetMapping("/get")
    public String getAdoptionById(@RequestParam Long id, Model model) {
        AdoptionDto dto = adoptionService.getEntity(id);
        if (dto == null) {
            model.addAttribute("error", "Adoption doesn't exist.");
        } else {
            model.addAttribute("adoption", dto);
        }
        return "admin-views/admin-adoptions";
    }

    @GetMapping("/all")
    public String getAllAdoptions(Model model) {
        List<AdoptionDto> dtos = adoptionService.getAllEntities();
        model.addAttribute("adoptionDtos", dtos);
        return "admin-views/admin-adoptions";
    }
}
