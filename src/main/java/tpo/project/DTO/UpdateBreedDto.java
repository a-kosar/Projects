package tpo.project.DTO;

import jakarta.validation.constraints.NotBlank;

public class UpdateBreedDto {
    @NotBlank(message = "Name cannot be empty.")
    private String name;
    private String description;
    private String appearance;

    public UpdateBreedDto() {
    }

    public UpdateBreedDto(String name, String description, String appearance) {
        this.name = name;
        this.description = description;
        this.appearance = appearance;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getDescription() {
        return description;
    }
}
