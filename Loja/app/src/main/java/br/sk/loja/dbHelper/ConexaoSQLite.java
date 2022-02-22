package br.sk.loja.dbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "produtos_loja";
    public ConexaoSQLite(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static ConexaoSQLite getInstance(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
        return  INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase SQLiteDatabase) {

        String sqlTabelaProduto =
                "CREATE TABLE IF NOT EXISTS produto" +
                        "(" +
                        "ID INTEGER PRIMARY KEY," +
                        "NOME TEXT," +
                        "QUANTIDADE INTEGER, " +
                        "PRECO REAL" +
                        ")";
        SQLiteDatabase.execSQL(sqlTabelaProduto);

        String sqlTabelaVenda =
                "CREATE TABLE IF NOT EXISTS venda" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "data INTEGER" +
                        ")";
        SQLiteDatabase.execSQL(sqlTabelaVenda);

        String sqlTabelaItemDaVenda =
                "CREATE TABLE IF NOT EXISTS item_da_venda" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "quantidade_vendida INTEGER," +
                        "id_produto INTEGER," +
                        "id_venda INTEGER" +
                        ")";
        SQLiteDatabase.execSQL(sqlTabelaItemDaVenda);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

