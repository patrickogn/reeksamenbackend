package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtrip", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;

    @Size(max = 45)
    @Column(name = "date", length = 45)
    private String date;

    @Size(max = 45)
    @Column(name = "time", length = 45)
    private String time;

    @Size(max = 45)
    @Column(name = "location", length = 45)
    private String location;

    @Size(max = 45)
    @Column(name = "duration", length = 45)
    private String duration;

    @Size(max = 45)
    @Column(name = "packinglist", length = 45)
    private String packinglist;

    @ManyToMany
    @JoinTable(name = "trip_has_guide",
            joinColumns = @JoinColumn(name = "trip_idtrip"),
            inverseJoinColumns = @JoinColumn(name = "guide_idguide"))
    private Set<Guide> guides = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "user_has_trip",
            joinColumns = @JoinColumn(name = "trip_idtrip"),
            inverseJoinColumns = @JoinColumn(name = "user_iduser"))
    private Set<User> users = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPackinglist() {
        return packinglist;
    }

    public void setPackinglist(String packinglist) {
        this.packinglist = packinglist;
    }

    public Set<Guide> getGuides() {
        return guides;
    }

    public void setGuides(Set<Guide> guides) {
        this.guides = guides;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}