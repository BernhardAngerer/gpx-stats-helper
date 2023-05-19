package at.bernhardangerer.gpxStatsHelper.model;

import com.topografix.model.WptType;

public final class FirstLastWpt {

    private WptType first;
    private WptType last;

    public FirstLastWpt(final WptType first, final WptType last) {
        this.first = first;
        this.last = last;
    }

    public WptType getFirst() {
        return first;
    }

    public void setFirst(final WptType first) {
        this.first = first;
    }

    public WptType getLast() {
        return last;
    }

    public void setLast(final WptType last) {
        this.last = last;
    }
}
