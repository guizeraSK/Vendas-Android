package br.sk.loja.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.sk.loja.R;
import br.sk.loja.controller.ProdutoCtrl;
import br.sk.loja.dbHelper.ConexaoSQLite;
import br.sk.loja.model.Produto;

public class ActivityProduto extends AppCompatActivity {

    private Produto produto;
    private EditText edNomeProduto;
    private EditText edQuantidadeProduto;
    private EditText edPreco;
    private EditText edIdProduto; //Código de Barras do produto

    private Button btSalvarProduto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        btSalvarProduto = (Button) findViewById(R.id.btSalvarProduto);
        edNomeProduto = (EditText) findViewById(R.id.edNomeProduto);
        edQuantidadeProduto = (EditText) findViewById(R.id.edQuantidadeProduto);
        edPreco = (EditText) findViewById(R.id.edPreco);
        edIdProduto = (EditText) findViewById(R.id.edIdProduto);

        this.clickBotaoSalvarListener();

    }

    private void clickBotaoSalvarListener(){
        this.btSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto produtoACadastrar = getDadosProdutoFormulario();

                if(produtoACadastrar != null){
                    ProdutoCtrl produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstance(ActivityProduto.this));
                    long idProduto = produtoCtrl.salvarProdutoCtrl(produtoACadastrar);

                    if(idProduto > 0){
                        Toast.makeText(ActivityProduto.this,"Produto salvo com sucesso", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(ActivityProduto.this,"Produto não foi salvo", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActivityProduto.this, "Todos os campos são obrigatórios", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private Produto getDadosProdutoFormulario(){
        this.produto = new Produto();

        if(this.edIdProduto.getText().toString().isEmpty() == false){
            this.produto.setId(Long.parseLong(this.edIdProduto.getText().toString()));
        }else {
            return null;
        }
        if(this.edNomeProduto.getText().toString().isEmpty() == false){
            this.produto.setNome(this.edNomeProduto.getText().toString());
        }else{
            return null;}
        if(edQuantidadeProduto.getText().toString().isEmpty() == false){
            int quantidadeProduto = Integer.parseInt(this.edQuantidadeProduto.getText().toString());
            this.produto.setQuantidadeEmEstoque(quantidadeProduto);
        }else{
            return null;}
        if(edPreco.getText().toString().isEmpty() == false){
            double precoProduto = Double.parseDouble(this.edPreco.getText().toString());
            this.produto.setPreco(precoProduto);
        }else{
            return null;}
        return  produto;
    }
}