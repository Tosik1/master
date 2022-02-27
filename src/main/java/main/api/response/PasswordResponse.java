package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordResponse {

    private boolean result;

    private PasswordErrorsResponse errors;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public PasswordErrorsResponse getErrors() {
        return errors;
    }

    public void setErrors(PasswordErrorsResponse errors) {
        this.errors = errors;
    }
}
