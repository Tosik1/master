package main.core.api.response;

public class CaptchaResponse {

    private int secret;

    private String image;

    public CaptchaResponse(int secret, String image) {
        this.secret = secret;
        this.image = image;
    }

    public int getSecret() {
        return secret;
    }

    public void setSecret(int secret) {
        this.secret = secret;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
