package developers.gitanio.es.gitanio.services;

import java.util.List;

import developers.gitanio.es.gitanio.model.Produto;

/**
 * Created by pedro on 01/07/16.
 */
public interface AsyncResponse {
    void setListProduto(List<Produto> output);
    List<Produto> getListProduto();
}
