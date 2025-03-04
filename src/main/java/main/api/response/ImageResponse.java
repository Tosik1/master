package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ImageResponse {

    private boolean result = false;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ImageErrorsResponse errors;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ImageErrorsResponse getErrors() {
        return errors;
    }

    public void setErrors(ImageErrorsResponse errors) {
        this.errors = errors;
    }
}
