package developers.gitanio.es.gitanio;


import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProdutoAdapter extends BaseAdapter{

    Context ctx;
    List<Produto> produtos;


    public ProdutoAdapter(Context _ctx, List<Produto> _produtos){
        this.ctx = _ctx;
        this.produtos = _produtos;

    }

    @Override
    public int getCount(){
        return this.produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Produto produto = produtos.get(position);
        View linha = LayoutInflater.from(this.ctx).inflate(R.layout.item_produto, null);
        ImageView imgProduto = (ImageView) linha.findViewById(R.id.foto_produto);
        TextView txtProduto = (TextView) linha.findViewById(R.id.text_produto);
        TextView txtQuantidade = (TextView) linha.findViewById(R.id.text_qnt);

        Resources res = ctx.getResources();

        return null;
    }
}
