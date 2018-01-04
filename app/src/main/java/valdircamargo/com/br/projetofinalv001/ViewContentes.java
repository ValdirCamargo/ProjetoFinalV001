package valdircamargo.com.br.projetofinalv001;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import valdircamargo.com.br.projetofinalv001.database.ClientOpenHelper;

/**
 * Created by Valdir on 14/12/2017.
 */

public class ViewContentes extends AppCompatActivity {

    ClientOpenHelper myDB;
    ImageButton img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontentes_layout);

        img = findViewById(R.id.imgBack2);
        ListView listView = (ListView) findViewById(R.id.lstView);
        myDB = new ClientOpenHelper(this);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte  = new Intent(ViewContentes.this,ActMain.class);
                finish();
            }
        });

        ArrayList<String> lista = new ArrayList<>();
        Cursor data = myDB.getList();


        if (data.getCount() == 0) {
            Toast.makeText(ViewContentes.this, "Não a dados cadastrados", Toast.LENGTH_LONG).show();
        } else {
            data.moveToFirst();
            do {
                lista.add(data.getString(1));
                lista.add(data.getString(2));
                lista.add(data.getString(3));
                lista.add(data.getString(4));
                lista.add(data.getString(5));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, lista);
                listView.setAdapter(listAdapter);
            }while(data.moveToNext());
        }
    }
}
