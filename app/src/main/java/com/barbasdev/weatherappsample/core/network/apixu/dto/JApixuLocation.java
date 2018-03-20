package com.barbasdev.weatherappsample.core.network.apixu.dto;

import com.barbasdev.weatherappsample.core.presentation.location.JLocation;
import com.google.gson.annotations.SerializedName;

/**
 * Created by edu on 24/02/2018.
 */

public class JApixuLocation implements JLocation {

    private Long id;
    private String name;
    private String region;
    private String country;
    private Float lat;
    private Float lon;
    private String url;
    private String localtime;
    private @SerializedName("tz_id") String tzId;
    private @SerializedName("localtime_epoch") Long localtimeEpoch;

    public JApixuLocation(
            Long id,
            String name,
            String region,
            String country,
            Float lat,
            Float lon,
            String url,
            String localtime,
            String tzId,
            Long localtimeEpoch
    ) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
        this.url = url;
        this.localtime = localtime;
        this.tzId = tzId;
        this.localtimeEpoch = localtimeEpoch;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Float getLat() {
        return lat;
    }

    @Override
    public void setLat(float lat) {
        this.lat = lat;
    }

    @Override
    public Float getLon() {
        return lon;
    }

    @Override
    public void setLon(float lon) {
        this.lon = lon;
    }
}
