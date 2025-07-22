package at.bernhardangerer.gpxStatsHelper.util;

import com.topografix.model.Waypoint;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

final class ElevationWaypointFixture {

    private ElevationWaypointFixture() {
    }

    public static List<Waypoint> getWaypointsDownOnlySmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(575));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(572));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(572));
        waypoints.add(waypoint5);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsDownUpDownUp() {
        final List<Waypoint> waypoints = getWaypointsDownUpDown();
        final Waypoint waypoint8 = new Waypoint();
        waypoint8.setEle(BigDecimal.valueOf(530));
        waypoints.add(waypoint8);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsDownUpDown() {
        final List<Waypoint> waypoints = getWaypointsDownUp();
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint7);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsDownUpSmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(525));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(525));
        waypoints.add(waypoint7);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsUpOnlySmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(585));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(588));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(588));
        waypoints.add(waypoint5);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsUpDown() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint7);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsUpDownSmoothStartSmoothEnd() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint7);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsUpDownUpDown() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(500));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(505));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint6);
        final Waypoint waypoint7 = new Waypoint();
        waypoint7.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint7);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsUpDownUp() {
        final List<Waypoint> waypoints = getWaypointsUpDown();
        final Waypoint waypoint8 = new Waypoint();
        waypoint8.setEle(BigDecimal.valueOf(550));
        waypoints.add(waypoint8);
        final Waypoint waypoint9 = new Waypoint();
        waypoint9.setEle(BigDecimal.valueOf(600));
        waypoints.add(waypoint9);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsUpOnly() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(585));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(590));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(595));
        waypoints.add(waypoint3);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsDownUp() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(510));
        waypoints.add(waypoint3);
        final Waypoint waypoint4 = new Waypoint();
        waypoint4.setEle(BigDecimal.valueOf(515));
        waypoints.add(waypoint4);
        final Waypoint waypoint5 = new Waypoint();
        waypoint5.setEle(BigDecimal.valueOf(520));
        waypoints.add(waypoint5);
        final Waypoint waypoint6 = new Waypoint();
        waypoint6.setEle(BigDecimal.valueOf(525));
        waypoints.add(waypoint6);
        return waypoints;
    }

    public static List<Waypoint> getWaypointsDownOnly() {
        final List<Waypoint> waypoints = new ArrayList<>();
        final Waypoint waypoint1 = new Waypoint();
        waypoint1.setEle(BigDecimal.valueOf(580));
        waypoints.add(waypoint1);
        final Waypoint waypoint2 = new Waypoint();
        waypoint2.setEle(BigDecimal.valueOf(575));
        waypoints.add(waypoint2);
        final Waypoint waypoint3 = new Waypoint();
        waypoint3.setEle(BigDecimal.valueOf(572));
        waypoints.add(waypoint3);
        return waypoints;
    }
}
