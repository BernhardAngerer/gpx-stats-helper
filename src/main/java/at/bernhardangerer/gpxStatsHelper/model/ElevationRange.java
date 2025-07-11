package at.bernhardangerer.gpxStatsHelper.model;

import com.topografix.model.Waypoint;
import lombok.Data;

@Data
public final class ElevationRange {
    private Waypoint highest;
    private Waypoint lowest;

}
