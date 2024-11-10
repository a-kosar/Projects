package tpo.project.Models;

public class AdoptionForm {
    private Long userId;
    private Long dogId;
    private String description;

    public AdoptionForm(Long userId, Long dogId, String description) {
        this.userId = userId;
        this.dogId = dogId;
        this.description = description;
    }
}
