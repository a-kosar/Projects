package tpo.project.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class AdoptionDto {

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    @NotBlank(message = "Sex cannot be empty.")
    private String sex;

    @Positive(message = "Age must be a positive number.")
    private int age;

    @Positive(message = "Weight must be a positive number")
    private double weight;

    @NotBlank(message = "Description cannot be empty.")
    private String description;

    @NotBlank(message = "Appearance cannot be empty.")
    private String appearance;

    private boolean availability;

    public AdoptionDto() {
    }

    public AdoptionDto(String name, String sex, int age, double weight, String description, String appearance, boolean availability) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.description = description;
        this.appearance = appearance;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
