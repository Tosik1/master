package main.controller;

import main.api.response.AuthCheckResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private final AuthCheckResponse authCheckResponse;

    public ApiAuthController(AuthCheckResponse authCheckResponse) {
        this.authCheckResponse = authCheckResponse;
    }

    @GetMapping("/check")
    private boolean check(){
        return authCheckResponse.isResult();
    }
}
