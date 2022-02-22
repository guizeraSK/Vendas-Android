package br.sk.loja.controller;

import java.util.List;

import br.sk.loja.DAO.VendaDAO;
import br.sk.loja.dbHelper.ConexaoSQLite;
import br.sk.loja.model.Venda;

public class VendaCtrl {

    private final VendaDAO vendaDAO;

    public VendaCtrl(ConexaoSQLite pConexaoSQLite){
        vendaDAO = new VendaDAO(pConexaoSQLite);
    }
    public long salvarVendaCtrl(Venda pVenda){
        return vendaDAO.salvarVendaDAO(pVenda);
    }

    public boolean salvarItensVendaCtrl(Venda pVenda){
        return vendaDAO.salvarItensDaVendaDAO(pVenda);
    }

    public List<Venda> listarVendasCtrl(){
        return vendaDAO.listarVendasDAO();
    }

}

