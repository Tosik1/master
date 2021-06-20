package main.core.controller;

import main.core.api.response.AuthCheckResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    @GetMapping("/check")
    private AuthCheckResponse check(){
        return AuthCheckResponse.NO_AUTH;
    }
}
