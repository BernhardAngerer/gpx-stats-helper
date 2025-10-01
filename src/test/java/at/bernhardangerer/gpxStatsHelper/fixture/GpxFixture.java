package at.bernhardangerer.gpxStatsHelper.fixture;

public final class GpxFixture {

    public static final String GPX = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <gpx version="1.1" creator="me"
            xsi:schemaLocation="http://www.topografix.com/GPX/1/1
            http://www.topografix.com/GPX/1/1/gpx.xsd
            http://www.garmin.com/xmlschemas/GpxExtensions/v3
            http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd
            http://www.garmin.com/xmlschemas/TrackPointExtension/v1
            http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd"
            xmlns="http://www.topografix.com/GPX/1/1"
            xmlns:gpxtpx="http://www.garmin.com/xmlschemas/TrackPointExtension/v1"
            xmlns:gpxx="http://www.garmin.com/xmlschemas/GpxExtensions/v3"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <metadata>
            <link>https://www.my-url.at</link></metadata>
            <trk>
            <trkseg>
            <trkpt lat="47.80743" lon="12.378228"><ele>587</ele><time>2021-09-07T13:37:42Z</time> </trkpt>
            <trkpt lat="47.807343" lon="12.378138"><ele>588</ele><time>2021-09-07T13:38:18Z</time> </trkpt>
            <trkpt lat="47.807335" lon="12.378"><ele>588</ele><time>2021-09-07T13:38:25Z</time> </trkpt>
            <trkpt lat="47.807377" lon="12.377702"><ele>588</ele><time>2021-09-07T13:38:31Z</time> </trkpt>
            <trkpt lat="47.807346" lon="12.37751"><ele>588</ele><time>2021-09-07T13:38:34Z</time> </trkpt>
            <trkpt lat="47.807312" lon="12.377382"><ele>586</ele><time>2021-09-07T13:38:36Z</time> </trkpt>
            </trkseg>
            <trkseg>
            <trkpt lat="47.806938" lon="12.378183"><ele>598</ele><time>2021-09-07T16:10:27Z</time> </trkpt>
            <trkpt lat="47.806793" lon="12.378217"><ele>598</ele><time>2021-09-07T16:11:20Z</time> </trkpt>
            <trkpt lat="47.806873" lon="12.378148"><ele>598</ele><time>2021-09-07T16:13:51Z</time> </trkpt>
            <trkpt lat="47.806969" lon="12.37816"><ele>598</ele><time>2021-09-07T16:13:55Z</time> </trkpt>
            <trkpt lat="47.80706" lon="12.378138"><ele>598</ele><time>2021-09-07T16:14:00Z</time> </trkpt>
            <trkpt lat="47.807156" lon="12.378108"><ele>598</ele><time>2021-09-07T16:14:06Z</time> </trkpt>
            <trkpt lat="47.807251" lon="12.378079"><ele>598</ele><time>2021-09-07T16:14:11Z</time> </trkpt>
            <trkpt lat="47.807346" lon="12.38"><ele>596</ele><time>2021-09-07T16:14:16Z</time> </trkpt>
            </trkseg>
            </trk>
            </gpx>""";

    public static final String GPX_WITHOUT_ELEVATION = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <gpx version="1.1" creator="me"
            xsi:schemaLocation="http://www.topografix.com/GPX/1/1
            http://www.topografix.com/GPX/1/1/gpx.xsd
            http://www.garmin.com/xmlschemas/GpxExtensions/v3
            http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd
            http://www.garmin.com/xmlschemas/TrackPointExtension/v1
            http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd"
            xmlns="http://www.topografix.com/GPX/1/1"
            xmlns:gpxtpx="http://www.garmin.com/xmlschemas/TrackPointExtension/v1"
            xmlns:gpxx="http://www.garmin.com/xmlschemas/GpxExtensions/v3"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <metadata>
            <link>https://www.my-url.at</link></metadata>
            <trk>
            <trkseg>
            <trkpt lat="47.80743" lon="12.378228"><time>2021-09-07T13:37:42Z</time> </trkpt>
            <trkpt lat="47.807343" lon="12.378138"><time>2021-09-07T13:38:18Z</time> </trkpt>
            <trkpt lat="47.807335" lon="12.378"><time>2021-09-07T13:38:25Z</time> </trkpt>
            <trkpt lat="47.807377" lon="12.377702"><time>2021-09-07T13:38:31Z</time> </trkpt>
            <trkpt lat="47.807346" lon="12.37751"><time>2021-09-07T13:38:34Z</time> </trkpt>
            <trkpt lat="47.807312" lon="12.377382"><time>2021-09-07T13:38:36Z</time> </trkpt>
            </trkseg>
            <trkseg>
            <trkpt lat="47.806938" lon="12.378183"><time>2021-09-07T16:10:27Z</time> </trkpt>
            <trkpt lat="47.806793" lon="12.378217"><time>2021-09-07T16:11:20Z</time> </trkpt>
            <trkpt lat="47.806873" lon="12.378148"><time>2021-09-07T16:13:51Z</time> </trkpt>
            <trkpt lat="47.806969" lon="12.37816"><time>2021-09-07T16:13:55Z</time> </trkpt>
            <trkpt lat="47.80706" lon="12.378138"><time>2021-09-07T16:14:00Z</time> </trkpt>
            <trkpt lat="47.807156" lon="12.378108"><time>2021-09-07T16:14:06Z</time> </trkpt>
            <trkpt lat="47.807251" lon="12.378079"><time>2021-09-07T16:14:11Z</time> </trkpt>
            <trkpt lat="47.807346" lon="12.38"><time>2021-09-07T16:14:16Z</time> </trkpt>
            </trkseg>
            </trk>
            </gpx>""";

