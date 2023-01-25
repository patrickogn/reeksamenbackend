package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TripHasGuideId implements Serializable {
    private static final long serialVersionUID = -6408764078579440457L;
    @NotNull
    @Column(name = "trip_idtrip", nullable = false)
    private Integer tripIdtrip;

    @NotNull
    @Column(name = "guide_idguide", nullable = false)
    private Integer guideIdguide;

    public Integer getTripIdtrip() {
        return tripIdtrip;
    }

    public void setTripIdtrip(Integer tripIdtrip) {
        this.tripIdtrip = tripIdtrip;
    }

    public Integer getGuideIdguide() {
        return guideIdguide;
    }

    public void setGuideIdguide(Integer guideIdguide) {
        this.guideIdguide = guideIdguide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripHasGuideId entity = (TripHasGuideId) o;
        return Objects.equals(this.tripIdtrip, entity.tripIdtrip) &&
                Objects.equals(this.guideIdguide, entity.guideIdguide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripIdtrip, guideIdguide);
    }

}