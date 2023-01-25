package entities;

import javax.persistence.*;

@Entity
@Table(name = "trip_has_guide")
public class TripHasGuide {
    @EmbeddedId
    private TripHasGuideId id;

    @MapsId("tripIdtrip")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_idtrip", nullable = false)
    private Trip tripIdtrip;

    @MapsId("guideIdguide")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guide_idguide", nullable = false)
    private Guide guideIdguide;

    public TripHasGuideId getId() {
        return id;
    }

    public void setId(TripHasGuideId id) {
        this.id = id;
    }

    public Trip getTripIdtrip() {
        return tripIdtrip;
    }

    public void setTripIdtrip(Trip tripIdtrip) {
        this.tripIdtrip = tripIdtrip;
    }

    public Guide getGuideIdguide() {
        return guideIdguide;
    }

    public void setGuideIdguide(Guide guideIdguide) {
        this.guideIdguide = guideIdguide;
    }

}