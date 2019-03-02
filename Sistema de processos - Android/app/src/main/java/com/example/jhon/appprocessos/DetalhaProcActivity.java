package com.example.jhon.appprocessos;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import br.com.processos.model.Processo;


public class DetalhaProcActivity extends Activity {

    private Processo proc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalha_proc);

        proc = MyProcessActivity.getProcesso();

        ((TextView) findViewById(R.id.textViewNumero)).setText(new String().valueOf(proc.getNumero()));
        ((TextView) findViewById(R.id.textViewReg)).setText(new String().valueOf(proc.getRegAdvogado()));
        ((TextView) findViewById(R.id.textViewRequerido)).setText(proc.getRequerido());
        ((TextView) findViewById(R.id.textViewTipo)).setText(proc.getTipo());
        ((EditText) findViewById(R.id.editTextDes)).setText(proc.getDescricao());
        ((TextView) findViewById(R.id.textViewDtI)).setText(proc.getDataInicial());
        ((TextView) findViewById(R.id.textViewDtF)).setText(proc.getDataFinal());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalha_proc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}