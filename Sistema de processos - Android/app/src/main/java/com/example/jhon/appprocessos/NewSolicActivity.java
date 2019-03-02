package com.example.jhon.appprocessos;

import android.app.Activity;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.processos.Util.Util;
import br.com.processos.dao.AdvogadoDAO;
import br.com.processos.dao.SolicitacaoDAO;
import br.com.processos.dao.TipoAcaoDAO;
import br.com.processos.model.Advogado;
import br.com.processos.model.Processo;
import br.com.processos.model.Solicitacao;
import br.com.processos.model.TipoAcao;


public class NewSolicActivity extends Activity {

    Spinner spinnerTipoAcao;
    Spinner spinnerAdvogado;
    EditText editTextDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_solic);

        if (Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            spinnerTipoAcao = (Spinner) findViewById(R.id.spinnerTipoAcao);
            spinnerTipoAcao.setAdapter(new ArrayAdapter<TipoAcao>(this, android.R.layout.simple_list_item_1, (new TipoAcaoDAO()).getAllTypes()));
            spinnerAdvogado = (Spinner) findViewById(R.id.spinnerAdvogado);
            spinnerAdvogado.setAdapter(new ArrayAdapter<Advogado>(this, android.R.layout.simple_list_item_1, (new AdvogadoDAO()).getAllAdvogados()));

            editTextDesc = (EditText) findViewById(R.id.editTextDesc);

        } catch (Exception e) {
            e.printStackTrace();
        }

        ((Button) findViewById(R.id.buttonEnviar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((new SolicitacaoDAO()).insert(new Solicitacao(Util.separaCodigo(spinnerAdvogado.getSelectedItem().toString()),
                        LoginActivity.getUsuerLogado(), "", ((EditText) findViewById(R.id.editTextDesc)).getText().toString(),
                        spinnerTipoAcao.getSelectedItem().toString()))) {
                    Toast.makeText(NewSolicActivity.this, "Solicitação enviada com sucesso.", Toast.LENGTH_LONG).show();
                    finish();
                } else
                    Toast.makeText(NewSolicActivity.this, "Erro ao enviar sua solicitação. Verifique conexão com a internet.", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_solic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
