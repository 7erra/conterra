package de.pgdev.conterra.forecast;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;

@RestController
public class ForecastController {
    @GetMapping("/forecast")
    public Forecast forecast(@RequestParam String lat, @RequestParam String lon) {
        return new Forecast(Instant.now().getEpochSecond(), 13.76, 20, 69);
    }
}
