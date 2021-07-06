package main.core.controller;

import main.core.api.request.UserRequest;
import main.core.api.response.AuthCheckResponse;
import main.core.api.response.CaptchaResponse;
import main.core.api.response.RegisterResponse;
import main.core.service.CaptchaService;
import main.core.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private final CaptchaService captchaService;

    private final UserService userService;

    public ApiAuthController(CaptchaService captchaService, UserService userService) {
        this.captchaService = captchaService;
        this.userService = userService;
    }

    @GetMapping("/check")
    private AuthCheckResponse check(){
        return AuthCheckResponse.NO_AUTH;
    }

    @GetMapping("/captcha")
    private CaptchaResponse captcha() throws IOException {
        return captchaService.getApiAuthCaptchaResponse();
    }

    @PostMapping("/register")
    private RegisterResponse register(@Valid @RequestBody UserRequest request){
        return userService.createUser(request.getE_mail(), request.getPassword(), request.getName(), request.getCaptcha(), request.getCaptcha_secret());
    }
}
