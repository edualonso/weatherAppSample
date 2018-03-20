package com.barbasdev.weatherappsample.core.presentation.location;

/**
 * Created by edu on 24/02/2018.
 */

public class JLocationImpl implements JLocation {

    private JLocation delegate;

    public JLocationImpl(JLocation delegate) {
        this.delegate = delegate;
    }

    @Override
    public Long getId() {
        return delegate.getId();
    }

    @Override
    public void setId(long id) {
        delegate.setId(id);
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public void setName(String name) {
        delegate.setName(name);
    }

    @Override
    public String getCountry() {
        return delegate.getCountry();
    }

    @Override
    public void setCountry(String country) {
        delegate.setCountry(country);
    }

    @Override
    public Float getLat() {
        return delegate.getLat();
    }

    @Override
    public void setLat(float lat) {
        delegate.setLat(lat);
    }

    @Override
    public Float getLon() {
        return delegate.getLon();
    }

    @Override
    public void setLon(float lon) {
        delegate.setLon(lon);
    }

}
