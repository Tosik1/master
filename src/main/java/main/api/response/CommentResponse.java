package main.api.response;

public class CommentResponse {

    private int id;

    private boolean result;

    private CommentErrorsResponse errors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public CommentErrorsResponse getErrors() {
        return errors;
    }

    public void setErrors(CommentErrorsResponse errors) {
        this.errors = errors;
    }
}
