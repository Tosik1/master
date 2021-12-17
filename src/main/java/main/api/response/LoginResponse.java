package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import main.model.custom.CustomUserForLogin;

@Data
public class LoginResponse {

    private boolean result = false;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user")
    private CustomUserForLogin user;
}
