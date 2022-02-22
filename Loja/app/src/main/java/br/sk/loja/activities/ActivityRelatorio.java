package br.sk.loja.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import br.sk.loja.R;
import br.sk.loja.adapter.AdapterListaVendas;
import br.sk.loja.controller.VendaCtrl;
import br.sk.loja.dbHelper.ConexaoSQLite;
import br.sk.loja.model.Venda;

public class ActivityRelatorio extends AppCompatActivity {

    private ListView lsvVendas;
    private List<Venda> vendaList;
    private AdapterListaVendas adpListaVendas;
    private VendaCtrl vendaCtrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        this.vendaCtrl = new VendaCtrl(ConexaoSQLite.getInstance(ActivityRelatorio.this));

        vendaList = this.vendaCtrl.listarVendasCtrl();

        this.lsvVendas = (ListView) findViewById(R.id.lsvMinhasVendas);

        this.adpListaVendas = new AdapterListaVendas(ActivityRelatorio.this, vendaList);

        this.lsvVendas.setAdapter(this.adpListaVendas);

    }
}