package br.sk.loja.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.sk.loja.R;
import br.sk.loja.model.ItemDoCarrinho;
import br.sk.loja.model.Produto;

public class AdapterItensDoCarrinho extends BaseAdapter {

    private Context context;
    private List<ItemDoCarrinho> itensDoCarrinho;

    public AdapterItensDoCarrinho(Context context, List<ItemDoCarrinho> itensDoCarrinho) {
        this.context = context;
        this.itensDoCarrinho = itensDoCarrinho;
    }

    @Override
    public int getCount() {
        return this.itensDoCarrinho.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.itensDoCarrinho.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    public boolean removerItemDoCarrinho(int posicao){
        this.itensDoCarrinho.remove(posicao);
        notifyDataSetChanged();

        return true;
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_carrinho_produtos, null);
        TextView txNomeProduto = (TextView) v.findViewById(R.id.txNomeProduto);
        TextView txPrecoProduto = (TextView) v.findViewById(R.id.txPrecoProduto);
        TextView txQuantidadeProduto = (TextView) v.findViewById(R.id.txQuantidadeProduto);
        TextView txTotalItens = (TextView) v.findViewById(R.id.txTotalItens);

        txNomeProduto.setText(this.itensDoCarrinho.get(posicao).getNome());
        txPrecoProduto.setText(String.valueOf(this.itensDoCarrinho.get(posicao).getPrecoProduto()));
        txQuantidadeProduto.setText(String.valueOf(this.itensDoCarrinho.get(posicao).getQuantidadeSelecionada()));
        txTotalItens.setText(String.valueOf(this.itensDoCarrinho.get(posicao).getPrecoItemDaVenda()));

        return v;
    }

    //add um item ao carrinho
    public void addItemDoCarrinho(ItemDoCarrinho pItemDoCarrinho){
        this.itensDoCarrinho.add(pItemDoCarrinho);
        this.notifyDataSetChanged();
    }


    //atualiza a lista do carrinho

    public void atualizar(List<ItemDoCarrinho> pItensDoCarrinho){
        this.itensDoCarrinho.clear();
        this.itensDoCarrinho = pItensDoCarrinho;
        this.notifyDataSetChanged();
    }

}
