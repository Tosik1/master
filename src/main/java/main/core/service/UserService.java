package main.core.service;

import main.core.api.response.ErrorsResponse;
import main.core.api.response.RegisterResponse;
import main.core.model.User;
import main.core.repository.CaptchaCodesRepository;
import main.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CaptchaCodesRepository captchaCodesRepository;

    public RegisterResponse createUser(String eMail, String password, String name, String captcha, String captchaSecret) {
        RegisterResponse registerResponse = new RegisterResponse();
        ErrorsResponse errorsResponse = new ErrorsResponse();
        if (captchaCodesRepository.findCodeBySecretCode(captchaSecret).equals(captcha)){

            User test = userRepository.findUserByEmail(eMail);
            if (test == null || !test.getEmail().equals(eMail)){
                if (validateName(name)){
                    if (password.length() >= 6){
                        userRepository.insertUser(eMail, String.valueOf(password.hashCode()), name, 0);
                        return registerResponse;
                    } else {
                        errorsResponse.setPassword("Пароль короче 6 символов");
                    }
                } else {
                    errorsResponse.setName("Имя указано неверно");
                }
            } else {
                errorsResponse.setEmail("Пользователь с таким e-mail уже зарегестрирован");
            }
        } else {
            errorsResponse.setCaptcha("Код с картинки введен неверно");
        }

        registerResponse.setResult(false);
        registerResponse.setErrors(errorsResponse);

        return registerResponse;
    }

    private boolean validateName(String name){
        String regx = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}
