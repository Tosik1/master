package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {

    private int id;

    private Boolean result = true;

    private CommentErrorsResponse errors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean isResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public CommentErrorsResponse getErrors() {
        return errors;
    }

    public void setErrors(CommentErrorsResponse errors) {
        this.errors = errors;
    }
}
