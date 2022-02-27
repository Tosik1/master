package main.service;

import lombok.SneakyThrows;
import main.api.request.EditProfileRequest;
import main.api.request.LoginRequest;
import main.api.request.PasswordRequest;
import main.api.request.RestoreRequest;
import main.api.response.*;
import main.model.User;
import main.model.custom.CustomUserForLogin;
import main.repository.CaptchaCodesRepository;
import main.repository.GlobalSettingsRepository;
import main.repository.PostRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.SQLOutput;
import java.util.Date;
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

    @Autowired
    private PostService postService;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private GlobalSettingsRepository settingsRepository;

    private static Random sRandom = new Random();
    private static char[] sAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    private static int sLength = sAlphabet.length;

    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public ResponseEntity createUser(String eMail, String password, String name, String captcha, String captchaSecret) {
        if (settingsRepository.getMultiuserSetting().equals("YES")) {
            RegisterResponse registerResponse = new RegisterResponse();
            ErrorsResponse errorsResponse = new ErrorsResponse();
            int i = 0;
            if (!captchaCodesRepository.findCodeBySecretCode(captchaSecret).equals(captcha)) {
                errorsResponse.setCaptcha("Код с картинки введен неверно");
                i++;
            }
            if (userRepository.findUserByEmail(eMail).isPresent()) {
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
                User user = new User();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
                String hashedPassword = passwordEncoder.encode(password);
                user.setPassword(hashedPassword);
                user.setEmail(eMail);
                user.setName(name);
                user.setIsModerator(0);
                user.setRegTime(new Date(System.currentTimeMillis()));
                userRepository.save(user);
            }

            return ResponseEntity.ok(registerResponse);
        } else {
            return (ResponseEntity) ResponseEntity.notFound();
        }
    }

    @SneakyThrows
    public ResponseEntity addImage(MultipartFile file) {

        ImageResponse imageResponse = new ImageResponse();
        ImageErrorsResponse errors = new ImageErrorsResponse();
        int i = 0;
        String contentType = file.getContentType();

        if (!contentType.substring(contentType.indexOf("/") + 1).equals("jpg") || !contentType.substring(contentType.indexOf("/") + 1).equals("png")) {
            System.out.println(contentType.substring(contentType.indexOf("/") + 1));
            errors.setFormat("Отправлен файл неподходящего формата. Требуются изображения формата \"jpg\" или \"png\"");
            imageResponse.setErrors(errors);
            i++;
        }
        String pathImage = getPathToImage(file);
        File image = new File(pathImage);
        OutputStream outStream = new FileOutputStream(image);
        outStream.write(file.getBytes());

        Image im = ImageIO.read(image);
        if (im.getWidth(null) > 360 || im.getHeight(null) > 360) {
            errors.setFormat("Отправлен файл неподходящего размера. Максимальный размер изображения 36х36");
            imageResponse.setErrors(errors);
            i++;
        }

        if (i > 0) {
            return ResponseEntity.badRequest().body(imageResponse);
        } else {
            return ResponseEntity.ok(pathImage.replace("src/main/resources/static", ""));
        }
//        if (contentType.substring(contentType.indexOf("/") + 1).equals("jpg") || contentType.substring(contentType.indexOf("/") + 1).equals("png")) {
//
//            String pathImage = getPathToImage(file);
//            File image = new File(pathImage);
//            OutputStream outStream = new FileOutputStream(image);
//            outStream.write(file.getBytes());
//
//            Image im = ImageIO.read(image);
//
//            if (im.getWidth(null) == 360 && im.getHeight(null) == 360) {
//                return ResponseEntity.ok(pathImage.replace("src/main/resources/static", ""));
//            } else {
//                errors.setFormat("Отправлен файл неподходящего размера. Максимальный размер изображения 36х36");
//                imageResponse.setErrors(errors);
//                return ResponseEntity.badRequest().body(imageResponse);
//            }
//        } else {
//            System.out.println(contentType.substring(contentType.indexOf("/") + 1));
//            errors.setSize("Отправлен файл неподходящего формата. Требуются изображения формата \"jpg\" или \"png\"");
//            imageResponse.setErrors(errors);
//            return ResponseEntity.badRequest().body(imageResponse);
//        }
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

        String pathToResizePhoto = pathImage.substring(0, pathImage.lastIndexOf(".")) + "-resize" + pathImage.substring(pathImage.lastIndexOf("."));
        resizeFile(pathImage, pathToResizePhoto, 36, 36);

        if ((image.length() / (1024 * 1024)) > 5) {
            errorsProfileResponse.setPhoto("Фото слишком большое, нужно не более 5 Мб");
        } else {
            currentUser.setPhoto(pathToResizePhoto.replace("src/main/resources/static", ""));
        }

        if (password != null) {
            if (password.length() < 6) {
                errorsProfileResponse.setPassword("Пароль короче 6-ти символов");
                countErrors++;
            } else {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
                String hashedPassword = passwordEncoder.encode(password);
                currentUser.setPassword(hashedPassword);
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
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
                String hashedPassword = passwordEncoder.encode(password);
                currentUser.setPassword(hashedPassword);
            }
        }

        userRepository.save(currentUser);

        if (countErrors > 0) {
            editProfileResponse.setResult(false);
        }
        editProfileResponse.setErrors(errorsProfileResponse);

        return ResponseEntity.ok(editProfileResponse);
    }

    public LoginResponse getLoginResponse(String email) {

        main.model.User currentUser = findByEmail(email);

        CustomUserForLogin userResponse = new CustomUserForLogin();

        userResponse.setEmail(currentUser.getEmail());
        userResponse.setModeration(currentUser.isModerator());
        userResponse.setId(currentUser.getId());
        userResponse.setPhoto(currentUser.getPhoto());
        userResponse.setName(currentUser.getName());
        if (currentUser.isModerator()) {
            userResponse.setSettings(true);
            userResponse.setModerationCount(postService.countPostsOnModeration());
        }
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setResult(true);
        loginResponse.setUser(userResponse);
        return loginResponse;
    }

    public ResponseEntity sendMailForResponse(RestoreRequest request) {
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();

            String hash = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            User user = userRepository.findUserByEmail(request.getEmail()).get();
            user.setCode(hash);
            userRepository.save(user);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(request.getEmail());
            simpleMailMessage.setSubject("Восстановление пароля");
            simpleMailMessage.setText("Ссылка на восстановление пароля: localhost:8080/login/change-password/" + hash);
            emailSender.send(simpleMailMessage);

            return ResponseEntity.ok(new RestoreResponse(true));
        } else {
            return ResponseEntity.ok(new RestoreResponse(false));
        }
    }

    public ResponseEntity password(PasswordRequest request){
        int i = 0;
        PasswordResponse response = new PasswordResponse();
        PasswordErrorsResponse errorsResponse = new PasswordErrorsResponse();
        User user = userRepository.findUserByCode(request.getCode()).get();
        if (captchaCodesRepository.findCodeBySecretCode(request.getCaptchaSecret()) == null){
            errorsResponse.setCaptcha("Код с картинки введён неверно");
            i++;
        }else {
            String code = captchaCodesRepository.findCodeBySecretCode(request.getCaptchaSecret());
            if (request.getCaptcha().equals(code)) {
                if (request.getPassword().length() < 6) {
                    errorsResponse.setPassword("Пароль короче 6-ти символов");
                    i++;
                } else {
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
                    String hashedPassword = passwordEncoder.encode(request.getPassword());
                    user.setPassword(hashedPassword);
                    userRepository.save(user);
                }
            } else {
                errorsResponse.setCode("\"Ссылка для восстановления пароля устарела. <a href=\"/auth/restore\">Запросить ссылку снова</a>");
                i++;
            }
        }
        if (i > 0) {
            response.setErrors(errorsResponse);
            response.setResult(false);
            return ResponseEntity.ok(response);
        }
        else {
            response.setResult(true);
            return ResponseEntity.ok(response);
        }
    }

    @SneakyThrows
    private String getPathToImage(MultipartFile file) {
        StringBuilder builder = new StringBuilder();
        builder.append("src/main/resources/static/upload");
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

    private static void resizeFile(String imagePathToRead, String imagePathToWrite, int resizeWidth, int resizeHeight)
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

        try {
            File file = new File(imagePathToWrite);
            ImageIO.write(bufferedImageOutput, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private char getRandomChar() {
        return sAlphabet[sRandom.nextInt(sLength)];
    }


    private boolean validateName(String name) {
        String regx = "^[a-z0-9_-]{3,15}$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }
}
