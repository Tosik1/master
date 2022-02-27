package main.api.response;

public class RestoreResponse {
    private boolean result;

    public RestoreResponse(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
