package br.sk.loja.controller;

import android.hardware.lights.LightsManager;

import java.util.List;

import br.sk.loja.DAO.ProdutoDAO;
import br.sk.loja.dbHelper.ConexaoSQLite;
import br.sk.loja.model.Produto;

public class ProdutoCtrl {

    private final ProdutoDAO produtoDAO;

    public ProdutoCtrl(ConexaoSQLite pConexaoSQLite){
        produtoDAO = new ProdutoDAO(pConexaoSQLite);
    }

    public long salvarProdutoCtrl(Produto pProduto){
        return  this.produtoDAO.salvarProdutoDAO(pProduto);
    }

    public List<Produto> getListaProdutosCtrl(){
        return this.produtoDAO.getListaProdutosDAO();
    }

    public boolean excluirProdutoCtrl(long pIdProduto){
        return this.produtoDAO.excluirProdutoDAO(pIdProduto);
    }

}
