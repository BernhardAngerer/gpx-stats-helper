package at.bernhardangerer.gpxStatsHelper.enumeration;

import lombok.Getter;

@Getter
public enum SlopeSensitivity {
    /**
     * Maximum sensitivity;
     * No smoothing; reacts to every change in elevation, even the smallest.
     * Best for precise analysis on very clean data, but extremely noisy on GPS tracks.
     */
    HIGHEST(2, 0.000),

    /**
     * Very responsive smoothing; good for mountaineering or steep terrain
     * where detecting short, sharp changes is important.
     */
    HIGH(3, 0.004),

    /**
     * Balanced smoothing; good general-purpose default.
     */
    MEDIUM(5, 0.007),

    /**
     * Stable smoothing; filters out most noise.
     * Good for cycling or data with significant GPS elevation fluctuations.
     */
    LOW(7, 0.01);

    private final int windowSize;
    private final double slopeThreshold;

    SlopeSensitivity(final int windowSize, final double slopeThreshold) {
        this.windowSize = windowSize;
        this.slopeThreshold = slopeThreshold;
    }
}
