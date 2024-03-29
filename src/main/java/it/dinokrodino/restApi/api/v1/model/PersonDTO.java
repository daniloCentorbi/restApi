package it.dinokrodino.restApi.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PersonDTO {
    private Long id;

    @ApiModelProperty(value = "first name", required = true)
    private String firstName;

    @ApiModelProperty(required = true)
    private String lastName;

    //@JsonProperty("customer_url")
    private String customerUrl;
}
