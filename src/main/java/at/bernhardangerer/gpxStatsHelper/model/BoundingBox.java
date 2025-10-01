package at.bernhardangerer.gpxStatsHelper.model;

import java.math.BigDecimal;

public record BoundingBox(BigDecimal minLat, BigDecimal minLon, BigDecimal maxLat, BigDecimal maxLon) {
}
