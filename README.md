[![Maven Package](https://github.com/BernhardAngerer/gpx-stats-helper/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/BernhardAngerer/gpx-stats-helper/actions/workflows/maven-publish.yml)

# GPX stats helper library

This Java library provides static helper methods to read from GPX 1.1 files using the official XSD schema from [TopoGrafix](https://www.topografix.com/gpx.asp). The following parameters can then be calculated: 
+ Total distance [km]
+ Ascent/descent elevation [m]
+ Lowest/highest elevation [m]
+ Total duration [h]
+ Duration in motion [h]
+ Start/end time [h]
+ Max/average speed [km/h]
+ Start/end position [lon, lat]
+ Positive and negative elevation peak positions [lon, lat]
+ Description of any geo-position (using the free [Nominatim API](https://nominatim.org/release-docs/develop/api/Reverse/) Reverse Geocoding (OpenStreetMap data))

## Technical requirements:
+ Java 11+

## Usage:
```java
final File file = new File(Example.class.getClassLoader().getResource("example/example1.gpx").getFile());
final GpxType gpx = GpxConverter.convertGpxFromFile(file);
final TrkType track = gpx.getTrk().get(0);

final Double totalDistance = DistanceTotalCalculator.fromTrk(track);

final ElevationDelta delta = ElevationDeltaCalculator.fromTrk(track);

final ElevationRange range = ElevationRangeCalculator.fromTrk(track);

final FirstLastWaypoint firstLastWaypoint = FirstLastWptCalculator.fromTrk(track);

final Duration totalDuration = calcDateTimeDifference(firstLast.getFirst().getTime(), firstLast.getLast().getTime());

final Long durationInMotion = DurationInMotionCalculator.fromTrk(track);

final Double maxSpeed = SpeedMaxCalculator.fromTrk(track);

final Double avgSpeed = SpeedAvgCalculator.fromTrack(track);

final GeocodeReverseModel startPos = GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocode(
    firstLast.getFirst().getLat().toString(), firstLast.getFirst().getLon().toString()));

final GeocodeReverseModel endPos = GeocodeUtil.convertFromJson(GEOCODE_SERVICE.reverseGeocode(
    firstLast.getLast().getLat().toString(), firstLast.getLast().getLon().toString()));

final List<Waypoint> positivePeaks =
    ElevationPeakUtil.findPositivePeaks(track.getTrkseg().get(0).getTrkpt(), BigDecimal.valueOf(100));

final List<Waypoint> negativePeaks =
    ElevationPeakUtil.findNegativePeaks(track.getTrkseg().get(0).getTrkpt(), BigDecimal.valueOf(100));
```

### Output of example.gpx executing Example.main():
```
total distance: 26,4 km
ascent: 1008 m
descent: 997 m
highest point: 1547 m.s.l.
lowest point: 587 m.s.l.
start time: 07.09.2021, 15:37:42 h
end time: 07.09.2021, 18:14:16 h
total duration: 02:36:34 h
duration in motion: 02:15:36 h
maximum speed: 51,9 km/h
average speed: 11,7 km/h
start position: Lat 47.80743 / Lon 12.378228
end position: Lat 47.807346 / Lon 12.378055
start geoposition: 16, Ulmenstraße, Westerham, Bergham, Bernau am Chiemsee, Landkreis Rosenheim, Bayern, 83233, Deutschland
end geoposition: 16, Ulmenstraße, Westerham, Bergham, Bernau am Chiemsee, Landkreis Rosenheim, Bayern, 83233, Deutschland
Positive Peak 1 - Geoposition: Roßleitenlift Bergstation, Reitweg, Goriloch, Aschau im Chiemgau, Landkreis Rosenheim, Bayern, 83229, Deutschland
Negative Peak 1 - Geoposition: Lindenstraße, Bergham, Kraimoos, Bernau am Chiemsee, Landkreis Rosenheim, Bayern, 83233, Deutschland
Negative Peak 2 - Geoposition: Lindenstraße, Bergham, Kraimoos, Bernau am Chiemsee, Landkreis Rosenheim, Bayern, 83233, Deutschland
```

### How to add project dependency to Maven or Gradle:

https://jitpack.io/private#BernhardAngerer/gpx-stats-helper/1.0.0
