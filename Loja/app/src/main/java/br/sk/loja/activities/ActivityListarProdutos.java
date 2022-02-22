package br.sk.loja.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.sk.loja.R;
import br.sk.loja.adapter.AdapterListaProduto;
import br.sk.loja.controller.ProdutoCtrl;
import br.sk.loja.dbHelper.ConexaoSQLite;
import br.sk.loja.model.Produto;

public class ActivityListarProdutos extends AppCompatActivity {

    private ListView lsvProdutos;
    private List<Produto> produtoList;
    private AdapterListaProduto adapterListaProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_produtos);

        //to do BUSCAR PRODUTOS DO BANCO

        ProdutoCtrl produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstance(ActivityListarProdutos.this));
        produtoList = produtoCtrl.getListaProdutosCtrl();

        this.lsvProdutos = (ListView) findViewById(R.id.lsvProdutos);

        this.adapterListaProduto = new AdapterListaProduto(ActivityListarProdutos.this, this.produtoList);

        this.lsvProdutos.setAdapter(this.adapterListaProduto);

        this.lsvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Produto produtoSelecionado = (Produto) adapterListaProduto.getItem(posicao);
                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(ActivityListarProdutos.this);

                janelaDeEscolha.setTitle("Escolha:");
                janelaDeEscolha.setMessage("O que deseja fazer com o produto selecionado?");
                janelaDeEscolha.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                janelaDeEscolha.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        boolean exclui = produtoCtrl.excluirProdutoCtrl(produtoSelecionado.getId());
                        dialog.cancel();
                        if (exclui == true){
                            adapterListaProduto.removerProduto(posicao);
                            Toast.makeText(ActivityListarProdutos.this, "Produto excluído com sucesso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ActivityListarProdutos.this, "Erro ao excluir produto", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                janelaDeEscolha.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                    }
                });


                janelaDeEscolha.create().show();
            }
        });

    }
}