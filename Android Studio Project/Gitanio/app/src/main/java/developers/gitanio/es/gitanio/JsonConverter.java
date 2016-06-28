package developers.gitanio.es.gitanio;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pedro on 26/06/16.
 */
public class JsonConverter {

    public static Produto getProduto(String json) throws JSONException {

        JSONObject produtoJson = new JSONObject(json);

        int quantidade = produtoJson.getInt("quantidade");
        String nome = produtoJson.getString("nome");
        String foto = produtoJson.getString("foto");
        String descricao = produtoJson.getString("descricao");

        Produto produto = new Produto(nome, descricao, foto, quantidade);
        return(produto);
    }
}
