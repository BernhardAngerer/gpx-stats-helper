package at.bernhardangerer.gpxStatsHelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AscentDescentPair {
    private BigDecimal ascent;
    private BigDecimal descent;

    /**
     * Represents a summary of elevation changes over a route or track.
     * <p>
     * The elevation profile includes:
     * <ul>
     *     <li>{@code ascent} – total elevation gained (positive slope)</li>
     *     <li>{@code descent} – total elevation lost (negative slope)</li>
     * </ul>
     * Each value may be {@code null} if not computed or applicable.
     *
     * @return sum of all fields
     */
    public BigDecimal sum() {
        return (getAscent() != null ? getAscent() : BigDecimal.ZERO)
                .add(getDescent() != null ? getDescent() : BigDecimal.ZERO);
    }
}
