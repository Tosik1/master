package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class NewPostResponse {

    private boolean result = true;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PostErrorsResponse errors;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public PostErrorsResponse getErrors() {
        return errors;
    }

    public void setErrors(PostErrorsResponse errors) {
        this.errors = errors;
    }
}
