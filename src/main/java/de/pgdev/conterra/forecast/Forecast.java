package de.pgdev.conterra.forecast;

public record Forecast(long time, double temp, double feels_like, int humidity) {
}
