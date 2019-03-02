package com.example.jhon.appprocessos;

import android.app.Activity;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.processos.dao.AdvogadoDAO;
import br.com.processos.dao.TipoAcaoDAO;
import br.com.processos.model.AdvogadoRank;
import br.com.processos.model.TipoAcao;


public class RankActivity extends Activity {

    private String areaAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        if (Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ((Spinner) findViewById(R.id.spinnerArea)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carregaRank(((Spinner) findViewById(R.id.spinnerArea)).getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                carregaRank("");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if( item.getItemId() == R.id.action_rankGeral)
            carregaRank("");
        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();

        try {
            ArrayList<TipoAcao> arrayList = (new TipoAcaoDAO()).getAllTypes();
            if (arrayList != null) arrayList.set(0, new TipoAcao("", ""));
            else return;
            ((Spinner) findViewById(R.id.spinnerArea)).setAdapter((new ArrayAdapter<TipoAcao>(this,
                    android.R.layout.simple_list_item_1, arrayList)));
            carregaRank("");
        } catch (Exception e) {
            Toast.makeText(RankActivity.this, "Erro na conexão com a internet.", Toast.LENGTH_LONG).show();
        }
    }

    public void carregaRank(String area) {
        try {
            if(areaAnterior == area) return;
            ArrayList<AdvogadoRank> arrayList = (new AdvogadoDAO()).getRank(area);

            if(arrayList == null) {
                ((ListView) findViewById(R.id.listViewRank)).setAdapter(null);
            } else {
                ((ListView) findViewById(R.id.listViewRank)).setAdapter(new ArrayAdapter<AdvogadoRank>(this,
                        android.R.layout.simple_list_item_1, arrayList));
                areaAnterior = area;
            }
        } catch (Exception e) {
            Toast.makeText(RankActivity.this, "Erro na conexão com a internet.", Toast.LENGTH_LONG).show();
        }
    }
}
