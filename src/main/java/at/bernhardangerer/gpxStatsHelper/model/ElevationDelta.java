package at.bernhardangerer.gpxStatsHelper.model;

import java.math.BigDecimal;

public final class ElevationDelta {
    private BigDecimal ascent = BigDecimal.ZERO;
    private BigDecimal descent = BigDecimal.ZERO;

    public BigDecimal getAscent() {
        return ascent;
    }

    public void setAscent(final BigDecimal ascent) {
        this.ascent = ascent;
    }

    public BigDecimal getDescent() {
        return descent;
    }

    public void setDescent(final BigDecimal descent) {
        this.descent = descent;
    }

    @Override
    public String toString() {
        return "ElevationDelta {"
            + "ascent=" + ascent
            + ", descent=" + descent
            + '}';
    }
}
