package tpo.project.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RegisteredUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;

    @OneToMany
    private List<DogBreed> favourites;


    public RegisteredUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public RegisteredUser() {

    }

    public String getUsername() {
        return username;
    }

    public void addFavourite(DogBreed breed) {
        favourites.add(breed);
    }

    public List<DogBreed> getFavourites() {
        return favourites;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
