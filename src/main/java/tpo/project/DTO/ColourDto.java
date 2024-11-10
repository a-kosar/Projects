package tpo.project.DTO;

import jakarta.validation.constraints.NotBlank;

public class ColourDto {
    @NotBlank(message = "Code cannot be empty.")
    private String code;

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    public ColourDto() {}

    public ColourDto(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
