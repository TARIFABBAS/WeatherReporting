package mapper;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "speed",
        "gust"
})
@Generated("jsonschema2pojo")
public class Wind {

    @JsonProperty("speed")
    private Double speed;
    @JsonProperty("gust")
    private Double gust;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     */
    public Wind() {
    }

    /**
     * @param speed
     * @param gust
     */
    public Wind(Double speed, Double gust) {
        super();
        this.speed = speed;
        this.gust = gust;
    }

    @JsonProperty("speed")
    public Double getSpeed() {
        return speed;
    }

    @JsonProperty("speed")
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @JsonProperty("gust")
    public Double getGust() {
        return gust;
    }

    @JsonProperty("gust")
    public void setGust(Double gust) {
        this.gust = gust;
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