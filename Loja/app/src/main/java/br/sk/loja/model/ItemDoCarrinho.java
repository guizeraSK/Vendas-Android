package br.sk.loja.model;

public class ItemDoCarrinho {

    private long id;
    private String nome;
    private int quantidadeSelecionada;
    private long idProduto;
    private double precoProduto;
    private double precoItemDaVenda;  //precoItemDaVenda = quantidade * preçoProduto


    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeSelecionada() {
        return quantidadeSelecionada;
    }

    public void setQuantidadeSelecionada(int quantidadeSelecionada) {
        this.quantidadeSelecionada = quantidadeSelecionada;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public double getPrecoItemDaVenda() {
        return precoItemDaVenda;
    }

    public void setPrecoItemDaVenda(double precoItemDaVenda) {
        this.precoItemDaVenda = precoItemDaVenda;
    }
}
