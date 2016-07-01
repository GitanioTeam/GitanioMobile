package developers.gitanio.es.gitanio;

import java.util.List;

/**
 * Created by pedro on 01/07/16.
 */
public interface AsyncResponse {
    void setListProduto(List<Produto> output);
    List<Produto> getListProduto();
}
