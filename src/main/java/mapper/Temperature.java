package mapper;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "actual",
        "feels_like"
})
@Generated("jsonschema2pojo")
public class Temperature {

    @JsonProperty("actual")
    private Double actual;
    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Temperature() {
    }

    /**
     * @param feelsLike
     * @param actual
     */
    public Temperature(Double actual, Double feelsLike) {
        super();
        this.actual = actual;
        this.feelsLike = feelsLike;
    }

    @JsonProperty("actual")
    public Double getActual() {
        return actual;
    }

    @JsonProperty("actual")
    public void setActual(Double actual) {
        this.actual = actual;
    }

    @JsonProperty("feels_like")
    public Double getFeelsLike() {
        return feelsLike;
    }

    @JsonProperty("feels_like")
    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
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