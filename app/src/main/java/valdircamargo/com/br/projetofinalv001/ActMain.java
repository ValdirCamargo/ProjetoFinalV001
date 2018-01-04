package valdircamargo.com.br.projetofinalv001;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import valdircamargo.com.br.projetofinalv001.dominio.entidades.repositorio.ClienteRepositorio;

public class ActMain extends AppCompatActivity {

    private Button btnCad;
    private Button btnLista;
    private ClienteRepositorio db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        btnCad = (Button) findViewById(R.id.btnCad);
        btnLista = (Button) findViewById(R.id.btnLis);
        btnCad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent it = new Intent(ActMain.this, ActFormCad.class);
                startActivity(it);
            }
        });

        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActMain.this, ViewContentes.class);
                startActivity(intent);
            }
        });

    }
}
