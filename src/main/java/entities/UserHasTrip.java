package entities;

import javax.persistence.*;

@Entity
@Table(name = "user_has_trip")
public class UserHasTrip {
    @EmbeddedId
    private UserHasTripId id;

    @MapsId("userIduser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_iduser", nullable = false)
    private Bruger userIduser;

    @MapsId("tripIdtrip")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_idtrip", nullable = false)
    private Trip tripIdtrip;

    public UserHasTripId getId() {
        return id;
    }

    public void setId(UserHasTripId id) {
        this.id = id;
    }

    public Bruger getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(Bruger userIduser) {
        this.userIduser = userIduser;
    }

    public Trip getTripIdtrip() {
        return tripIdtrip;
    }

    public void setTripIdtrip(Trip tripIdtrip) {
        this.tripIdtrip = tripIdtrip;
    }

}