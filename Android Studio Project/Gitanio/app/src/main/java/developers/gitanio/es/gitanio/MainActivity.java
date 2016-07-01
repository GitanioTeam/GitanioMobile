package developers.gitanio.es.gitanio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Produto> listaProdutos = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProdutoAdapter mAdapter;
    private ProdutoHttp produtoHttp;
    private AsyncHandle asyncHandle = new AsyncHandle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.produtos_toolbar);
        ToolbarSupport.startToolbar(this, toolbar,"Estoque");

        recyclerView = (RecyclerView) findViewById(R.id.list_produtos);

        mAdapter = new ProdutoAdapter(listaProdutos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareProdutoData();

    /*
        recyclerView.addOnItemTouchListener(new RecyclerViewListener.RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerViewListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Produto produto = listaProdutos.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    */
    }

    private void prepareProdutoData() {
        //testando layout da lista
        listaProdutos.add(new Produto("Cerveja Bavaria", "Uma cerva", "link1", "23"));
        listaProdutos.add(new Produto("Cerveja Corona", "Uma outra cerva", "link2", "47"));
        listaProdutos.add(new Produto("Refrigerante Coca Cola", "Uma coca", "link3", "23"));
        listaProdutos.add(new Produto("Refrigerante Guarana 2l", "Esse é bom", "link4", "23"));

        // Código de requisicao
        //this.produtoHttp = new ProdutoHttp(this.asyncHandle);
        //this.produtoHttp.execute();
        //this.listaProdutos = this.asyncHandle.getListProduto();

        mAdapter.notifyDataSetChanged();

    }

}
