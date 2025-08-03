package at.bernhardangerer.gpxStatsHelper.model;

import com.topografix.model.Waypoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ElevationExtremes {
    private Waypoint highestPoint;
    private Waypoint lowestPoint;
}
