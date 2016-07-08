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
import developers.gitanio.es.gitanio.services.AsyncResponse;

public class MainActivity extends AppCompatActivity implements AsyncResponse{

    private List<Produto> listaProdutos = new ArrayList<Produto>();
    private RecyclerView recyclerView;
    private ProdutoAdapter mAdapter;
    private ProdutoHttp produtoHttp;

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

        if(btn != null){
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

        // Buscando dados do service
        this.produtoHttp = new ProdutoHttp(this);
        this.produtoHttp.execute();

        //Código de requisicao
//        Button btn = (Button) findViewById(R.id.atualizar_btn);
//        View text =  findViewById(R.id.texto_erro);
//
//        try{
//            if(this.listaProdutos.size() > 0){
//
//                btn.setVisibility(View.GONE);
//                text.setVisibility(View.GONE);
//            }else{
//
//                btn.setVisibility(View.VISIBLE);
//                text.setVisibility(View.VISIBLE);
//            }
//
//        }catch(NullPointerException e ){
//
//            btn.setVisibility(View.VISIBLE);
//            text.setVisibility(View.VISIBLE);
//        }
//
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinish(List<Produto> output) {


        for(Produto i: output){
            listaProdutos.add(i);
        }

        //Código de requisicao
        Button btn = (Button) findViewById(R.id.atualizar_btn);
        View text =  findViewById(R.id.texto_erro);

        try{
            if(this.listaProdutos.size() > 0){

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
