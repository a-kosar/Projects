package tpo.project.Controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tpo.project.DTO.ColourDto;
import tpo.project.Services.ColourService;

import java.util.List;

@Controller
@RequestMapping("/admin/colours")
public class AdminColourController {

    private final ColourService colourService;

    public AdminColourController(ColourService colourService) {
        this.colourService = colourService;
    }

    @GetMapping
    public String showColourAdmin(@RequestParam(required = false) String action,
                                Model model) {
        model.addAttribute("action", action);
        return "admin-views/admin-colours";
    }

    @PostMapping
    public String handleColourAction(@RequestParam String action, Model model) {
        model.addAttribute("action", action);
        model.addAttribute("colourDto", new ColourDto());
        model.addAttribute("updateDto", new ColourDto());
        return "admin-views/admin-colours";
    }

    @PostMapping("/add")
    public String addColour(@Valid @ModelAttribute("colourDto") ColourDto colourDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAllAttributes(bindingResult.getAllErrors());
            return "admin-views/admin-colours";
        }
        else {
            colourService.addEntity(colourDto);
            return "redirect:/admin/colours";
        }
    }

    @GetMapping("/put")
    public String updateColour(@RequestParam Long id, @ModelAttribute ColourDto updateDto, Model model) {
        if (colourService.updateEntity(updateDto, id)) {
            model.addAttribute("success", "Colour updated successfully.");
        } else {
            model.addAttribute("error", "Error updating colour.");
        }
        return "admin-views/admin-colours";
    }

    @GetMapping("/delete")
    public String deleteColour(@RequestParam Long id, Model model) {
        if (colourService.deleteEntity(id)) {
            model.addAttribute("success", "Colour deleted successfully.");
        } else {
            model.addAttribute("error", "Error deleting colour.");
        }
        return "admin-views/admin-colours";
    }

    @GetMapping("/get")
    public String getColourById(@RequestParam Long id, Model model) {
        ColourDto dto = colourService.getEntity(id);
        if (dto == null) {
            model.addAttribute("error", "Colour doesn't exist.");
        } else {
            model.addAttribute("colour", dto);
        }
        return "admin-views/admin-colours";
    }

    @GetMapping("/all")
    public String getAllColours(Model model) {
        List<ColourDto> dtos = colourService.getAllEntities();
        model.addAttribute("colourDtos", dtos);
        return "admin-views/admin-colours";
    }
}

