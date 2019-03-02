package com.example.jhon.appprocessos;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
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
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.processos.dao.ProcessoDAO;
import br.com.processos.model.Advogado;
import br.com.processos.model.Processo;
import br.com.processos.model.Usuario;


public class MyProcessActivity extends Activity {

    private TabHost tabHostProc;
    private ListView [] listsView;
    private ArrayList<Processo>[] arrayLists;
    static private Processo processo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_process);

        tabHostProc = (TabHost) findViewById(R.id.tabHostProc);
        tabHostProc.setup();
        TabHost.TabSpec tabSpec;

        tabSpec = tabHostProc.newTabSpec("active");
        tabSpec.setContent(R.id.tabProcActive);
        tabSpec.setIndicator("Ativo");
        tabHostProc.addTab(tabSpec);

        tabSpec = tabHostProc.newTabSpec("pendentes");
        tabSpec.setContent(R.id.tabProcPend);
        tabSpec.setIndicator("Pendente");
        tabHostProc.addTab(tabSpec);

        tabSpec = tabHostProc.newTabSpec("completos");
        tabSpec.setContent(R.id.tabProcConcluido);
        tabSpec.setIndicator("Completo");
        tabHostProc.addTab(tabSpec);

        listsView = new ListView[3];
        listsView[1] = (ListView) findViewById(R.id.listViewAtivos);
        listsView[0] = (ListView) findViewById(R.id.listViewPend);
        listsView[2] = (ListView) findViewById(R.id.listViewCompleto);

        if (Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        for(int i = 0; i < 3; i++) {
            listsView[i].setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    processo = (Processo)arg0.getAdapter().getItem(position);
                    startActivity(new Intent(MyProcessActivity.this, DetalhaProcActivity.class));
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_process, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_perfil:
                startActivity(new Intent(MyProcessActivity.this, RegisterActivity.class));
                break;
            case R.id.action_rank:
                startActivity(new Intent(MyProcessActivity.this, RankActivity.class));
                break;
            case R.id.action_new_solicitacion:
                startActivity(new Intent(MyProcessActivity.this, NewSolicActivity.class));
                break;
            case R.id.action_atualizar:
                preencheListsView();
                Toast.makeText(this, "Atualizado.", Toast.LENGTH_LONG).show();
                break;
            case R.id.action_sair:
                LoginActivity.deslogar();
                startActivity(new Intent(MyProcessActivity.this, LoginActivity.class));
                finish();
            case R.id.action_fechar:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        preencheListsView();
    }

    public void preencheListsView() {
        arrayLists = (new ProcessoDAO()).getAllProcess(LoginActivity.getUsuerLogado());
        if (arrayLists == null) return;
        for (int i = 0; i < 3; i++) {
            if(arrayLists[i] != null)
                listsView[i].setAdapter(new ArrayAdapter<Processo>(this, android.R.layout.simple_list_item_1, arrayLists[i]));
        }
    }

    static public Processo getProcesso() { return  processo;}
}
