package at.bernhardangerer.gpxStatsHelper.model;

public final class DistanceDuration {

    private double distance;
    private long duration;

    public DistanceDuration() {
    }

    public DistanceDuration(final double distance, final long duration) {
        this.distance = distance;
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(final double distance) {
        this.distance = distance;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(final long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "DistanceDuration {"
            + "distance=" + distance
            + ", duration=" + duration
            + '}';
    }
}
