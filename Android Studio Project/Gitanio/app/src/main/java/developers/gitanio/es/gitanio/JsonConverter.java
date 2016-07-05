package developers.gitanio.es.gitanio;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import developers.gitanio.es.gitanio.model.Produto;

/**
 * Created by pedro on 26/06/16.
 */
public class JsonConverter {

    public static Produto getProduto(String json) throws JSONException {

        Gson gson = new Gson();
        Produto produto = gson.fromJson(json, Produto.class);

        return(produto);
    }

    public static JSONObject getAutenticacao(String user, String senha) throws JSONException {

        JSONObject autenticacao = new JSONObject();
        autenticacao.put("user", user);
        autenticacao.put("senha", senha);

        return(autenticacao);
    }

    public static String getToken(String json) throws JSONException {

        JSONObject token = new JSONObject(json);
        return(token.getString("token"));
    }
}
