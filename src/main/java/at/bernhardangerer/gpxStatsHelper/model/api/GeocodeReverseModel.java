package at.bernhardangerer.gpxStatsHelper.model.api;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

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
    private double[] boundingbox;
    private String svg;
    @SerializedName("extratags")
    private ExtraTags extraTags;
    @SerializedName("namedetails")
    private NameDetails nameDetails;

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(final int placeId) {
        this.placeId = placeId;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(final String licence) {
        this.licence = licence;
    }

    public String getOsmType() {
        return osmType;
    }

    public void setOsmType(final String osmType) {
        this.osmType = osmType;
    }

    public long getOsmId() {
        return osmId;
    }

    public void setOsmId(final long osmId) {
        this.osmId = osmId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(final String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(final String lon) {
        this.lon = lon;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public double[] getBoundingbox() {
        return boundingbox;
    }

    public void setBoundingbox(final double[] boundingbox) {
        this.boundingbox = boundingbox;
    }

    public String getSvg() {
        return svg;
    }

    public void setSvg(final String svg) {
        this.svg = svg;
    }

    public ExtraTags getExtraTags() {
        return extraTags;
    }

    public void setExtraTags(final ExtraTags extraTags) {
        this.extraTags = extraTags;
    }

    public NameDetails getNameDetails() {
        return nameDetails;
    }

    public void setNameDetails(final NameDetails nameDetails) {
        this.nameDetails = nameDetails;
    }

    @Override
    public String toString() {
        return "GeocodeReverseModel {"
            + "placeId=" + placeId
            + ", licence='" + licence + '\''
            + ", osmType='" + osmType + '\''
            + ", osmId=" + osmId
            + ", lat='" + lat + '\''
            + ", lon='" + lon + '\''
            + ", displayName='" + displayName + '\''
            + ", address=" + address
            + ", boundingbox=" + Arrays.toString(boundingbox)
            + ", svg='" + svg + '\''
            + ", extraTags=" + extraTags
            + ", nameDetails=" + nameDetails
            + '}';
    }

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

        public String getEle() {
            return ele;
        }

        public void setEle(final String ele) {
            this.ele = ele;
        }

        public String getWikidata() {
            return wikidata;
        }

        public void setWikidata(final String wikidata) {
            this.wikidata = wikidata;
        }

        public String getWikipedia() {
            return wikipedia;
        }

        public void setWikipedia(final String wikipedia) {
            this.wikipedia = wikipedia;
        }

        public String getPopulation() {
            return population;
        }

        public void setPopulation(final String population) {
            this.population = population;
        }

        public String getRefAtGkz() {
            return refAtGkz;
        }

        public void setRefAtGkz(final String refAtGkz) {
            this.refAtGkz = refAtGkz;
        }

        public String getLinkedPlace() {
            return linkedPlace;
        }

        public void setLinkedPlace(final String linkedPlace) {
            this.linkedPlace = linkedPlace;
        }

        public String getOpenGeoDbLat() {
            return openGeoDbLat;
        }

        public void setOpenGeoDbLat(final String openGeoDbLat) {
            this.openGeoDbLat = openGeoDbLat;
        }

        public String getOpenGeoDbLon() {
            return openGeoDbLon;
        }

        public void setOpenGeoDbLon(final String openGeoDbLon) {
            this.openGeoDbLon = openGeoDbLon;
        }

        public String getNamePrefixAt() {
            return namePrefixAt;
        }

        public void setNamePrefixAt(final String namePrefixAt) {
            this.namePrefixAt = namePrefixAt;
        }

        public String getOpenGeoDbName() {
            return openGeoDbName;
        }

        public void setOpenGeoDbName(final String openGeoDbName) {
            this.openGeoDbName = openGeoDbName;
        }

        public String getOpenGeoDbType() {
            return openGeoDbType;
        }

        public void setOpenGeoDbType(final String openGeoDbType) {
            this.openGeoDbType = openGeoDbType;
        }

        public String getOpenGeoDbIsIn() {
            return openGeoDbIsIn;
        }

        public void setOpenGeoDbIsIn(final String openGeoDbIsIn) {
            this.openGeoDbIsIn = openGeoDbIsIn;
        }

        public String getOpenGeoDbLayer() {
            return openGeoDbLayer;
        }

        public void setOpenGeoDbLayer(final String openGeoDbLayer) {
            this.openGeoDbLayer = openGeoDbLayer;
        }

        public String getOpenGeoDbLocId() {
            return openGeoDbLocId;
        }

        public void setOpenGeoDbLocId(final String openGeoDbLocId) {
            this.openGeoDbLocId = openGeoDbLocId;
        }

        public String getOpenGeoDbVersion() {
            return openGeoDbVersion;
        }

        public void setOpenGeoDbVersion(final String openGeoDbVersion) {
            this.openGeoDbVersion = openGeoDbVersion;
        }

        public String getOpenGeoDbSortName() {
            return openGeoDbSortName;
        }

        public void setOpenGeoDbSortName(final String openGeoDbSortName) {
            this.openGeoDbSortName = openGeoDbSortName;
        }

        public String getOpenGeoDbPopulation() {
            return openGeoDbPopulation;
        }

        public void setOpenGeoDbPopulation(final String openGeoDbPopulation) {
            this.openGeoDbPopulation = openGeoDbPopulation;
        }

        public String getOpenGeoDbAutoUpdate() {
            return openGeoDbAutoUpdate;
        }

        public void setOpenGeoDbAutoUpdate(final String openGeoDbAutoUpdate) {
            this.openGeoDbAutoUpdate = openGeoDbAutoUpdate;
        }

        public String getOpenGeoDbIsInLocId() {
            return openGeoDbIsInLocId;
        }

        public void setOpenGeoDbIsInLocId(final String openGeoDbIsInLocId) {
            this.openGeoDbIsInLocId = openGeoDbIsInLocId;
        }

        public String getOpenGeoDbPostalCodes() {
            return openGeoDbPostalCodes;
        }

        public void setOpenGeoDbPostalCodes(final String openGeoDbPostalCodes) {
            this.openGeoDbPostalCodes = openGeoDbPostalCodes;
        }

        public String getOpenGeoDbCommunityIdentificationNumber() {
            return openGeoDbCommunityIdentificationNumber;
        }

        public void setOpenGeoDbCommunityIdentificationNumber(final String openGeoDbCommunityIdentificationNumber) {
            this.openGeoDbCommunityIdentificationNumber = openGeoDbCommunityIdentificationNumber;
        }

        @Override
        public String toString() {
            return "ExtraTags {"
                + "ele='" + ele + '\''
                + ", wikidata='" + wikidata + '\''
                + ", wikipedia='" + wikipedia + '\''
                + ", population='" + population + '\''
                + ", refAtGkz='" + refAtGkz + '\''
                + ", linkedPlace='" + linkedPlace + '\''
                + ", openGeoDbLat='" + openGeoDbLat + '\''
                + ", openGeoDbLon='" + openGeoDbLon + '\''
                + ", namePrefixAt='" + namePrefixAt + '\''
                + ", openGeoDbName='" + openGeoDbName + '\''
                + ", openGeoDbType='" + openGeoDbType + '\''
                + ", openGeoDbIsIn='" + openGeoDbIsIn + '\''
                + ", openGeoDbLayer='" + openGeoDbLayer + '\''
                + ", openGeoDbLocId='" + openGeoDbLocId + '\''
                + ", openGeoDbVersion='" + openGeoDbVersion + '\''
                + ", openGeoDbSortName='" + openGeoDbSortName + '\''
                + ", openGeoDbPopulation='" + openGeoDbPopulation + '\''
                + ", openGeoDbAutoUpdate='" + openGeoDbAutoUpdate + '\''
                + ", openGeoDbIsInLocId='" + openGeoDbIsInLocId + '\''
                + ", openGeoDbPostalCodes='" + openGeoDbPostalCodes + '\''
                + ", openGeoDbCommunityIdentificationNumber='" + openGeoDbCommunityIdentificationNumber + '\''
                + '}';
        }
    }

    public static final class NameDetails {
        private String name;
        @SerializedName("name_de")
        private String nameDe;
        @SerializedName("_place_name")
        private String placeNname;
        @SerializedName("official_name")
        private String officialName;

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getNameDe() {
            return nameDe;
        }

        public void setNameDe(final String nameDe) {
            this.nameDe = nameDe;
        }

        public String getPlaceNname() {
            return placeNname;
        }

        public void setPlaceNname(final String placeNname) {
            this.placeNname = placeNname;
        }

        public String getOfficialName() {
            return officialName;
        }

        public void setOfficialName(final String officialName) {
            this.officialName = officialName;
        }

        @Override
        public String toString() {
            return "NameDetails {"
                + "name='" + name + '\''
                + ", nameDe='" + nameDe + '\''
                + ", placeNname='" + placeNname + '\''
                + ", officialName='" + officialName + '\''
                + '}';
        }
    }

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

        public String getHistoric() {
            return historic;
        }

        public void setHistoric(final String historic) {
            this.historic = historic;
        }

        public String getBuilding() {
            return building;
        }

        public void setBuilding(final String building) {
            this.building = building;
        }

        public String getHighway() {
            return highway;
        }

        public void setHighway(final String highway) {
            this.highway = highway;
        }

        public String getHouseNumber() {
            return houseNumber;
        }

        public void setHouseNumber(final String houseNumber) {
            this.houseNumber = houseNumber;
        }

        public String getRoad() {
            return road;
        }

        public void setRoad(final String road) {
            this.road = road;
        }

        public String getQuarter() {
            return quarter;
        }

        public void setQuarter(final String quarter) {
            this.quarter = quarter;
        }

        public String getHamlet() {
            return hamlet;
        }

        public void setHamlet(final String hamlet) {
            this.hamlet = hamlet;
        }

        public String getFarm() {
            return farm;
        }

        public void setFarm(final String farm) {
            this.farm = farm;
        }

        public String getMunicipality() {
            return municipality;
        }

        public void setMunicipality(final String municipality) {
            this.municipality = municipality;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(final String village) {
            this.village = village;
        }

        public String getSuburb() {
            return suburb;
        }

        public void setSuburb(final String suburb) {
            this.suburb = suburb;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(final String locality) {
            this.locality = locality;
        }

        public String getTown() {
            return town;
        }

        public void setTown(final String town) {
            this.town = town;
        }

        public String getCity() {
            return city;
        }

        public void setCity(final String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(final String county) {
            this.county = county;
        }

        public String getUnitCode16() {
            return unitCode16;
        }

        public void setUnitCode16(final String unitCode16) {
            this.unitCode16 = unitCode16;
        }

        public String getStateDistrict() {
            return stateDistrict;
        }

        public void setStateDistrict(final String stateDistrict) {
            this.stateDistrict = stateDistrict;
        }

        public String getState() {
            return state;
        }

        public void setState(final String state) {
            this.state = state;
        }

        public String getUnitCode14() {
            return unitCode14;
        }

        public void setUnitCode14(final String unitCode14) {
            this.unitCode14 = unitCode14;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(final String postcode) {
            this.postcode = postcode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(final String country) {
            this.country = country;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(final String countryCode) {
            this.countryCode = countryCode;
        }

        @Override
        public String toString() {
            return "Address {"
                + "historic='" + historic + '\''
                + ", building='" + building + '\''
                + ", highway='" + highway + '\''
                + ", houseNumber='" + houseNumber + '\''
                + ", road='" + road + '\''
                + ", quarter='" + quarter + '\''
                + ", hamlet='" + hamlet + '\''
                + ", farm='" + farm + '\''
                + ", municipality='" + municipality + '\''
                + ", village='" + village + '\''
                + ", suburb='" + suburb + '\''
                + ", locality='" + locality + '\''
                + ", town='" + town + '\''
                + ", city='" + city + '\''
                + ", county='" + county + '\''
                + ", unitCode16='" + unitCode16 + '\''
                + ", stateDistrict='" + stateDistrict + '\''
                + ", state='" + state + '\''
                + ", unitCode14='" + unitCode14 + '\''
                + ", postcode='" + postcode + '\''
                + ", country='" + country + '\''
                + ", countryCode='" + countryCode + '\''
                + '}';
        }
    }
}

