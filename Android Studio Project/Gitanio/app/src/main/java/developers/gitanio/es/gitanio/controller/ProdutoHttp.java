package developers.gitanio.es.gitanio.controller;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    private List<Produto> obterProdutosDoServidor(){

        List<Produto> produtoList = new ArrayList<>();

        try {


            HttpHeaders token = AppUserConfig.getInstance().getToken();

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            // Make the network request
            ResponseEntity<String> response = restTemplate.exchange(DicionarioURL.GET_PRODUTOS_URL, HttpMethod.GET,
                    new HttpEntity<Object>(token), String.class);

            String body = response.getBody();
            JSONObject json = new JSONObject(body);
            json = json.getJSONObject("_embedded");
            JSONArray array = json.getJSONArray("produtos");

            produtoList = new ArrayList<>();
            Gson gson = new Gson();

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = array.getJSONObject(i);
                jsonObject.remove("_links");

                Produto produto = gson.fromJson(jsonObject.toString(), Produto.class);


                produtoList.add(produto);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return produtoList;
    }

    @Override
    protected List<Produto> doInBackground(Void... params) {

        List<Produto> listaProdutos = obterProdutosDoServidor();
        return(listaProdutos);
    }

    @Override
    protected void onPostExecute(List<Produto> produtos) {
        asyncResponse.onFinish(produtos);
    }
}