package valdircamargo.com.br.projetofinalv001;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import valdircamargo.com.br.projetofinalv001.database.ClientOpenHelper;
import valdircamargo.com.br.projetofinalv001.dominio.entidades.Cliente;
import valdircamargo.com.br.projetofinalv001.dominio.entidades.repositorio.ClienteRepositorio;

public class ActFormCad extends AppCompatActivity {

    //Declarando os EditTexts
    EditText edtName ;
    EditText edtCPF  ;
    EditText edtIdade;
    EditText edtTel  ;
    EditText edtEmail;

    //Declarando os Buttons
    Button      btnCancel;
    Button      btnSave  ;
    ImageButton imgVoltar;

    private ConstraintLayout layoutMain;

    private ClienteRepositorio clienteRepositorio;

    private SQLiteDatabase conexao;

    private ClientOpenHelper clientOpenHelper;

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_form_cad);


        edtName  = (EditText)    findViewById(R.id.edtNome)    ;
        edtCPF   = (EditText)    findViewById(R.id.edtCpf)     ;
        edtIdade = (EditText)    findViewById(R.id.edtIdade)   ;
        edtTel   = (EditText)    findViewById(R.id.edtTelefone);
        edtEmail = (EditText)    findViewById(R.id.edtEmail)   ;

        imgVoltar= (ImageButton) findViewById(R.id.imgBack2)    ;
        btnSave  = (Button)      findViewById(R.id.btnSalvar)  ;
        btnCancel= (Button)      findViewById(R.id.btnCancelar);

        layoutMain = (ConstraintLayout) findViewById(R.id.layoutCadCli);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmar();
            }
        });

        imgVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        criarConexao();
    }

    //Metodo para criar uma conexao com DB
    private void criarConexao(){

        try {

            clientOpenHelper = new ClientOpenHelper(this);

            conexao = clientOpenHelper.getWritableDatabase();

            Snackbar.make(layoutMain, R.string.Aviso, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.ok,null)
                    .show();
            clienteRepositorio = new ClienteRepositorio(conexao);
        }catch (SQLException ex){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Error");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("OK",null);
            dlg.show();
        }
    }

    //Metodo para confirmar a inclusao no banco

    private void confirmar(){

        cliente = new Cliente();

        if (validaCampos() == false){

            cliente.idade = Integer.parseInt(edtIdade.getText().toString());
            try {

                clienteRepositorio.inserir(cliente);
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                finish();
                dlg.setTitle("Sucess");
                dlg.setMessage("Cadastro realizado com sucesso");
                dlg.setNeutralButton("OK",null);
                dlg.show();

            }catch (SQLException ex){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle("Error");
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton("OK",null);
                dlg.show();
            }
        }
    }

    //Metodo para validar se todos os campos estão ´preenchidos
    private boolean validaCampos(){

        boolean res = false;


        String nome     = edtName.getText().toString();
        String cpf      = edtCPF.getText().toString();
        String telefone = edtTel.getText().toString();
        String email    = edtEmail.getText().toString();

        cliente.nome     = nome;
        cliente.cpf      = cpf;
        cliente.telefone = telefone;
        cliente.email    = email;

        if (res = isCampoVazio(nome)){
            edtName.requestFocus();
        }else
            if (res = isCampoVazio(cpf)){
                edtCPF.requestFocus();
            }else
                if (res = isCampoVazio(telefone)){
                    edtTel.requestFocus();
                }else
                    if (res = !isEmailVazio(email)){
                       edtEmail.requestFocus();
                    }
        if (res){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.Aviso);
            dlg.setMessage(R.string.Aviso_msg);
            dlg.setNeutralButton(R.string.aviso_btn_ok, null);
            dlg.show();
        }
        return res;
    }

    //Metodo que valida se os campos estão preenchidos
    private boolean isCampoVazio (String valor){

        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    private boolean CampoVazio (Integer valor){

        boolean resultado = ( valor < 0 );
        return resultado;
    }

    //Metodo de validaçao de email
    private boolean isEmailVazio(String email){

        boolean resultado = (!isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

}
