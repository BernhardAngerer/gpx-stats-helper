package at.bernhardangerer.gpxStatsHelper.model;

import java.math.BigDecimal;

public final class BoundingBox {
    private final BigDecimal minLat;
    private final BigDecimal minLon;
    private final BigDecimal maxLat;
    private final BigDecimal maxLon;

    public BoundingBox(BigDecimal minLat, BigDecimal minLon, BigDecimal maxLat, BigDecimal maxLon) {
        this.minLat = minLat;
        this.minLon = minLon;
        this.maxLat = maxLat;
        this.maxLon = maxLon;
    }

    public BigDecimal getMinLat() {
        return minLat;
    }

    public BigDecimal getMinLon() {
        return minLon;
    }

    public BigDecimal getMaxLat() {
        return maxLat;
    }

    public BigDecimal getMaxLon() {
        return maxLon;
    }

    @Override
    public String toString() {
        return "BoundingBox {"
                + "minLat=" + minLat
                + ", minLon=" + minLon
                + ", maxLat=" + maxLat
                + ", maxLon=" + maxLon
                + '}';
    }
}
