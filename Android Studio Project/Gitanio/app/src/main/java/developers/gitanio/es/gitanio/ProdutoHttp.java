package developers.gitanio.es.gitanio;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class ProdutoHttp extends AsyncTask<Void,Void,List<Produto>> {

    public static final String BASE_URL = "https://raw.githubusercontent.com/gustavosotnas/gitanio/master/";
    private AsyncResponse delegate;

    public ProdutoHttp(AsyncResponse delegate){
        this.delegate = delegate;
    }

    public AsyncResponse getDelegate() {
        return delegate;
    }

    private Produto[] obterProdutosDoServidor(){

        Produto[] resposta = null;

        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(5, TimeUnit.SECONDS);
        client.setConnectTimeout(10, TimeUnit.SECONDS);

        try {
            String linkUrl = BASE_URL + "gitanio.json";
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
        delegate.setListProduto(produtos);
    }
}