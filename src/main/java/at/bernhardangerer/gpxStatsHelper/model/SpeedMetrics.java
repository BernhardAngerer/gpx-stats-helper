package at.bernhardangerer.gpxStatsHelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public final class SpeedMetrics {
    private double distance;
    private long duration;
}
