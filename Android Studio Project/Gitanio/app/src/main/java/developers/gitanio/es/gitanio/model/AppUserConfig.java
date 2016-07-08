package developers.gitanio.es.gitanio.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;

import developers.gitanio.es.gitanio.services.JsonConverter;

/**
 * Created by pedro on 01/07/16.
 */
public class AppUserConfig {

    private static AppUserConfig ourInstance = new AppUserConfig();

    public static AppUserConfig getInstance() {
        return ourInstance;
    }

    private HttpHeaders token;

    private AppUserConfig() {
    }

    public HttpHeaders getToken() {
        return token;
    }

    public void setToken(HttpHeaders token) {

        this.token = token;
    }
}