    public static final String GPX_WITHOUT_TIME = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <gpx version="1.1" creator="me"
            xsi:schemaLocation="http://www.topografix.com/GPX/1/1
            http://www.topografix.com/GPX/1/1/gpx.xsd
            http://www.garmin.com/xmlschemas/GpxExtensions/v3
            http://www.garmin.com/xmlschemas/GpxExtensionsv3.xsd
            http://www.garmin.com/xmlschemas/TrackPointExtension/v1
            http://www.garmin.com/xmlschemas/TrackPointExtensionv1.xsd"
            xmlns="http://www.topografix.com/GPX/1/1"
            xmlns:gpxtpx="http://www.garmin.com/xmlschemas/TrackPointExtension/v1"
            xmlns:gpxx="http://www.garmin.com/xmlschemas/GpxExtensions/v3"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <metadata>
            <link>https://www.my-url.at</link></metadata>
            <trk>
            <trkseg>
            <trkpt lat="47.80743" lon="12.378228"><ele>587</ele> </trkpt>
            <trkpt lat="47.807343" lon="12.378138"><ele>588</ele> </trkpt>
            <trkpt lat="47.807335" lon="12.378"><ele>588</ele> </trkpt>
            <trkpt lat="47.807377" lon="12.377702"><ele>588</ele> </trkpt>
            <trkpt lat="47.807346" lon="12.37751"><ele>588</ele> </trkpt>
            <trkpt lat="47.807312" lon="12.377382"><ele>586</ele> </trkpt>
            </trkseg>
            <trkseg>
            <trkpt lat="47.806938" lon="12.378183"><ele>598</ele> </trkpt>
            <trkpt lat="47.806793" lon="12.378217"><ele>598</ele> </trkpt>
            <trkpt lat="47.806873" lon="12.378148"><ele>598</ele> </trkpt>
            <trkpt lat="47.806969" lon="12.37816"><ele>598</ele> </trkpt>
            <trkpt lat="47.80706" lon="12.378138"><ele>598</ele> </trkpt>
            <trkpt lat="47.807156" lon="12.378108"><ele>598</ele> </trkpt>
            <trkpt lat="47.807251" lon="12.378079"><ele>598</ele> </trkpt>
            <trkpt lat="47.807346" lon="12.38"><ele>596</ele> </trkpt>
            </trkseg>
            </trk>
            </gpx>""";

    private GpxFixture() {
    }
}
