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
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lista);
        String jsonExample =
                "{ \"nome\": \"Alicate\", \"descricao\": \"um alicate\", \"foto\": \"https://www.google.com.br/imgres?imgurl=http%3A%2F%2F7it.com.br%2Fwp-content%2Fuploads%2F2015%2F10%2FAzure_Migrate-to-azure_Migrate-to-azure.png&imgrefurl=http%3A%2F%2Fwww.7it.com.br%2Fazure%2F&docid=nLjxC220h9yG2M&tbnid=8LTHA69y3ZxEWM%3A&w=980&h=560&bih=613&biw=1366&ved=0ahUKEwii8v7T0cnNAhUJHJAKHVw5DQQQMwg0KAEwAQ&iact=mrc&uact=8\", \"quantidade\":2 }";

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