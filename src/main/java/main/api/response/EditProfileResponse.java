package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class EditProfileResponse {

    private boolean result = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorsProfileResponse errors;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ErrorsProfileResponse getErrors() {
        return errors;
    }

    public void setErrors(ErrorsProfileResponse errors) {
        this.errors = errors;
    }
}
