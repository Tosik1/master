package main.core.service;

import com.github.cage.Cage;
import com.github.cage.GCage;
import com.github.cage.YCage;
import main.core.api.response.CaptchaResponse;
import main.core.repository.CaptchaCodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

@Service
public class CaptchaService {

    @Autowired
    private CaptchaCodesRepository captchaCodesRepository;

    public CaptchaResponse getApiAuthCaptchaResponse() throws IOException {
        Cage cage = new GCage();

        StringBuilder stringBuilder = new StringBuilder();

        String text = cage.getTokenGenerator().next();
        stringBuilder.append("data:image/png;base64, ");
        stringBuilder.append(Base64.getEncoder()
                .encodeToString(cage.draw(text)));

        CaptchaResponse captchaResponse = new CaptchaResponse(text.hashCode(), stringBuilder.toString());
        captchaCodesRepository.putCaptcha(text, String.valueOf(text.hashCode()));

        return captchaResponse;
    }
}
