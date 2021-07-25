package org.accuweather.com;

import mapper.Temperature;
import mapper.WeatherReport;
import mapper.Wind;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class WeatherDetailPage {
    private WebDriver driver;

    @FindBy(css = ".header-loc")
    private WebElement header;

    @FindBy(css = ".cur-con-weather-card__cta .text")
    private WebElement moreDetails;

    @FindBy(css = ".display-temp")
    private WebElement temp;

    @FindBy(css = ".current-weather-extra div")
    private WebElement feelTemp;

    public WeatherDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifySearchedLocation(String location) {
        Assert.assertTrue(header.getText().toLowerCase().contains(location.toLowerCase()), "Search Location Exists");
    }

    public void getMoreDetails() {
        moreDetails.click();
    }

    public WeatherReport fetchWeatherDetail() {
        String temperature = temp.getText().split("°C")[0];
        String feelsLike = feelTemp.getText().split(" ")[1].split("°")[0];
        String windSpeed = getWeatherPropertyValue("Wind").split(" ")[1];
        String windGust = getWeatherPropertyValue("Wind Gusts").split(" ")[0];
        String humidity = getWeatherPropertyValue("Humidity").split("%")[0];
        String pressure = getWeatherPropertyValue("Pressure").split(" ")[1];
        String cloudCover = getWeatherPropertyValue("Cloud Cover").split("%")[0];
        String visibility = getWeatherPropertyValue("Visibility").split(" ")[0];

        System.out.println(temperature);
        System.out.println(feelsLike);
        System.out.println(windSpeed);
        System.out.println(windGust);
        System.out.println(humidity);
        System.out.println(pressure);
        System.out.println(cloudCover);
        System.out.println(visibility);

        Wind wind = new Wind(Double.valueOf(windSpeed),Double.valueOf(windGust));
        Temperature temp = new Temperature(Double.valueOf(temperature),Double.valueOf(feelsLike));
        WeatherReport report = new WeatherReport("UI",temp,wind,Double.valueOf(humidity),Double.valueOf(pressure),Double.valueOf(cloudCover),Double.valueOf(visibility));
        return report;
    }

    private String getWeatherPropertyValue(String propertyName) {
        By by = By.xpath("//div[@class='current-weather-details']//*[text()='" + propertyName + "']/following-sibling::div");
        return driver.findElement(by).getText();
    }
}
