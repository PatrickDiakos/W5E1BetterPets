/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package better_pets;

/**
 *
 * @author Rjian
 */
public class Pet {
    // Attributes of Pet
    private int id;
    private String name;
    private String species;
    private String colour;
    private String owner;

    // Create a constructor for a Pet class
    
    // Create getters and setters for a Pet class

    public Pet(int id, String name, String species, String colour, String owner) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.colour = colour;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    
}
