package valdircamargo.com.br.projetofinalv001.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import valdircamargo.com.br.projetofinalv001.dominio.entidades.Cliente;

/**
 * Created by Valdir De Camargo on 13/12/2017.
 */

public class ClientOpenHelper  extends SQLiteOpenHelper{

    public static final String TABLE_NAME = "CLIENTE";

    public ClientOpenHelper(Context context) {
        super(context,"CLIENT",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptDB.getCreateTableCliente());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);

    }

    //Criando o meotodo de insert
    public void inserir(Cliente cliente){

        SQLiteDatabase conexao = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME",cliente.nome);
        contentValues.put("CPF",cliente.cpf);
        contentValues.put("IDADE",cliente.idade);
        contentValues.put("TELEFONE",cliente.telefone);
        contentValues.put("EMAIL",cliente.email);

        conexao.insertOrThrow("CLIENTE",null,contentValues);
    }

    //Criando o metodo de DELET
    public  void excluir(int id_cliente){

        SQLiteDatabase conexao = this.getWritableDatabase();
        //Cria um array de uma posição para guardar somente o id_cliente
        String[] parametros = new String[1];
        parametros[0]=String.valueOf(id_cliente);

        conexao.delete("CLIENTE","ID_CLI = ?",parametros);

    }

    public Cursor getList(){
        SQLiteDatabase conexao = this.getWritableDatabase();
        Cursor data = conexao.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }

    //Metodo para lista todos os clientes
    public List<Cliente> buscarTodos(){

        SQLiteDatabase conexao = this.getWritableDatabase();
        List<Cliente> clientes = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM CLIENTE");

        Cursor res = conexao.rawQuery(sql.toString(),null);

        if (res.moveToFirst()){
            do {

                Cliente cli = new Cliente();

                cli.id_cliente = res.getInt(res.getColumnIndexOrThrow("ID_CLI"));
                cli.nome = res.getString(res.getColumnIndexOrThrow("NOME"));
                cli.cpf = res.getString(res.getColumnIndexOrThrow("CPF"));
                cli.idade = res.getInt(res.getColumnIndexOrThrow("IDADE"));
                cli.telefone = res.getString(res.getColumnIndexOrThrow("TELEFONE"));
                cli.email = res.getString(res.getColumnIndexOrThrow("EMAIL"));
            }while (res.moveToNext());
        }
        return clientes;
    }
}
