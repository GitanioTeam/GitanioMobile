package developers.gitanio.es.gitanio.model;

public class Produto {

    private String codigo;
    private String quantidadeMinima;
    private String descricao;
    private float valorUnitario;


    public String getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public Produto(String descricao, String quantidadeMinima) {
        this.descricao = descricao;
        this.quantidadeMinima = quantidadeMinima;
    }
}