package tpo.project.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Colour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;

    @ManyToMany(mappedBy = "colours", fetch = FetchType.LAZY)
    private List<DogBreed> breeds = new ArrayList<>();

    public Colour(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Colour() {

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public List<DogBreed> getBreeds() {
        return breeds;
    }

    public void setBreeds(List<DogBreed> breeds) {
        this.breeds = breeds;
    }
}
