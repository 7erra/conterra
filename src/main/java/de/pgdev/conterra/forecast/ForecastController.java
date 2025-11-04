package de.pgdev.conterra.forecast;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Random;

@RestController
public class ForecastController {
    Random rand = new Random();

    private double round(double number) {
        return (double)Math.round(number * 100) / 100;
    }

    @GetMapping("/forecast")
    public Forecast forecast(@RequestParam String lat, @RequestParam String lon) {
        double temp = round(rand.nextDouble(60) - 20);
        double feels_like = round(rand.nextDouble(60) - 20);
        int humidity = rand.nextInt(100);
        return new Forecast(Instant.now().getEpochSecond(), temp, feels_like, humidity);
    }
}
