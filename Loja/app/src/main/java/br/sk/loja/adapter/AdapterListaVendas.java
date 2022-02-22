package br.sk.loja.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.sk.loja.R;
import br.sk.loja.model.Produto;
import br.sk.loja.model.Venda;

public class AdapterListaVendas extends BaseAdapter {

    private Context context;
    private List<Venda> vendaList;

    public AdapterListaVendas(Context context, List<Venda> vendaList) {
        this.context = context;
        this.vendaList = vendaList;
    }

    @Override
    public int getCount() {
        return this.vendaList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.vendaList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_relatorio, null);
        TextView txData = (TextView) v.findViewById(R.id.txData);
        TextView txTotalVenda = (TextView) v.findViewById(R.id.txTotalVenda);
        TextView txQuantidadeItens = (TextView) v.findViewById(R.id.txQuantidadeItens);

        txData.setText(this.vendaList.get(posicao).getDataDaVenda().toString());
        txTotalVenda.setText(String.valueOf(this.vendaList.get(posicao).getTotalVenda()));
        txQuantidadeItens.setText(String.valueOf(this.vendaList.get(posicao).getQteItensVenda()));

        return v;
    }

    public void atualizar(List<Venda> pVendas){
        this.vendaList.clear();
        this.vendaList = pVendas;
        this.notifyDataSetChanged();
    }
}
