package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BrugerHasTripId implements Serializable {
    private static final long serialVersionUID = -8138042975427078183L;
    @NotNull
    @Column(name = "bruger_idbruger", nullable = false)
    private Integer brugerIdbruger;

    @NotNull
    @Column(name = "trip_idtrip", nullable = false)
    private Integer tripIdtrip;

    public Integer getBrugerIdbruger() {
        return brugerIdbruger;
    }

    public void setBrugerIdbruger(Integer brugerIdbruger) {
        this.brugerIdbruger = brugerIdbruger;
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
        BrugerHasTripId entity = (BrugerHasTripId) o;
        return Objects.equals(this.tripIdtrip, entity.tripIdtrip) &&
                Objects.equals(this.brugerIdbruger, entity.brugerIdbruger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripIdtrip, brugerIdbruger);
    }

}