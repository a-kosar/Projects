package tpo.project.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class DogType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "`group`")
    private String group;
    private String purpose;
    private boolean familyFriendly;

    public DogType(String group, String purpose, boolean familyFriendly) {
        this.group = group;
        this.purpose = purpose;
        this.familyFriendly = familyFriendly;
    }

    public DogType() {

    }

    //@OneToMany(mappedBy = "type")
  //  private List<DogBreed> breeds = new ArrayList<DogBreed>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public boolean isFamilyFriendly() {
        return familyFriendly;
    }

    public void setFamilyFriendly(boolean familyFriendly) {
        this.familyFriendly = familyFriendly;
    }
}
