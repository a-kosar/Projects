package tpo.project.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TypeDto {

    @NotBlank(message = "Group cannot be empty.")
    private String group;

    @NotBlank(message = "Purpose cannot be empty.")
    private String purpose;

    private boolean familyFriendly;

    public TypeDto(String group, String purpose, boolean familyFriendly) {
        this.group = group;
        this.purpose = purpose;
        this.familyFriendly = familyFriendly;
    }

    public TypeDto() {

    }

    public boolean isFamilyFriendly() {
        return familyFriendly;
    }

    public String getGroup() {
        return group;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setFamilyFriendly(boolean familyFriendly) {
        this.familyFriendly = familyFriendly;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
