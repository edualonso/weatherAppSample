package com.barbasdev.weatherappsample.core.presentation.location.delegate;

import com.barbasdev.weatherappsample.core.network.apixu.dto.JApixuLocation;
import com.barbasdev.weatherappsample.core.presentation.location.JILocation;

/**
 * Created by edu on 24/02/2018.
 */

public class JApixuLocationDelegate implements JILocation {

    private JApixuLocation location;

    public JApixuLocationDelegate(JApixuLocation location) {
        this.location = location;
    }

    @Override
    public Long getId() {
        return location.getId();
    }

    @Override
    public void setId(long id) {
        location.setId(id);
    }

    @Override
    public String getName() {
        return location.getName();
    }

    @Override
    public void setName(String name) {
        location.setName(name);
    }

    @Override
    public String getCountry() {
        return location.getCountry();
    }

    @Override
    public void setCountry(String country) {
        location.setCountry(country);
    }

    @Override
    public Float getLat() {
        return location.getLat();
    }

    @Override
    public void setLat(float lat) {
        location.setLat(lat);
    }

    @Override
    public Float getLon() {
        return location.getLon();
    }

    @Override
    public void setLon(float lon) {
        location.setLon(lon);
    }
}
