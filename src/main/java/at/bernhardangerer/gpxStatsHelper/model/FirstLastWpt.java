package at.bernhardangerer.gpxStatsHelper.model;

import com.topografix.model.Waypoint;

public final class FirstLastWpt {

    private Waypoint first;
    private Waypoint last;

    public FirstLastWpt(final Waypoint first, final Waypoint last) {
        this.first = first;
        this.last = last;
    }

    public Waypoint getFirst() {
        return first;
    }

    public void setFirst(final Waypoint first) {
        this.first = first;
    }

    public Waypoint getLast() {
        return last;
    }

    public void setLast(final Waypoint last) {
        this.last = last;
    }
}
