package at.bernhardangerer.gpxStatsHelper.model.api;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public final class GeocodeReverseModel {
    @SerializedName("place_id")
    private int placeId;
    private String licence;
    @SerializedName("osm_type")
    private String osmType;
    @SerializedName("osm_id")
    private long osmId;
    private String lat;
    private String lon;
    @SerializedName("display_name")
    private String displayName;
    private Address address;
    @SerializedName("boundingbox")
    private double[] boundingBox;
    private String svg;
    @SerializedName("extratags")
    private ExtraTags extraTags;
    @SerializedName("namedetails")
    private NameDetails nameDetails;

    @Data
    public static final class ExtraTags {
        private String ele;
        private String wikidata;
        private String wikipedia;
        private String population;
        @SerializedName("ref_at_gkz")
        private String refAtGkz;
        @SerializedName("linked_place")
        private String linkedPlace;
        @SerializedName("opengeodb_lat")
        private String openGeoDbLat;
        @SerializedName("opengeodb_lon")
        private String openGeoDbLon;
        @SerializedName("name_prefix_at")
        private String namePrefixAt;
        @SerializedName("openGeoDB_name")
        private String openGeoDbName;
        @SerializedName("openGeoDB_type")
        private String openGeoDbType;
        @SerializedName("openGeoDB_is_in")
        private String openGeoDbIsIn;
        @SerializedName("openGeoDB_layer")
        private String openGeoDbLayer;
        @SerializedName("openGeoDB_loc_id")
        private String openGeoDbLocId;
        @SerializedName("openGeoDB_version")
        private String openGeoDbVersion;
        @SerializedName("openGeoDB_sort_name")
        private String openGeoDbSortName;
        @SerializedName("openGeoDB_population")
        private String openGeoDbPopulation;
        @SerializedName("openGeoDB_auto_update")
        private String openGeoDbAutoUpdate;
        @SerializedName("openGeoDB_is_in_loc_id")
        private String openGeoDbIsInLocId;
        @SerializedName("openGeoDB_postal_codes")
        private String openGeoDbPostalCodes;
        @SerializedName("openGeoDB_community_identification_number")
        private String openGeoDbCommunityIdentificationNumber;
    }

    @Data
    public static final class NameDetails {
        private String name;
        @SerializedName("name_de")
        private String nameDe;
        @SerializedName("_place_name")
        private String placeNname;
        @SerializedName("official_name")
        private String officialName;
    }

    @Data
    public static final class Address {
        private String historic;
        private String building;
        private String highway;
        @SerializedName("house_number")
        private String houseNumber;
        private String road;
        private String quarter;
        private String hamlet;
        private String farm;
        private String municipality;
        private String village;
        private String suburb;
        private String locality;
        private String town;
        private String city;
        private String county;
        @SerializedName("ISO3166-2-lvl6")
        private String unitCode16;
        @SerializedName("state_district")
        private String stateDistrict;
        private String state;
        @SerializedName("ISO3166-2-lvl4")
        private String unitCode14;
        private String postcode;
        private String country;
        @SerializedName("country_code")
        private String countryCode;
    }
}

