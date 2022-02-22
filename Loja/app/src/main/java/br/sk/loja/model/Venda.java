package br.sk.loja.model;

import java.util.Date;
import java.util.List;

public class Venda {

    private long id;

    private Date dataDaVenda;
    private String nomeCliente; //esquece kkkk
    private List<ItemDoCarrinho> itensDaVenda;
    private double totalVenda;
    private int qteItensVenda;

    public Venda() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getDataDaVenda() {
        return dataDaVenda;
    }
    public void setDataDaVenda(Date dataDaVenda) {
        this.dataDaVenda = dataDaVenda;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public List<ItemDoCarrinho> getItensDaVenda() {
        return itensDaVenda;
    }

    public void setItensDaVenda(List<ItemDoCarrinho> itensDaVenda) {
        this.itensDaVenda = itensDaVenda;
    }

    public double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public int getQteItensVenda() {
        return qteItensVenda;
    }

    public void setQteItensVenda(int qteItensVenda) {
        this.qteItensVenda = qteItensVenda;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataDaVenda=" + dataDaVenda +
                ", nomeCliente='" + nomeCliente + '\'' +
                '}';
    }

}
