[![Maven Package](https://github.com/BernhardAngerer/gpx-stats-helper/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/BernhardAngerer/gpx-stats-helper/actions/workflows/maven-publish.yml)

# GPX stats helper library

This Java library provides static helper methods to read from GPX 1.1 files using the official XSD schema from [TopoGrafix](https://www.topografix.com/gpx.asp). The following parameters can then be calculated: 
+ Number of waypoints
+ Total distance [km]
+ Ascent/descent elevation [m]
+ Lowest/highest point [m]
+ Latitude Range [lat, lat]
+ Longitude Range [lon, lon]
+ Total duration [h]
+ Duration in motion / at rest [h]
+ Start/end time [h]
+ Max/average speed [km/h]
+ Start/end position [lon, lat]
+ Farthest position [lon, lat]
+ Positive and negative elevation peak positions [lon, lat]
+ Description of any geo-position (using the free [Nominatim API](https://nominatim.org/release-docs/develop/api/Reverse/) Reverse Geocoding (OpenStreetMap data))

## Technical requirements:
+ Java 11+

### Output of example.gpx executing Example.main():
```
Number Of Waypoints: 2108
Total Distance: 26,4 km
Ascent: 1008 m
Descent: 997 m
Highest Point: 1547 m.s.l.
Highest Point - Geoposition: Roßleitenlift Bergstation, Reitweg, Goriloch, Aschau im Chiemgau, Landkreis Rosenheim, Bayern, 83229, Deutschland / URL: https://nominatim.openstreetmap.org/ui/reverse.html?lat=47.754635&lon=12.355498&zoom=18&layer=poi
Lowest Point: 587 m.s.l.
Lowest Point - Geoposition: Rottauer Straße, Bergham, Kraimoos, Bernau am Chiemsee, Landkreis Rosenheim, Bayern, 83233, Deutschland / URL: https://nominatim.openstreetmap.org/ui/reverse.html?lat=47.80743&lon=12.378228&zoom=18&layer=poi
Latitude Range: 47.754356 => 47.80743
Longitude Range: 12.334281 => 12.378897
Start Time: 07.09.2021, 15:37:42 h
End Time: 07.09.2021, 18:14:16 h
Total Duration: 02:36:34 h
Duration In Motion: 02:15:36 h
Duration At Rest: 00:20:58 h
Maximum Speed: 51,9 km/h
Average Speed: 11,7 km/h
Start Position: Lat 47.80743 / Lon 12.378228
End Position: Lat 47.807346 / Lon 12.378055
Start = End - Geoposition: Rottauer Straße, Bergham, Kraimoos, Bernau am Chiemsee, Landkreis Rosenheim, Bayern, 83233, Deutschland / URL: https://nominatim.openstreetmap.org/ui/reverse.html?lat=47.80743&lon=12.378228&zoom=18&layer=poi
Farthest Point - Geoposition: Reitweg, Goriloch, Aschau im Chiemgau, Landkreis Rosenheim, Bayern, 83229, Deutschland / URL: https://nominatim.openstreetmap.org/ui/reverse.html?lat=47.754356&lon=12.355104&zoom=18&layer=poi
Positive Peak 1 - Geoposition: Roßleitenlift Bergstation, Reitweg, Goriloch, Aschau im Chiemgau, Landkreis Rosenheim, Bayern, 83229, Deutschland / URL: https://nominatim.openstreetmap.org/ui/reverse.html?lat=47.754635&lon=12.355498&zoom=18&layer=poi
Negative Peak 1 - Geoposition: Rottauer Straße, Bergham, Kraimoos, Bernau am Chiemsee, Landkreis Rosenheim, Bayern, 83233, Deutschland / URL: https://nominatim.openstreetmap.org/ui/reverse.html?lat=47.80743&lon=12.378228&zoom=18&layer=poi
Negative Peak 2 - Geoposition: Rottauer Straße, Bergham, Kraimoos, Bernau am Chiemsee, Landkreis Rosenheim, Bayern, 83233, Deutschland / URL: https://nominatim.openstreetmap.org/ui/reverse.html?lat=47.807049&lon=12.378118&zoom=18&layer=poi
```

### How to add project dependency to Maven or Gradle:

https://jitpack.io/private#BernhardAngerer/gpx-stats-helper/1.1.0
