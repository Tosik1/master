package main.core.repository;

import main.core.model.CaptchaCodes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CaptchaCodesRepository extends CrudRepository<CaptchaCodes, Integer> {

    @Modifying
    @Transactional
    @Query(value = "Insert captcha_codes(code, secret_code, time) values( ?1, ?2, sysdate())", nativeQuery = true)
    void putCaptcha(String code, String secret_code);

    @Modifying
    @Transactional
    @Query(value = "delete FROM captcha_codes as cc where sysdate() - cc.time > 3600000", nativeQuery = true)
    void deleteOldCaptcha();

    @Query(value = "Select cc.code from captcha_codes as cc where cc.secret_code = ?1", nativeQuery = true)
    String findCodeBySecretCode(String secretCode);
}
