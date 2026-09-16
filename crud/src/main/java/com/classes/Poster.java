package com.classes;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "posters")
public class Poster 
{
   public Poster(){}
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String location;
    private String description;
    private String links;
    private String imgURL;

    @ManyToOne
    @JoinColumn (name = "id_users")
    private User user;


    public Poster(int id, String eventName){
         this.id = id;
         this.title = title;
         this.location = location;
         this.description = description;
         this.links = links;
         this.imgURL = imgURL;
      
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

        public String getImgURL() {
        return imgURL;
    }
    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

}
