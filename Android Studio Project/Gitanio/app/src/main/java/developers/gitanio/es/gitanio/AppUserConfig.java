package developers.gitanio.es.gitanio;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pedro on 01/07/16.
 */
public class AppUserConfig {
    private static AppUserConfig ourInstance = new AppUserConfig();

    public static AppUserConfig getInstance() {
        return ourInstance;
    }

    private JSONObject jsonUserLoginConfig;
    private String token;

    private AppUserConfig() {
    }

    public JSONObject getUserLoginConfig() {
        return jsonUserLoginConfig;
    }

    public void setUserLoginConfig(String user, String senha) {

        try {
            this.jsonUserLoginConfig = JsonConverter.getAutenticacao(user,senha);
        } catch (JSONException e) {
            this.jsonUserLoginConfig = null;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {

        try {
            this.token = JsonConverter.getToken(token);
        } catch (JSONException e) {
            // Autenticação recusada
            this.token = null;
        }
    }
}
