package valdircamargo.com.br.projetofinalv001.database;

/**
 * Created by Valdir De Camargo on 13/12/2017.
 */

//Classe que vai conter o metodo com as instruções SqL
public class ScriptDB {

    //Metodo com as  instruçoes de criaçao do banco de dados concatenados com a Classe StringBuilder
    public  static String getCreateTableCliente() {

        //Declarando e chamando a classe StringBuilder para concatenar Strings
        StringBuilder sql = new StringBuilder();

        //Recebendo a String e concatenando
        sql.append(" CREATE TABLE IF NOT EXISTS CLIENTE (");
        sql.append("       ID_CLI   INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL, " );
        sql.append("       NOME     VARCHAR (250) NOT NULL DEFAULT (''), " );
        sql.append("       CPF      VARCHAR (15)  NOT NULL DEFAULT (''), " );
        sql.append("       IDADE    INTEGER       NOT NULL DEFAULT (''), " );
        sql.append("       TELEFONE VARCHAR (45)  NOT NULL DEFAULT (''), " );
        sql.append("       EMAIL    VARCHAR (200) NOT NULL DEFAULT ('') ) ");

        //Retorna a String sql a ser executada
        return sql.toString();

    }
}
