package main.service;

import lombok.SneakyThrows;
import main.api.request.EditProfileRequest;
import main.api.response.*;
import main.model.User;
import main.repository.CaptchaCodesRepository;
import main.repository.PostRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Random;
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

    private static Random sRandom = new Random();
    private static char[] sAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static int sLength = sAlphabet.length;

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public RegisterResponse createUser(String eMail, String password, String name, String captcha, String captchaSecret) {
        RegisterResponse registerResponse = new RegisterResponse();
        ErrorsResponse errorsResponse = new ErrorsResponse();
        int i = 0;
        if (!captchaCodesRepository.findCodeBySecretCode(captchaSecret).equals(captcha)) {
            errorsResponse.setCaptcha("Код с картинки введен неверно");
            i++;
        }
        User test = userRepository.findUserByEmail(eMail).orElseThrow(() -> new UsernameNotFoundException(eMail));
        if (test != null && test.getEmail().equals(eMail)) {
            errorsResponse.setEmail("Пользователь с таким e-mail уже зарегестрирован");
            i++;
        }
        if (!validateName(name)) {
            errorsResponse.setName("Имя указано неверно");
            i++;
        }
        if (password.length() < 6) {
            i++;
            errorsResponse.setPassword("Пароль короче 6 символов");
        }

        if (i >= 1) {
            registerResponse.setResult(false);
            registerResponse.setErrors(errorsResponse);
        } else {
            userRepository.insertUser(eMail, String.valueOf(password.hashCode()), name, 0);
        }

        return registerResponse;
    }

    @SneakyThrows
    public ResponseEntity addImage(MultipartFile file) {

        InputStream inputStream = new ByteArrayInputStream(file.getBytes());

        ImageResponse imageResponse = new ImageResponse();
        ImageErrorsResponse errors = new ImageErrorsResponse();

        String contentType = file.getContentType();
        if (contentType.substring(contentType.indexOf("/") + 1).equals("jpg") || contentType.substring(contentType.indexOf("/") + 1).equals("png")) {

            String pathImage = getPathToImage(file);
            File image = new File(pathImage);
            OutputStream outStream = new FileOutputStream(image);
            outStream.write(file.getBytes());

            Image im = ImageIO.read(image);

            if (im.getWidth(null) == 36 && im.getHeight(null) == 36) {

//            String newName = builder + file.getOriginalFilename().substring(0, contentType.indexOf("/") - 1) + "-resize." + contentType.substring(contentType.indexOf("/") + 1);

//            resizeFile(builder + file.getOriginalFilename(), newName, 36, 36);

                return ResponseEntity.ok(pathImage);
            } else {
                errors.setImage("Отправлен файл неподходящего размера. Максимальный размер изображения 36х36");
                imageResponse.setErrors(errors);
                return ResponseEntity.badRequest().body(imageResponse);
            }
        } else {

            errors.setImage("Отправлен файл неподходящего формата. Требуются изображения формата \"jpg\" или \"png\"");
            imageResponse.setErrors(errors);
            return ResponseEntity.badRequest().body(imageResponse);
        }
    }

    @SneakyThrows
    public ResponseEntity<EditProfileResponse> editProfileWithPhoto(String email, int removePhoto, MultipartFile photo, String name, String password) {
        EditProfileResponse editProfileResponse = new EditProfileResponse();
        ErrorsProfileResponse errorsProfileResponse = new ErrorsProfileResponse();

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findUserByEmail(principal.getUsername()).get();

        int countErrors = 0;

        if (name != null) {
            if (name.length() < 3) {
                errorsProfileResponse.setName("Имя слишком короткое");
                countErrors++;
            } else {
                currentUser.setName(name);
            }
        }
        if (!email.equals(currentUser.getEmail())) {
            if (userRepository.findUserByEmail(email).isPresent()) {
                errorsProfileResponse.setEmail("Этот e-mail уже зарегистрирован");
                countErrors++;
            } else {
                currentUser.setEmail(email);
            }
        }
        if (removePhoto == 1) {
            currentUser.setPhoto("");
        }

        String pathImage = getPathToImage(photo);
        File image = new File(pathImage);
        OutputStream outStream = new FileOutputStream(image);
        outStream.write(photo.getBytes());
        if ((image.length() / (1024 * 1024)) > 5) {
            errorsProfileResponse.setPhoto("Фото слишком большое, нужно не более 5 Мб");
        } else {
            currentUser.setPhoto(pathImage);
        }

        if (password != null) {
            if (password.length() < 6) {
                errorsProfileResponse.setPassword("Пароль короче 6-ти символов");
                countErrors++;
            } else {
                currentUser.setPassword(password);
            }
        }

        userRepository.save(currentUser);

        if (countErrors > 0) {
            editProfileResponse.setResult(false);
            editProfileResponse.setErrors(errorsProfileResponse);
        }

        return ResponseEntity.ok(editProfileResponse);
    }


    public ResponseEntity<EditProfileResponse> editProfileWithoutPhoto(EditProfileRequest editProfileRequest) {
        EditProfileResponse editProfileResponse = new EditProfileResponse();
        ErrorsProfileResponse errorsProfileResponse = new ErrorsProfileResponse();

        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findUserByEmail(principal.getUsername()).get();

        String name = editProfileRequest.getName();
        String email = editProfileRequest.getEmail();
        int removePhoto = editProfileRequest.getRemovePhoto();
        String password = editProfileRequest.getPassword();

        int countErrors = 0;

        if (name != null) {
            if (name.length() < 3) {
                errorsProfileResponse.setName("Имя слишком короткое");
                countErrors++;
            } else {
                currentUser.setName(editProfileRequest.getName());
            }
        }
        if (!email.equals(currentUser.getEmail())) {
            if (userRepository.findUserByEmail(email).isPresent()) {
                errorsProfileResponse.setEmail("Этот e-mail уже зарегистрирован");
                countErrors++;
            } else {
                currentUser.setEmail(editProfileRequest.getEmail());
            }
        }
        if (removePhoto == 1) {
            currentUser.setPhoto("");
        }

        if (password != null) {
            if (password.length() < 6) {
                errorsProfileResponse.setPassword("Пароль короче 6-ти символов");
                countErrors++;
            } else {
                currentUser.setPassword(password);
            }
        }

        userRepository.save(currentUser);

        if (countErrors > 0) {
            editProfileResponse.setResult(false);
        }
        editProfileResponse.setErrors(errorsProfileResponse);

        return ResponseEntity.ok(editProfileResponse);
    }

    @SneakyThrows
    private String getPathToImage(MultipartFile file) {
        StringBuilder builder = new StringBuilder();
        builder.append("upload");
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) {
                builder.append("/");
            }
            builder.append(getRandomChar());
        }
        builder.append("/");
        File dirImage = new File(builder.toString());
        dirImage.mkdirs();

        String pathImage = builder + file.getOriginalFilename();

        return pathImage;
    }

    public static void resizeFile(String imagePathToRead, String imagePathToWrite, int resizeWidth, int resizeHeight)
            throws IOException {

        File fileToRead = new File(imagePathToRead);
        BufferedImage bufferedImageInput = ImageIO.read(fileToRead);

        BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,
                resizeHeight, bufferedImageInput.getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
        g2d.dispose();

        String formatName = imagePathToWrite.substring(imagePathToWrite
                .lastIndexOf(".") + 1);

        ImageIO.write(bufferedImageOutput, formatName, new File(imagePathToWrite));
    }


    private char getRandomChar() {
        return sAlphabet[sRandom.nextInt(sLength)];
    }

//    public EditProfileResponse editProfile(){
//
//    }

    private boolean validateName(String name) {
        String regx = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}
