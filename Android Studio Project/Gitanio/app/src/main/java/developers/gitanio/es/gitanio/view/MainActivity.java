package developers.gitanio.es.gitanio.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import developers.gitanio.es.gitanio.R;
import developers.gitanio.es.gitanio.controller.ProdutoAdapter;
import developers.gitanio.es.gitanio.controller.ProdutoHttp;
import developers.gitanio.es.gitanio.controller.ToolbarSupport;
import developers.gitanio.es.gitanio.model.Produto;
import developers.gitanio.es.gitanio.services.AsyncHandle;

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
        toolbar = ToolbarSupport.startToolbar(this, toolbar,"Estoque");
        recyclerView = (RecyclerView) findViewById(R.id.list_produtos);

        mAdapter = new ProdutoAdapter(listaProdutos);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        Button btn = (Button) findViewById(R.id.atualizar_btn);
        if(btn !=null){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    prepareProdutoData();
                }
            });
        }

        prepareProdutoData();


    }

    private void prepareProdutoData() {
        //testando layout da lista
        //listaProdutos.add(new Produto("Cerveja Bavaria", "23"));
        //listaProdutos.add(new Produto("Cerveja Corona", "47"));
        //listaProdutos.add(new Produto("Refrigerante Coca Cola", "23"));
        //listaProdutos.add(new Produto("Refrigerante Guarana 2l","23"));

        //Código de requisicao
        Button btn = (Button) findViewById(R.id.atualizar_btn);
        View text =  findViewById(R.id.texto_erro);

        this.produtoHttp = new ProdutoHttp(this.asyncHandle);
        this.produtoHttp.execute();

        try{
            if(this.asyncHandle.getListProduto().size()>0){

                this.listaProdutos = this.asyncHandle.getListProduto();
                btn.setVisibility(View.GONE);
                text.setVisibility(View.GONE);
            }else{

                btn.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
            }

        }catch(NullPointerException e ){

            btn.setVisibility(View.VISIBLE);
            text.setVisibility(View.VISIBLE);
        }

        mAdapter.notifyDataSetChanged();
    }

}
