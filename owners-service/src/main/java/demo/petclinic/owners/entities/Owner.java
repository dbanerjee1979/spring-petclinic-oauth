package demo.petclinic.owners.entities;

import demo.petclinic.owners.dtos.OwnerDto;

import javax.persistence.*;
import java.util.*;

@NamedEntityGraphs({
    @NamedEntityGraph(name = "Owner.withPets",
            attributeNodes = @NamedAttributeNode("pets")),
    @NamedEntityGraph(name = "Owner.withPetsAndVisits",
            attributeNodes = @NamedAttributeNode("pets"))
})
@Entity
@Table
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String telephone;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    @OrderBy("id ASC")
    private List<Pet> pets;

    public Owner() {
    }

    public Owner(String firstName, String lastName, String address, String city, String telephone, Pet... pets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.pets = new ArrayList<>(Arrays.asList(pets));
        this.pets.stream().forEach(pet -> pet.setOwner(this));
    }

    public Owner(OwnerDto owner) {
        merge(owner);
    }

    public Owner merge(OwnerDto owner) {
        this.firstName = owner.getFirstName();
        this.lastName = owner.getLastName();
        this.address = owner.getAddress();
        this.city = owner.getCity();
        this.telephone = owner.getTelephone();
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
