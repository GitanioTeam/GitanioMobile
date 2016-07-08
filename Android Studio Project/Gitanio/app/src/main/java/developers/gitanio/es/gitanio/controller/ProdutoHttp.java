package developers.gitanio.es.gitanio.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import developers.gitanio.es.gitanio.model.AppUserConfig;
import developers.gitanio.es.gitanio.model.DicionarioURL;
import developers.gitanio.es.gitanio.model.Produto;
import developers.gitanio.es.gitanio.services.AsyncResponse;

public class ProdutoHttp extends AsyncTask<Void,Void,List<Produto>> {

    private AsyncResponse asyncResponse;

    public ProdutoHttp(AsyncResponse asyncResponse){
        
        this.asyncResponse = asyncResponse;
    }

    public AsyncResponse getDelegate() {
        return asyncResponse;
    }

    private Produto[] obterProdutosDoServidor(){

        Produto[] resposta = null;

        try {

            HttpHeaders token = AppUserConfig.getInstance().getToken();

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            // Make the network request
            ResponseEntity<Object> response = restTemplate.exchange(DicionarioURL.GET_PRODUTOS_URL, HttpMethod.GET,
                    new HttpEntity<Object>(token), Object.class);

            Object ob = response.getBody();

            ob.toString();

        } catch (Exception e){
            e.printStackTrace();
        }

        return resposta;
    }

    @Override
    protected List<Produto> doInBackground(Void... params) {

        List<Produto> listaProdutos = new ArrayList<>();

        Produto[] produtos = obterProdutosDoServidor();
        try{
            for(Produto p : produtos){
                listaProdutos.add(p);
            }
        }catch(NullPointerException e ){

        }


        return(listaProdutos);
    }

    @Override
    protected void onPostExecute(List<Produto> produtos) {
        asyncResponse.setListProduto(produtos);
    }
}