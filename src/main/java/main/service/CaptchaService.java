package main.service;

import com.github.cage.Cage;
import com.github.cage.GCage;
import main.api.response.CaptchaResponse;
import main.repository.CaptchaCodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

        String text = cage.getTokenGenerator().next().substring(0, 5);
        stringBuilder.append("data:image/png;base64, ");
        stringBuilder.append(Base64.getEncoder()
                .encodeToString(cage.draw(text)));

        CaptchaResponse captchaResponse = new CaptchaResponse(text.hashCode(), stringBuilder.toString());
        captchaCodesRepository.putCaptcha(text, String.valueOf(text.hashCode()));

        return captchaResponse;
    }

    @Scheduled(cron = "0 1 * * * ?")
    public void removeOldCaptcha(){
        captchaCodesRepository.deleteOldCaptcha();
    }
}
