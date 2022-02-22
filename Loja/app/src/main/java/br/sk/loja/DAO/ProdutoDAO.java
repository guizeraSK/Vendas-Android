package br.sk.loja.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.sk.loja.dbHelper.ConexaoSQLite;
import br.sk.loja.model.Produto;

public class ProdutoDAO {

    private final ConexaoSQLite conexaoSQLite;

    public ProdutoDAO(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    public long salvarProdutoDAO(Produto pProduto){

        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            values.put("ID", pProduto.getId());
            values.put("NOME", pProduto.getNome());
            values.put("QUANTIDADE", pProduto.getQuantidadeEmEstoque());
            values.put("PRECO", pProduto.getPreco());

            long idProdutoInserido = db.insert("produto", null, values);

            return idProdutoInserido;

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(db != null){
                db.close();
            }
        }
        return 0;

    }

    public List<Produto> getListaProdutosDAO(){
        List<Produto> listaProdutos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        String query = "SELECT * FROM produto;";
        try{
            db = this.conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                Produto produtoTemporario = null;
            do {
                produtoTemporario = new Produto();
                produtoTemporario.setId(cursor.getLong(0));
                produtoTemporario.setNome(cursor.getString(1));
                produtoTemporario.setQuantidadeEmEstoque(cursor.getInt(2));
                produtoTemporario.setPreco(cursor.getDouble(3));

                listaProdutos.add(produtoTemporario);

            }while (cursor.moveToNext());
        }
    }catch (Exception e){
            Log.d("ERRO LISTA PRODUTOS", "Erro ao retornar os produtos");
            return null;
        }finally {
            if (db != null){
                db.close();
            }
        }
        return  listaProdutos;
    }

    public boolean excluirProdutoDAO(long pIdProduto){
        SQLiteDatabase db = null;
        try{
            db = this.conexaoSQLite.getWritableDatabase();
            db.delete(
                    "produto",
                    "id = ?",
                    new String[]{String.valueOf(pIdProduto)}
            );
        }catch (Exception e){
            Log.d("PRODUTODAO", "Não foi possível deletar o produto");
            return false;
        }finally {
            if (db != null){
                db.close();
            }
        }
        return true;
    }


}
