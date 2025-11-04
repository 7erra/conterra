package de.pgdev.conterra.forecast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
record OpenWeatherForecastEntryMain(double temp, double feels_like, int humidity) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
record OpenWeatherForecastEntry(long dt, OpenWeatherForecastEntryMain main) {
}

@JsonIgnoreProperties(ignoreUnknown = true)
public record OpenWeatherForecastResponse(List<OpenWeatherForecastEntry> list) {
}
