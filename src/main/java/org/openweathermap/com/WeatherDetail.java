package org.openweathermap.com;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import restservices.BaseApiGet;
import utility.SessionContext;

import java.net.URI;

public class WeatherDetail extends BaseApiGet {

    public WeatherDetail(URI baseURI, String apiPath) {
        this.baseURI = baseURI;
        this.apiPath = apiPath;
        requestSpecBuilder = new RequestSpecBuilder();
        responseSpecBuilder = new ResponseSpecBuilder();
    }



}