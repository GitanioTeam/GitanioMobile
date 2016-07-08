package developers.gitanio.es.gitanio.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import developers.gitanio.es.gitanio.R;
import developers.gitanio.es.gitanio.model.Produto;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder>{

    private List<Produto> listaProdutos;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView descricao;
        public TextView qntMinima;

        public MyViewHolder(View view) {
            super(view);
            descricao = (TextView) view.findViewById(R.id.text_produto);
            qntMinima = (TextView) view.findViewById(R.id.text_qnt);
        }
    }


    public ProdutoAdapter(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Produto produto = listaProdutos.get(position);
        holder.descricao.setText(produto.getDescricao());
        holder.qntMinima.setText(Integer.toString(produto.getQuantidadeMinima()));
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }
}