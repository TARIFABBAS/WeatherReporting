package org.comparator.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import mapper.Temperature;
import mapper.WeatherReport;
import mapper.Wind;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.openweathermap.com.WeatherDetail;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utility.SessionContext;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utils {

    SoftAssert softAssert = new SoftAssert();

    public static void main(String[] args) {
//        String str = "{\"coord\":{\"lon\":77.6033,\"lat\":12.9762},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":20.9,\"feels_like\":21.4,\"temp_min\":20.9,\"temp_max\":20.9,\"pressure\":1009,\"humidity\":90,\"sea_level\":1009,\"grnd_level\":907},\"visibility\":10000,\"wind\":{\"speed\":6.22,\"deg\":258,\"gust\":11.72},\"clouds\":{\"all\":56},\"dt\":1627158304,\"sys\":{\"type\":1,\"id\":9208,\"country\":\"IN\",\"sunrise\":1627173206,\"sunset\":1627219108},\"timezone\":19800,\"id\":1277333,\"name\":\"Bengaluru\",\"cod\":200}";
//        new Utils().fetchWeatherDetailFromAPI(str);
    }

    public Response getWeatherDetail(String baseURI, String location) throws JsonProcessingException, MalformedURLException, URISyntaxException {
        WeatherDetail wd = new WeatherDetail(new URL(baseURI).toURI(), SessionContext.get().openweather_apiPath);
        wd.setQueryParam("q", location);
        wd.setQueryParam("appid", SessionContext.get().openweather_apiKey);
        wd.setQueryParam("units", "metric");
        wd.setContentType("application/json");
        wd.setExpectedStatusCode(200);
        wd.perform();
        Response response = wd.getResponse();
        System.out.println(response.asString());
        return response;
    }

    public WeatherReport fetchWeatherDetailFromAPI(Response response) {
        JsonPath jsonPath = response.jsonPath();
//        JsonPath jsonPath = new JsonPath(str);
        double temperature = jsonPath.getDouble("main.temp");
        double feelsLike = jsonPath.getDouble("main.feels_like");
        double windSpeed = jsonPath.getDouble("wind.speed") * 3.6; // converting it into m/sec-->km/hr
        Object object = jsonPath.get("wind.gust");
        double windGust = (object == null) ? 0.0 : jsonPath.getDouble("wind.gust") * 3.6;
        double humidity = jsonPath.getDouble("main.humidity");
        double pressure = jsonPath.getDouble("main.pressure");
        double cloudCover = jsonPath.getDouble("clouds.all");
        double visibility = jsonPath.getDouble("visibility") / 1000; // comverting it into m-->km

        System.out.println(temperature);
        System.out.println(feelsLike);
        System.out.println(windGust);
        System.out.println(windSpeed);
        System.out.println(humidity);
        System.out.println(pressure);
        System.out.println(cloudCover);
        System.out.println(visibility);

        Wind wind = new Wind(windSpeed, windGust);
        Temperature temp = new Temperature(temperature, Double.valueOf(feelsLike));
        WeatherReport report = new WeatherReport("API", temp, wind, humidity, pressure, cloudCover, visibility);
        return report;
    }

    public List<WeatherReport> compareWeather(List<WeatherReport> weatherReports, double tempThreshold, double humidThreshold) {
        Comparator<WeatherReport> comparator = new Comparator<WeatherReport>() {
            @Override
            public int compare(WeatherReport o1, WeatherReport o2) {
                double temp1 = o1.getTemperature().getActual() / tempThreshold;
                double temp2 = o2.getTemperature().getActual() / tempThreshold;

                double humid1 = o1.getHumidity() / humidThreshold;
                double humid2 = o1.getHumidity() / humidThreshold;
                return new CompareToBuilder().append(temp1, temp2).append(humid1, humid2).build();
            }
        };

        Collections.sort(weatherReports, comparator);
        return weatherReports;
    }

    public void compareReport(WeatherReport r1, WeatherReport r2, String property, double threshold) {
        if (Math.abs(getPropertyValue(r1, property) - getPropertyValue(r2, property)) < threshold) {
            Assert.assertTrue(true, property.toUpperCase() + " lies within threshold limit");
            System.out.println(property.toUpperCase() + " lies within threshold limit");
        } else {
            softAssert.assertTrue(false, property.toUpperCase() + " does not lies within threshold limit");
            System.out.println(property.toUpperCase() + " does not lies within threshold limit");
        }
    }

    private double getPropertyValue(WeatherReport r, String property) {
        double result = Double.MIN_VALUE;
        switch (property.toUpperCase()) {
            case "TEMPERATURE":
                result = r.getTemperature().getActual();
                break;
            case "HUMIDITY":
                result = r.getHumidity();
                break;
            default:
                System.out.println("Unexpected value: " + property.toUpperCase());
        }
        return result;
    }

}
