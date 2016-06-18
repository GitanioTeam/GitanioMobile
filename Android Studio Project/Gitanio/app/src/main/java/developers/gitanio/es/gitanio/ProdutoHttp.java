package developers.gitanio.es.gitanio;

import java.util.concurrent.TimeUnit;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class ProdutoHttp {

    public static final String BASE_URL =
            "https://raw.githubusercontent.com/gustavosotnas/gitanio/master/";
    public static Produto[] obterProdutosDoServidor(){
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(5, TimeUnit.SECONDS);
        client.setConnectTimeout(10, TimeUnit.SECONDS);
        Request request = new Request.Builder()
                .url(BASE_URL + "gitanio.json")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            Gson gson = new Gson();
            return gson.fromJson(json, Produto[].class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}