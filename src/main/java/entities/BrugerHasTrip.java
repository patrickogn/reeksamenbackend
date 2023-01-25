package entities;

import javax.persistence.*;

@Entity
@Table(name = "bruger_has_trip")
public class BrugerHasTrip {
    @EmbeddedId
    private BrugerHasTripId id;

    @MapsId("brugerIdbruger")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bruger_idbruger", nullable = false)
    private Bruger brugerIdbruger;

    @MapsId("tripIdtrip")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_idtrip", nullable = false)
    private Trip tripIdtrip;

    public BrugerHasTripId getId() {
        return id;
    }

    public void setId(BrugerHasTripId id) {
        this.id = id;
    }

    public Bruger getBrugerIdbruger() {
        return brugerIdbruger;
    }

    public void setBrugerIdbruger(Bruger brugerIdbruger) {
        this.brugerIdbruger = brugerIdbruger;
    }

    public Trip getTripIdtrip() {
        return tripIdtrip;
    }

    public void setTripIdtrip(Trip tripIdtrip) {
        this.tripIdtrip = tripIdtrip;
    }

}