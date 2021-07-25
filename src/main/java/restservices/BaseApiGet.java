package restservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.net.URI;

import static io.restassured.RestAssured.given;

public class BaseApiGet {

    protected String contentType;
    protected int expectedStatusCode;
    protected String accessToken;

    protected URI baseURI;
    protected String apiPath;

    protected RequestSpecBuilder requestSpecBuilder;
    protected RequestSpecification REQUEST;
    protected ResponseSpecBuilder responseSpecBuilder;
    protected ResponseSpecification RESPONSE;
    protected Response apiResponse;

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setExpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    public Response getResponse() {
        return apiResponse;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void addHeader(String headerName, String headerValue) {
        requestSpecBuilder.addHeader(headerName, headerValue);
    }

    public void setQueryParam(String paramName, Object value) {
        requestSpecBuilder.addQueryParam(paramName, value);
    }

    public void createRequest() {
        requestSpecBuilder
                .setBaseUri(baseURI)
                .setBasePath(apiPath)
                .setContentType(contentType);
        REQUEST = requestSpecBuilder.build();
    }

    public void executeRequest() throws JsonProcessingException {
        apiResponse = given()
                .spec(REQUEST)
                .get();
    }

    public void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        RESPONSE = responseSpecBuilder.build();
        apiResponse.then().spec(RESPONSE);
    }

    private void displaySeparator() {
        System.out.println("===================================================");
    }

    public void perform() throws JsonProcessingException {
        createRequest();
        executeRequest();
//        validateResponse();
        displaySeparator();
    }

    public void setCookies(Cookies cookies) {
        requestSpecBuilder.addCookies(cookies);
    }
}