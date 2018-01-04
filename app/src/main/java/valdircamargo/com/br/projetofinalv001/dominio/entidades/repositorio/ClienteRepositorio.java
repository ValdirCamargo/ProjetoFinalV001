package valdircamargo.com.br.projetofinalv001.dominio.entidades.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import valdircamargo.com.br.projetofinalv001.dominio.entidades.Cliente;

/**
 * Created by Valdir De Camargo on 13/12/2017.
 */

public class ClienteRepositorio {

    //Declara a variavel de conexao
    private SQLiteDatabase conexao;

    //passa a conexao para clienteRepositorio
    public ClienteRepositorio (SQLiteDatabase conexao) {
        this.conexao = conexao;
    }

    //Criando o meotodo de insert
    public void inserir(Cliente cliente){

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

        //Cria um array de uma posição para guardar somente o id_cliente
        String[] parametros = new String[1];
        parametros[0]=String.valueOf(id_cliente);

        conexao.delete("CLIENTE","ID_CLI = ?",parametros);

    }

    //Metodo para lista todos os clientes
    public List<Cliente> buscarTodos(){

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

    // conta o numero de linhas no bd
    public int getContactsCount() {

        Cursor dataCount = conexao.rawQuery("SELECT ID_CLI FROM CLIENTE", null);

        int count = dataCount.getCount();
        dataCount.close();
        conexao.close();

        return count;
    }
}
