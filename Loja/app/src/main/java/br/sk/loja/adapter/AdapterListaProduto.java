package br.sk.loja.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.sk.loja.R;
import br.sk.loja.model.Produto;

public class AdapterListaProduto extends BaseAdapter {

    private Context context;
    private List<Produto> produtoList;

    public AdapterListaProduto(Context context, List<Produto> produtoList) {
        this.context = context;
        this.produtoList = produtoList;
    }

    @Override
    public int getCount() {
        return this.produtoList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.produtoList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    public void removerProduto(int posicao){
        this.produtoList.remove(posicao);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_produto, null);
        TextView txNomeProduto = (TextView) v.findViewById(R.id.txNomeProduto);
        TextView txPrecoProduto = (TextView) v.findViewById(R.id.txPrecoProduto);
        TextView txEstoqueProduto = (TextView) v.findViewById(R.id.txEstoqueProduto);

        txNomeProduto.setText(this.produtoList.get(posicao).getNome());
        txPrecoProduto.setText(String.valueOf(this.produtoList.get(posicao).getPreco()));
        txEstoqueProduto.setText(String.valueOf(this.produtoList.get(posicao).getQuantidadeEmEstoque()));

        return v;
    }
}
