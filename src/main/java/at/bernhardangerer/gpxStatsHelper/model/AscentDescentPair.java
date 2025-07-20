package at.bernhardangerer.gpxStatsHelper.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AscentDescentPair {
    private BigDecimal ascent;
    private BigDecimal descent;
}
