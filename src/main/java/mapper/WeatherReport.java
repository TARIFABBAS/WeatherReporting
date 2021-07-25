package mapper;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "using",
        "temperature",
        "wind",
        "humidity",
        "presssure",
        "clouds",
        "visibility"
})
@Generated("jsonschema2pojo")
public class WeatherReport {

    @JsonProperty("using")
    private String using;
    @JsonProperty("temperature")
    private Temperature temperature;
    @JsonProperty("wind")
    private Wind wind;
    @JsonProperty("humidity")
    private Double humidity;
    @JsonProperty("presssure")
    private Double presssure;
    @JsonProperty("clouds")
    private Double clouds;
    @JsonProperty("visibility")
    private Double visibility;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public WeatherReport() {
    }

    /**
     * @param visibility
     * @param temperature
     * @param humidity
     * @param presssure
     * @param clouds
     * @param wind
     */
    public WeatherReport(String using, Temperature temperature, Wind wind, Double humidity, Double presssure, Double clouds, Double visibility) {
        super();
        this.using = using;
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.presssure = presssure;
        this.clouds = clouds;
        this.visibility = visibility;
    }

    @JsonProperty("using")
    public String getUsing() {
        return using;
    }

    @JsonProperty("using")
    public void setUsing(String using) {
        this.using = using;
    }

    @JsonProperty("temperature")
    public Temperature getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("wind")
    public Wind getWind() {
        return wind;
    }

    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    @JsonProperty("humidity")
    public Double getHumidity() {
        return humidity;
    }

    @JsonProperty("humidity")
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("presssure")
    public Double getPresssure() {
        return presssure;
    }

    @JsonProperty("presssure")
    public void setPresssure(Double presssure) {
        this.presssure = presssure;
    }

    @JsonProperty("clouds")
    public Double getClouds() {
        return clouds;
    }

    @JsonProperty("clouds")
    public void setClouds(Double clouds) {
        this.clouds = clouds;
    }

    @JsonProperty("visibility")
    public Double getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}