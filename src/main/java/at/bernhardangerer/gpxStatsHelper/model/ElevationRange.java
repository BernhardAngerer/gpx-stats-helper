package at.bernhardangerer.gpxStatsHelper.model;

import com.topografix.model.Waypoint;

public final class ElevationRange {
    private Waypoint highest;
    private Waypoint lowest;

    public Waypoint getHighest() {
        return highest;
    }

    public void setHighest(final Waypoint highest) {
        this.highest = highest;
    }

    public Waypoint getLowest() {
        return lowest;
    }

    public void setLowest(final Waypoint lowest) {
        this.lowest = lowest;
    }

    @Override
    public String toString() {
        return "ElevationRange {"
            + "highest=" + highest
            + ", lowest=" + lowest
            + '}';
    }
}
