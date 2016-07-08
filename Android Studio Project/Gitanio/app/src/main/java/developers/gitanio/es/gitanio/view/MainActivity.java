package developers.gitanio.es.gitanio.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import developers.gitanio.es.gitanio.R;
import developers.gitanio.es.gitanio.controller.ProdutoAdapter;
import developers.gitanio.es.gitanio.controller.ProdutoHttp;
import developers.gitanio.es.gitanio.controller.ToolbarSupport;
import developers.gitanio.es.gitanio.model.Produto;
import developers.gitanio.es.gitanio.services.AsyncResponse;

public class MainActivity extends AppCompatActivity implements AsyncResponse, SwipeRefreshLayout.OnRefreshListener{
    private View text;
    private SwipeRefreshLayout mSwipeRefreshLayout ;
    private List<Produto> listaProdutos = new ArrayList<Produto>();
    private RecyclerView recyclerView;
    private ProdutoAdapter mAdapter;
    private ProdutoHttp produtoHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Iniciando interface da toolbar
        Toolbar toolbar = new Toolbar(getApplicationContext());
        ToolbarSupport.startToolbar(this,toolbar,R.id.produtos_toolbar,"Estoque");

        //Iniciando interface de delize para atualizar
        mSwipeRefreshLayout =  (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        text = findViewById(R.id.texto_erro);

        //Iniciando recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.list_produtos);

        mAdapter = new ProdutoAdapter(listaProdutos);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

       onRefresh();
    }
    @Override
    public void onRefresh() {
        // Buscando dados do service

        text.setVisibility(View.GONE);
        this.produtoHttp = new ProdutoHttp(this);
        this.produtoHttp.execute();
    }

    @Override
    public void onFinish(List<Produto> output) {
        listaProdutos.clear();

        for(Produto i: output){
            listaProdutos.add(i);
        }

        //Código de requisicao

        try{
            if(this.listaProdutos.size() > 0){


                text.setVisibility(View.GONE);
            }else{


                text.setVisibility(View.VISIBLE);
            }

        }catch(NullPointerException e ){


            text.setVisibility(View.VISIBLE);
        }

        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_estoque, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.menu_sobre) {
            startActivity(new Intent(this, SobreActivity.class));
            return true;
        }else if(id == R.id.menu_logout){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("logout", true);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
