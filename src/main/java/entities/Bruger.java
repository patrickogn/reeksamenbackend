package entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bruger")
public class Bruger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbruger", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "adress", length = 45)
    private String adress;

    @Size(max = 45)
    @Column(name = "phone", length = 45)
    private String phone;

    @Size(max = 45)
    @Column(name = "email", length = 45)
    private String email;

    @Size(max = 45)
    @Column(name = "birthyear", length = 45)
    private String birthyear;

    @Size(max = 45)
    @Column(name = "gender", length = 45)
    private String gender;

    @ManyToMany
    @JoinTable(name = "bruger_has_trip",
            joinColumns = @JoinColumn(name = "bruger_idbruger"),
            inverseJoinColumns = @JoinColumn(name = "trip_idtrip"))
    private Set<Trip> trips = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(String birthyear) {
        this.birthyear = birthyear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Trip> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trip> trips) {
        this.trips = trips;
    }

}