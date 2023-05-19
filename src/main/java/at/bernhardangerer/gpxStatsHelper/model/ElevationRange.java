package at.bernhardangerer.gpxStatsHelper.model;

import java.math.BigDecimal;

public final class ElevationRange {
    private BigDecimal highest;
    private BigDecimal lowest;

    public BigDecimal getHighest() {
        return highest;
    }

    public void setHighest(final BigDecimal highest) {
        this.highest = highest;
    }

    public BigDecimal getLowest() {
        return lowest;
    }

    public void setLowest(final BigDecimal lowest) {
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
