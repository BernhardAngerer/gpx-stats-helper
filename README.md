# GPX stats helper library
This Java library provides static helper methods to read from GPX 1.1 files and calculate the following parameters: 
+ Total distance [km]
+ Ascent/descent elevation [m]
+ Lowest/highest elevation [m]
+ Total duration [h]
+ Duration in motion [h]
+ Start time [h]
+ End time [h]
+ Max speed [km/h]
+ Average speed [km/h]
+ Start position [lon, lat]
+ End position [lon, lat]
+ Start geo-position (using OpenStreetMap API data)
+ End geo-position (using OpenStreetMap API data)

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
```

### Output of example.gpx executing Example.main():
```java
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
```
