package br.sk.loja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.sk.loja.activities.ActivityListarProdutos;
import br.sk.loja.activities.ActivityProduto;
import br.sk.loja.activities.ActivityRelatorio;
import br.sk.loja.activities.ActivityVenda;
import br.sk.loja.controller.ProdutoCtrl;
import br.sk.loja.dbHelper.ConexaoSQLite;
import br.sk.loja.model.Produto;

public class MainActivity extends AppCompatActivity {

    private Button btCadastroProdutos;
    private Button btListarProdutos;
    private Button btVenderProdutos;
    private Button btRelatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexaoSQLite.getInstance(this);

        /*this.btRelatorio= (Button) findViewById(R.id.btRelatorio);*/
        this.btVenderProdutos = (Button) findViewById(R.id.btVenderProdutos);
        this.btListarProdutos = (Button) findViewById(R.id.btListarProdutos);
        this.btCadastroProdutos = (Button) findViewById(R.id.btCadastroProduto);
        this.btCadastroProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //executado ao clicar no button

                Intent intent = new Intent(MainActivity.this, ActivityProduto.class);
                startActivity(intent);

            }
        });

        this.btListarProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //executado ao clicar no button

                Intent intent = new Intent(MainActivity.this, ActivityListarProdutos.class);
                startActivity(intent);

            }
        });

        this.btVenderProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //executado ao clicar no button

                Intent intent = new Intent(MainActivity.this, ActivityVenda.class);
                startActivity(intent);
            }
        });

        /* this.btRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //executado ao clicar no button

                Intent intent = new Intent(MainActivity.this, ActivityRelatorio.class);
                startActivity(intent);
            }
        });*/

    }
}