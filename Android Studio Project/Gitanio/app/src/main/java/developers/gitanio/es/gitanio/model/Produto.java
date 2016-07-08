package developers.gitanio.es.gitanio.model;

public class Produto {

    private int codigo;
    private int quantidadeMinima;
    private String descricao;
    private float valorUnitario;


    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public Produto(int codigo, String descricao, int quantidadeMinima, float valorUnitario) {
        this.descricao = descricao;
        this.quantidadeMinima = quantidadeMinima;
        this.codigo = codigo;
        this.valorUnitario = valorUnitario;
    }
}