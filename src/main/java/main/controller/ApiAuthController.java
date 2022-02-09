package main.controller;

import main.api.request.LoginRequest;
import main.api.request.UserRequest;
import main.api.response.CaptchaResponse;
import main.api.response.LoginResponse;
import main.api.response.LogoutResponse;
import main.api.response.RegisterResponse;
import main.model.custom.CustomUserForLogin;
import main.service.CaptchaService;
import main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private final AuthenticationManager authenticationManager;

    private final CaptchaService captchaService;

    private final UserService userService;

    @Autowired
    public ApiAuthController(AuthenticationManager authenticationManager, CaptchaService captchaService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.captchaService = captchaService;
        this.userService = userService;
    }

    @GetMapping("/captcha")
    public CaptchaResponse captcha() throws IOException {
        return captchaService.getApiAuthCaptchaResponse();
    }

    @PostMapping("/register")
    public RegisterResponse register(@Validated @RequestBody UserRequest request) {
        return userService.createUser(request.getEmail(), request.getPassword(), request.getName(), request.getCaptcha(), request.getCaptchaSecret());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = (User) auth.getPrincipal();

        return ResponseEntity.ok(getLoginResponse(user.getUsername()));
    }

    @GetMapping("/logout")
    public LogoutResponse logout(HttpServletRequest request,
                                 HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth);
        return new LogoutResponse();
    }

    @GetMapping("/check")
    public ResponseEntity<LoginResponse> check(Principal principal){
        if (principal == null){
            return ResponseEntity.ok(new LoginResponse());
        }
        return ResponseEntity.ok(getLoginResponse(principal.getName()));
    }

    private LoginResponse getLoginResponse(String email){
        main.model.User currentUser = userService.findByEmail(email);

        CustomUserForLogin userResponse = new CustomUserForLogin();

        userResponse.setEmail(currentUser.getEmail());
        userResponse.setModeration(currentUser.isModerator());
        userResponse.setId(currentUser.getId());
        userResponse.setPhoto(currentUser.getPhoto());
        userResponse.setName(currentUser.getName());
        if (currentUser.isModerator()) {
            userResponse.setSettings(true);
        }


        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setResult(true);
        loginResponse.setUser(userResponse);
        return loginResponse;
    }
}
