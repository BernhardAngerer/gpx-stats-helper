package at.bernhardangerer.gpxStatsHelper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class ElevationProfile extends AscentDescentPair {
    private BigDecimal flat;
    private BigDecimal unknown;

    public ElevationProfile(final BigDecimal ascent, final BigDecimal descent, final BigDecimal flat, final BigDecimal unknown) {
        super(ascent, descent);
        this.flat = flat;
        this.unknown = unknown;
    }

    /**
     * Represents a summary of elevation changes over a route or track.
     * <p>
     * The elevation profile includes:
     * <ul>
     *     <li>{@code ascent} – total elevation gained (positive slope)</li>
     *     <li>{@code flat} – total flat distance (no significant elevation change)</li>
     *     <li>{@code descent} – total elevation lost (negative slope)</li>
     *     <li>{@code unknown} – distance with undetermined or missing elevation data</li>
     * </ul>
     * Each value may be {@code null} if not computed or applicable.
     *
     * @return sum of all fields
     */
    public BigDecimal sum() {
        return (getAscent() != null ? getAscent() : BigDecimal.ZERO)
                .add(flat != null ? flat : BigDecimal.ZERO)
                .add(getDescent() != null ? getDescent() : BigDecimal.ZERO)
                .add(unknown != null ? unknown : BigDecimal.ZERO);
    }
}
