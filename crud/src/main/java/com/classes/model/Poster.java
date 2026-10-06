package com.classes.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity 
public class Poster 
{
   public Poster(){}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title is required") @Size(min = 3, max = 30, message = "Title must be between 3 and 30 characters")
    public String title;

    @Pattern(regexp = "^[a-zA-Z0-9-]+$", message = "Location can only contain letters, digits and dashes")
    public String location;
    @Size(min = 0, max = 200, message = "Description can't be longer than 200 characters ")
    public String description;
    public String links;

    @NotBlank(message = "An image is required")
    public String img;

    @ManyToOne
    @JoinColumn (name = "id_users")
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Poster(int id, String eventName){
         this.id = id;
         this.title = title;
         this.location = location;
         this.description = description;
         this.links = links;
         this.img = img;
      
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return title;
    }
    public void setEventNamet(String eventName) {
        this.title = eventName;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinks() {
        return links;
    }
    public void setLinks(String links) {
        this.links = links;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    
    

}
