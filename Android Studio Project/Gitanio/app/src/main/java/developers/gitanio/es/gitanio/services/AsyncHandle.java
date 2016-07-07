package developers.gitanio.es.gitanio.services;

import java.util.List;

import developers.gitanio.es.gitanio.model.Produto;

/**
 * Created by pedro on 01/07/16.
 */
public class AsyncHandle implements  AsyncResponse {

    private List<Produto> listaProduto;

    @Override
    public void setListProduto(List<Produto> output) {
        this.listaProduto = output;
    }

    @Override
    public List<Produto> getListProduto() {

        return this.listaProduto;
    }
}
