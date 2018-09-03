package com.barbasdev.weatherappsample.core.persistence

import junit.framework.Assert

object WeatherResultsTestHelper {

    internal fun assertWeatherResultsApixu(repository: Repository) {
        val weather = repository
                .getWeather("London")
                .test()
                .await()
                .values()[0]

        Assert.assertEquals("London", weather.location.name)
        Assert.assertEquals("United Kingdom", weather.location.country)
        Assert.assertEquals(51.52F, weather.location.lat)
        Assert.assertEquals(-0.11F, weather.location.lon)
        Assert.assertEquals(2F, weather.temperature)
    }

    internal fun assertWeatherResultsOpenWeather(repository: Repository) {
        val weather = repository
                .getWeather("London")
                .test()
                .await()
                .values()[0]

        Assert.assertEquals("London", weather.location.name)
        Assert.assertEquals("GB", weather.location.country)
        Assert.assertEquals(51.51F, weather.location.lat)
        Assert.assertEquals(-0.13F, weather.location.lon)
        Assert.assertEquals(280.32F, weather.temperature)
    }
}