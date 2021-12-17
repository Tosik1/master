package main.service;

import main.api.response.ErrorsResponse;
import main.api.response.RegisterResponse;
import main.repository.CaptchaCodesRepository;
import main.repository.PostRepository;
import main.repository.UserRepository;
import main.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CaptchaCodesRepository captchaCodesRepository;

    @Autowired
    private PostRepository postRepository;

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public RegisterResponse createUser(String eMail, String password, String name, String captcha, String captchaSecret) {
        RegisterResponse registerResponse = new RegisterResponse();
        ErrorsResponse errorsResponse = new ErrorsResponse();
        int i = 0;
        if (!captchaCodesRepository.findCodeBySecretCode(captchaSecret).equals(captcha)){
            errorsResponse.setCaptcha("Код с картинки введен неверно");
            i++;
        }
        User test = userRepository.findUserByEmail(eMail).orElseThrow(() -> new UsernameNotFoundException(eMail));
        if (test != null && test.getEmail().equals(eMail)){
            errorsResponse.setEmail("Пользователь с таким e-mail уже зарегестрирован");
            i++;
        }
        if (!validateName(name)){
            errorsResponse.setName("Имя указано неверно");
            i++;
        }
        if (password.length() < 6){
            i++;
            errorsResponse.setPassword("Пароль короче 6 символов");
        }

        if (i >= 1) {
            registerResponse.setResult(false);
            registerResponse.setErrors(errorsResponse);
        }else {
            userRepository.insertUser(eMail, String.valueOf(password.hashCode()), name, 0);
        }

        return registerResponse;
    }

    private boolean validateName(String name){
        String regx = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}
