package tpo.project;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import tpo.project.DTO.AdoptionDto;
import tpo.project.DTO.BreedDto;
import tpo.project.DTO.ColourDto;
import tpo.project.DTO.TypeDto;
import tpo.project.Models.Colour;
import tpo.project.Models.DogBreed;
import tpo.project.Models.DogType;
import tpo.project.Repositories.BreedRepository;
import tpo.project.Repositories.ColourRepository;
import tpo.project.Repositories.TypeRepository;
import tpo.project.Services.AdoptionService;
import tpo.project.Services.BreedService;
import tpo.project.Services.ColourService;
import tpo.project.Services.TypeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
