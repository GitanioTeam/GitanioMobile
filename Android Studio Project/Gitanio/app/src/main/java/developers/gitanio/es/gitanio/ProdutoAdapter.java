package developers.gitanio.es.gitanio;


import android.content.Context;
import android.content.res.Resources;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class ProdutoAdapter extends BaseAdapter{

    Context ctx;
    List<Produto> produtos;


    public ProdutoAdapter(Context ctx, List<Produto> produtos){
        this.ctx = ctx;
        this.produtos = produtos;

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

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View produtoView = inflater.inflate(R.layout.item_produto, parent, true);

        try {
            TextView nome = (TextView) produtoView.findViewById(R.id.text_produto);
            TextView quantidade = (TextView) produtoView.findViewById(R.id.text_qnt);
            ImageView foto = (ImageView) produtoView.findViewById(R.id.foto_produto);

            nome.setText(produto.getNome());
            quantidade.setText(produto.getQuantidade());
            foto.setImageBitmap(ProdutoHttp.getImageBitmap(produto));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return produtoView;
    }
}