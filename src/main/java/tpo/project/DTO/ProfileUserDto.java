package tpo.project.DTO;

import tpo.project.Models.DogBreed;

import java.util.List;

public class ProfileUserDto {
    private String username;
    private List<DogBreed> favourites;

    public ProfileUserDto(String username, List<DogBreed> favourites) {
        this.username = username;
        this.favourites = favourites;
    }

    public ProfileUserDto(){}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<DogBreed> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<DogBreed> favourites) {
        this.favourites = favourites;
    }
}
