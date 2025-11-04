package de.pgdev.conterra.forecast;

import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ForecastController {
    final String api = "https://api.openweathermap.org/data/2.5/forecast";
    UriComponentsBuilder apiUriBuilder;
    RestTemplate restTemplate = new RestTemplate();

    public ForecastController() {
        String apiKey = System.getenv("OPENWEATHER_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("OPENWEATHER_API_KEY environment variable is not set");
        }
        this.apiUriBuilder = UriComponentsBuilder.fromUriString(api)
                .queryParam("appid", apiKey)
                .queryParam("lat", "{lat}")
                .queryParam("lon", "{lon}");
    }

    @GetMapping(value = "/forecast", params = {"lat", "lon"})
    public Forecast getForecastByCoordinate(@RequestParam double lat, @RequestParam double lon) {
        OpenWeatherForecastResponse openWeatherForecastResponse = restTemplate.getForObject(
                apiUriBuilder.buildAndExpand(lat, lon).toUri(),
                OpenWeatherForecastResponse.class
        );
        if (openWeatherForecastResponse == null || openWeatherForecastResponse.list() == null || openWeatherForecastResponse.list().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "OpenWeather API returned no forecast data");
        }
        OpenWeatherForecastEntry e = openWeatherForecastResponse.list().getFirst();
        return new Forecast(e.dt(), e.main().temp(), e.main().feels_like(), e.main().humidity());
    }

    @GetMapping(value = "/forecast", params = {"country", "city", "street", "housenumber"})
    public Forecast getForecastByAddress(@RequestParam String country, @RequestParam String city, @RequestParam String street, @RequestParam String housenumber) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(System.getenv("OPENCAGE_API_KEY"));
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(String.format("%s, %s, %s, %s", country, city, street, housenumber));
        var response = jOpenCageGeocoder.forward(request);
        var firstResult = response.getFirstPosition();
        return getForecastByCoordinate(firstResult.getLat(), firstResult.getLng());
    }
}
