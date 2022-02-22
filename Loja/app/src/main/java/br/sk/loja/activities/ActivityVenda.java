package br.sk.loja.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.sk.loja.R;
import br.sk.loja.adapter.AdapterItensDoCarrinho;
import br.sk.loja.controller.ProdutoCtrl;
import br.sk.loja.controller.VendaCtrl;
import br.sk.loja.dbHelper.ConexaoSQLite;
import br.sk.loja.model.ItemDoCarrinho;
import br.sk.loja.model.Produto;
import br.sk.loja.model.Venda;

public class ActivityVenda extends AppCompatActivity {

    private Spinner spnProdutos;
    private List<Produto> listaProduto;
    private ProdutoCtrl produtoCtrl;
    private EditText quantidadeItem;
    private TextView txTotalVenda;

    //carrinho de compras
    private ListView lsvCarrinhoCompras;
    private List<ItemDoCarrinho> listaItensDoCarrinho;
    private AdapterItensDoCarrinho adpItemDoCarrinho;

    //controllers
    private VendaCtrl vendaCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        this.vendaCtrl = new VendaCtrl(ConexaoSQLite.getInstance(this));

        //utilizando o spinner
        this.produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstance(this));
        this.listaProduto = this.produtoCtrl.getListaProdutosCtrl();
        this.spnProdutos = (Spinner) this.findViewById(R.id.spnProduto);
        ArrayAdapter<Produto> spnProdutoAdapter = new ArrayAdapter<Produto>(
                this,
                android.R.layout.simple_spinner_item,
                this.listaProduto);
        this.spnProdutos.setAdapter(spnProdutoAdapter);
        //final do spinner

        this.quantidadeItem = (EditText) this.findViewById(R.id.edQuantidadeProduto);
        this.txTotalVenda = (TextView) this.findViewById(R.id.txTotalVenda);

        //variaveis e objetos do carrinho de compras
        this.lsvCarrinhoCompras = (ListView) this.findViewById(R.id.lsvProdutos);
        this.listaItensDoCarrinho = new ArrayList<>();
        this.adpItemDoCarrinho = new AdapterItensDoCarrinho(ActivityVenda.this, this.listaItensDoCarrinho);
        this.lsvCarrinhoCompras.setAdapter(this.adpItemDoCarrinho);

        this.lsvCarrinhoCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {

                final ItemDoCarrinho itemDoCarrinho = (ItemDoCarrinho) adpItemDoCarrinho.getItem(posicao);

                AlertDialog.Builder janelaDeEscolha = new AlertDialog.Builder(ActivityVenda.this);
                janelaDeEscolha.setTitle("Escolha:");
                janelaDeEscolha.setMessage("Deseja remover o item " + itemDoCarrinho.getNome() + "?");

                janelaDeEscolha.setNegativeButton("Não", null);

                janelaDeEscolha.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        boolean excluiu = false;

                        adpItemDoCarrinho.removerItemDoCarrinho(posicao);

                        double totalVenda = calcularTotalVenda(listaItensDoCarrinho);
                        atualizarValorTotalVenda(totalVenda);

                        if(!excluiu){
                            Toast.makeText(ActivityVenda.this, "Item removido do carrinho", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                janelaDeEscolha.create().show();

            }
        });

    }

    //clique no botao para finalizar a venda
    public void eventFecharVenda(View view){

        Venda vendaFechada = this.criarVenda();
        if(this.salvarVenda(vendaFechada) == true){
            Toast.makeText(this, "Venda finalizada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Venda não finalizada", Toast.LENGTH_SHORT).show();
        }

    }

    //clique no botao de adicionar produtos no carrinho
    public void eventAddProduto(View view){

        ItemDoCarrinho itemDoCarrinho = new ItemDoCarrinho();

        Produto produtoSelecionado = (Produto) this.spnProdutos.getSelectedItem();
        int quantidadeProduto = 0;

        if(this.quantidadeItem.getText().toString().equals("")) {
            quantidadeProduto = 1;
        }else{
            quantidadeProduto = Integer.parseInt(this.quantidadeItem.getText().toString());
        }
        itemDoCarrinho.setNome(produtoSelecionado.getNome());
        itemDoCarrinho.setIdProduto(produtoSelecionado.getId());
        itemDoCarrinho.setQuantidadeSelecionada(quantidadeProduto);
        itemDoCarrinho.setPrecoProduto(produtoSelecionado.getPreco());
        itemDoCarrinho.setPrecoItemDaVenda(itemDoCarrinho.getPrecoProduto() * itemDoCarrinho.getQuantidadeSelecionada());

        this.adpItemDoCarrinho.addItemDoCarrinho(itemDoCarrinho);

        double totalVenda = this.calcularTotalVenda(this.listaItensDoCarrinho);
        this.atualizarValorTotalVenda(totalVenda);

    }

    private double calcularTotalVenda(List<ItemDoCarrinho> pListaItensDoCarrinho){
        double totalVenda = 0.0d;

        for (ItemDoCarrinho itemDoCarrinho: pListaItensDoCarrinho) {
            totalVenda += itemDoCarrinho.getPrecoItemDaVenda();
        }
        return totalVenda;
    }

    private void atualizarValorTotalVenda(double pValorTotal){
        this.txTotalVenda.setText(String.valueOf(pValorTotal));
    }

    private Venda criarVenda(){
        Venda venda = new Venda();
        venda.setDataDaVenda(new Date());
        venda.setItensDaVenda(this.listaItensDoCarrinho);

        return venda;
    }

    private boolean salvarVenda(Venda pVenda){

        long idVenda = vendaCtrl.salvarVendaCtrl(pVenda);

        if(idVenda > 0){
            pVenda.setId(idVenda);
            if(vendaCtrl.salvarItensVendaCtrl(pVenda)){
                Toast.makeText(this, "Venda realizada", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Venda não deu certo.", Toast.LENGTH_SHORT).show();
            }
            return true;
        }

        return false;
    }

}