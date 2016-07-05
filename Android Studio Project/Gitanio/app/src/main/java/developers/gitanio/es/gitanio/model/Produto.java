package developers.gitanio.es.gitanio.model;

public class Produto {

    private String nome;
    private String quantidade;
    private String descricao;
    private String foto;

    public Produto(String nome, String descricao , String foto, String quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.quantidade = quantidade;
    }

    public String getQuantidade() {
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