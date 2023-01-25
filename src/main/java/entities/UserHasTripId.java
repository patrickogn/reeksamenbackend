package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserHasTripId implements Serializable {
    private static final long serialVersionUID = -5731798813186489438L;
    @NotNull
    @Column(name = "user_iduser", nullable = false)
    private Integer userIduser;

    @NotNull
    @Column(name = "trip_idtrip", nullable = false)
    private Integer tripIdtrip;

    public Integer getUserIduser() {
        return userIduser;
    }

    public void setUserIduser(Integer userIduser) {
        this.userIduser = userIduser;
    }

    public Integer getTripIdtrip() {
        return tripIdtrip;
    }

    public void setTripIdtrip(Integer tripIdtrip) {
        this.tripIdtrip = tripIdtrip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHasTripId entity = (UserHasTripId) o;
        return Objects.equals(this.userIduser, entity.userIduser) &&
                Objects.equals(this.tripIdtrip, entity.tripIdtrip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userIduser, tripIdtrip);
    }

}