package tpo.project.DTO;

import jakarta.validation.constraints.*;
import tpo.project.Validation.ValidColours;
import tpo.project.Validation.ValidType;

import java.util.ArrayList;
import java.util.List;

public class BreedDto {
    @NotBlank(message = "Name cannot be empty.")
    private String name;

    @NotBlank(message = "Description cannot be empty")
    private String description;

    @NotBlank(message = "Origin cannot be empty")
    private String origin;

    @Positive(message = "Lifespan must be positive.")
    private int lifespan;

    @Positive(message = "Weight must be positive.")
    private double weight;

    @Positive(message = "Height must be positive.")
    private double height;

    @NotBlank(message = "Size cannot be empty.")
    private String size;

    @NotBlank(message = "Appearance cannot be empty.")
    private String appearance;

    @NotEmpty(message = "Colours list cannot be empty.")
    @ValidColours()
    private String colours;

    @NotNull(message = "Type id cannot be null.")
    @Positive(message = "Type id must be positive.")
    @ValidType()
    private Long type;

    public BreedDto(String name, String description, String origin, int lifespan, double weight, double height, String size, String appearance, String colours, Long type) {
        this.name = name;
        this.description = description;
        this.origin = origin;
        this.lifespan = lifespan;
        this.weight = weight;
        this.height = height;
        this.size = size;
        this.colours = colours;
        this.appearance = appearance;
        this.type = type;
    }

    public BreedDto(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getLifespan() {
        return lifespan;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getColours() {
        return colours;
    }

    public void setColours(String colours) {
        this.colours = colours;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getType() {
        return type;
    }

    public List<Long> getColoursList() {
        List<Long> colourIds = new ArrayList<>();
        String[] coloursSplit = colours.split("\\s+");
        for (String colour : coloursSplit) {
            colourIds.add(Long.parseLong(colour));
        }
        return colourIds;
    }
}
