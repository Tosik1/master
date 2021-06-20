package main.core.api.response;

import org.springframework.stereotype.Component;

@Component
public class AuthCheckResponse {

    public static final AuthCheckResponse NO_AUTH = new AuthCheckResponse();

    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
