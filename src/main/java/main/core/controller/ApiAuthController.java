package main.core.controller;

import main.core.api.response.AuthCheckResponse;
import main.core.api.response.CaptchaResponse;
import main.core.service.CaptchaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private final CaptchaService captchaService;

    public ApiAuthController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping("/check")
    private AuthCheckResponse check(){
        return AuthCheckResponse.NO_AUTH;
    }

    @GetMapping("/captcha")
    private CaptchaResponse captcha() throws IOException {
        return captchaService.getApiAuthCaptchaResponse();
    }
}
