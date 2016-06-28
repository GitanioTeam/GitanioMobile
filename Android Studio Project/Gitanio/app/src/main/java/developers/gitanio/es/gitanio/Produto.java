package developers.gitanio.es.gitanio;

public class Produto {

    private String nome;
    private String descricao;
    private String foto;
    private int quantidade;

    public Produto(String nome, String descricao, String foto, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFoto() {
        return foto;
    }
}