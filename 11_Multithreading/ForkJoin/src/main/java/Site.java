import java.io.IOException;
import java.util.HashSet;

public class Site {


    private String site;
    private HashSet<Site> childeSite;

    public HashSet<Site> getChildeSite() {
        try {
            return Parser.parseHTML(this.getSite());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setChildeSite(HashSet<Site> childeSite) {
        this.childeSite = childeSite;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
