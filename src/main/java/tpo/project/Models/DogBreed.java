package tpo.project.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class DogBreed {
    public DogBreed() {

    }

    public DogBreed(String name, String description, String origin, int lifespan, double weight, double height, Size size, String appearance, List<Colour> colours, DogType type){
        this.name = name;
        this.description = description;
        this.origin = origin;
        this.lifespan = lifespan;
        this.weight = weight;
        this.height = height;
        this.size = size;
        this.appearance = appearance;
        this.colours = colours;
        this.type = type;
    }

    public enum Size {
        small, medium, large
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String origin;
    private int lifespan;
    private double weight;
    private double height;
    private Size size;
    private String appearance;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "dogbreed_colour",
            joinColumns = @JoinColumn(name = "dogbreed_id"),
            inverseJoinColumns = @JoinColumn(name = "colour_id")
    )
    private List<Colour> colours = new ArrayList<>();

    @ManyToOne
    private DogType type;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getHeight() {
        return height;
    }

    public DogType getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public int getLifespan() {
        return lifespan;
    }

    public Size getSize() {
        return size;
    }

    public String getOrigin() {
        return origin;
    }

    public String getAppearance() {
        return appearance;
    }

    public List<Colour> getColours() {
        return colours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setLifespan(int lifespan) {
        this.lifespan = lifespan;
    }

    public void setColours(List<Colour> colours) {
        this.colours = colours;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setType(DogType type) {
        this.type = type;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
