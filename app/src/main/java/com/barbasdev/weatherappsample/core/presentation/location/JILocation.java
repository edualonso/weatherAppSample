package com.barbasdev.weatherappsample.core.presentation.location;

/**
 * Created by edu on 24/02/2018.
 */

public interface JILocation {
    Long getId();
    void setId(long id);

    String getName();
    void setName(String name);

    String getCountry();
    void setCountry(String country);

    Float getLat();
    void setLat(float lat);

    Float getLon();
    void setLon(float lon);
}
