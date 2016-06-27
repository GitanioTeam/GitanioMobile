package developers.gitanio.es.gitanio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = (ListView) findViewById(R.id.lista);
        String jsonExample =
                "{ \"nome\": \"Alicate\", \"descricao\": \"um alicate\", \"imagemLink\": \"http://blablabla\", \"quantidade\":2 }";

        try {
            Produto p = JsonConverter.getProduto(jsonExample);

            List<Produto> produtos = new ArrayList<>();
            produtos.add(p);

            ProdutoAdapter produtoAdapter = new ProdutoAdapter(this.getApplicationContext(), produtos);
            listView.setAdapter(produtoAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}