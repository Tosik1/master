package main.core.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class RegisterResponse {

    private boolean result = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorsResponse errors;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ErrorsResponse getErrors() {
        return errors;
    }

    public void setErrors(ErrorsResponse errors) {
        this.errors = errors;
    }
}
