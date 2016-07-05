package developers.gitanio.es.gitanio.controller;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import developers.gitanio.es.gitanio.model.AppUserConfig;
import developers.gitanio.es.gitanio.model.DicionarioURL;
import developers.gitanio.es.gitanio.services.JsonConverter;
import developers.gitanio.es.gitanio.model.Produto;
import developers.gitanio.es.gitanio.services.AsyncResponse;

public class ProdutoHttp extends AsyncTask<Void,Void,List<Produto>> {

    private AsyncResponse asyncResponse;
    private OkHttpClient client;

    public ProdutoHttp(AsyncResponse asyncResponse){
        
        this.asyncResponse = asyncResponse;
        this.client = new OkHttpClient();
        client.setReadTimeout(5, TimeUnit.SECONDS);
        client.setConnectTimeout(10, TimeUnit.SECONDS);

    }

    public AsyncResponse getDelegate() {
        return asyncResponse;
    }

    private Produto[] obterProdutosDoServidor(){

        Produto[] resposta = null;

        try {
            String token = AppUserConfig.getInstance().getToken();
            String linkUrl = DicionarioURL.GET_TOKEN_URL;

            Request request = new Request.Builder().url(linkUrl).build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            Gson gson = new Gson();
            resposta = gson.fromJson(json, Produto[].class);

        } catch (Exception e){
            e.printStackTrace();
        }

        return resposta;
    }

    @Override
    protected List<Produto> doInBackground(Void... params) {

        List<Produto> listaProdutos = new ArrayList<>();

        Produto[] produtos = obterProdutosDoServidor();
        for(Produto p : produtos){
            listaProdutos.add(p);
        }

        return(listaProdutos);
    }

    @Override
    protected void onPostExecute(List<Produto> produtos) {
        asyncResponse.setListProduto(produtos);
    }
}