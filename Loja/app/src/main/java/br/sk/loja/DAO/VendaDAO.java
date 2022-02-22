package br.sk.loja.DAO;

import android.content.ClipData;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.sk.loja.dbHelper.ConexaoSQLite;
import br.sk.loja.model.ItemDoCarrinho;
import br.sk.loja.model.Venda;

public class VendaDAO {

    private final ConexaoSQLite conexaoSQLite;

    public VendaDAO(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }
    public long salvarVendaDAO(Venda pVenda){
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try{

            ContentValues values = new ContentValues();
            values.put("data", pVenda.getDataDaVenda().getTime());

            long idVendaInserido = db.insert("venda", null, values);

            return idVendaInserido;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.close();
            }
        }

        return 0;
    }

    //salvando venda
    public boolean salvarItensDaVendaDAO(Venda pVenda){
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        try{

            ContentValues values = null;

            for (ItemDoCarrinho itemDaVenda : pVenda.getItensDaVenda()){

                values = new ContentValues();
                values.put("quantidade_vendida", itemDaVenda.getQuantidadeSelecionada());
                values.put("id_produto", itemDaVenda.getId());
                values.put("id_venda", pVenda.getId());
                values.put("PRECO", itemDaVenda.getPrecoItemDaVenda());

                db.insert("item_da_venda", null, values);

            }
            return  true;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.close();
            }
        }
        return  false;
    }

    public List<Venda> listarVendasDAO() {

        List<Venda> listaVendas = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query =
                "SELECT " +
                        "venda.id," +
                        "venda.data," +
                        "SUM(produto.PRECO)," +
                        "COUNT(produto.ID)" +
                        "FROM venda" +
                        "INNER JOIN item_da_venda ON (venda.id = item_da_venda.id_venda)" +
                        "INNER JOIN produto ON (item_da_venda.id_produto = produto.ID)" +
                        "GROUP BY venda.id;";

        try{

            db = this.conexaoSQLite.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst()){

                Venda vendaTemp = null;

                do {

                    vendaTemp = new Venda();
                    vendaTemp.setId(cursor.getLong(0));
                    vendaTemp.setDataDaVenda(new Date(cursor.getLong(1)));
                    vendaTemp.setTotalVenda(cursor.getDouble(2));
                    vendaTemp.setQteItensVenda(cursor.getInt(3));

                    listaVendas.add(vendaTemp);

                }while(cursor.moveToNext());
            }

        }catch (Exception e){
            Log.d("ERRO VENDAS", "Deu erro ao retornar as vendas");
            return  null;
        }

        return  listaVendas;
    }

}
