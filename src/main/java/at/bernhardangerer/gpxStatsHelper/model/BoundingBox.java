package at.bernhardangerer.gpxStatsHelper.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public final class BoundingBox {
    private final BigDecimal minLat;
    private final BigDecimal minLon;
    private final BigDecimal maxLat;
    private final BigDecimal maxLon;
}
