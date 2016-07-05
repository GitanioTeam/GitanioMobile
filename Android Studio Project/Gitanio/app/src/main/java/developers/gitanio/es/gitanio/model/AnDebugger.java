package developers.gitanio.es.gitanio.model;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpHeaders;

/**
 * Created by pedro on 05/07/16.
 */
public class AnDebugger {
    private static AnDebugger ourInstance = new AnDebugger();
    private HttpAuthentication deb;

    public HttpAuthentication getDeb() {
        return deb;
    }

    public void setDeb(HttpAuthentication deb) {
        this.deb = deb;
    }

    public static AnDebugger getInstance() {
        return ourInstance;
    }

    private AnDebugger() {
    }


}
