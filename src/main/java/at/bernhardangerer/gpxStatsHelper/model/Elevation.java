package at.bernhardangerer.gpxStatsHelper.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public final class Elevation {
    private BigDecimal ascent;
    private BigDecimal descent;
}
