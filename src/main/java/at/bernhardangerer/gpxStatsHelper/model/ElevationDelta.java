package at.bernhardangerer.gpxStatsHelper.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public final class ElevationDelta {
    private BigDecimal ascent = BigDecimal.ZERO;
    private BigDecimal descent = BigDecimal.ZERO;
}
