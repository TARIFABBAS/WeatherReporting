package org.comparator.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import mapper.WeatherReport;
import org.accuweather.com.HomePage;
import org.accuweather.com.WeatherDetailPage;
import org.comparator.utility.Utils;
import org.openqa.selenium.WebDriver;
import utility.Keywords;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Common {
    private WebDriver driver;
    private WeatherDetailPage detailPage;
    private WeatherReport uiReport;
    private WeatherReport apiReport;

    private Utils utils = new Utils();

    @Given("^user open (.+) browser$")
    public void user_open_browser(String driverType) {
        driver = new Keywords().getDriver(driverType);
    }

    @Given("^user navigate to \"([^\"]*)\"$")
    public void user_navigate_to(String url) {
        driver.navigate().to(url);
    }

    @When("^user search for location (.+)$")
    public void user_search_for_location(String location) {
        HomePage homePage = new HomePage(driver);
        homePage.searchLocation(location);
        homePage.submitForm();
    }

    @Then("^user verify search result location (.+)$")
    public void user_verify_search_result_location(String location) {
        detailPage = new WeatherDetailPage(driver);
        detailPage.verifySearchedLocation(location);
        detailPage.getMoreDetails();
    }

    @Given("^user fetch all the weather details$")
    public void user_fetch_all_the_weather_details() {
        uiReport = detailPage.fetchWeatherDetail();
    }

    @Given("^user fetch weather details from \"([^\"]*)\" for location (.+)$")
    public void user_fetch_weather_details_from_for_location(String url, String location) throws MalformedURLException, URISyntaxException, JsonProcessingException {
        Response response = utils.getWeatherDetail(url, location);
        apiReport = utils.fetchWeatherDetailFromAPI(response);
    }

    @Then("^user compares weather info from UI and API on basis of temperature with threshold value (.+) (.+)$")
    public void user_compares_weather_info_from_ui_and_api_on_basis_of_temperature_with_threshold_value(Double tempThreshold, Double humidityThreshold) {
        utils.compareReport(uiReport,apiReport,"Temperature",tempThreshold);

        utils.compareReport(uiReport,apiReport,"Humidity",humidityThreshold);
    }

}
