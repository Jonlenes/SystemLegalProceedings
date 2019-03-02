package com.example.jhon.appprocessos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.processos.dao.PessoaDAO;
import br.com.processos.model.Pessoa;
import br.com.processos.model.Processo;


public class RegisterActivity extends Activity {

    boolean isInsert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        isInsert = true;

        if(!("").equals(LoginActivity.getUsuerLogado())) setPessoa();

        //botão cadastrar
        ((Button) findViewById(R.id.email_register_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = ((EditText) findViewById(R.id.edtRegLogin)).getText().toString();
                String senha = ((EditText) findViewById(R.id.edtRegPassword)).getText().toString();
                String repitaSenha = ((EditText) findViewById(R.id.edtRegRepPassword)).getText().toString();
                String nome = ((EditText) findViewById(R.id.edtRegName)).getText().toString();
                String telefone = ((EditText) findViewById(R.id.edtRegFone)).getText().toString();
                String email = ((EditText) findViewById(R.id.edtRegEmail)).getText().toString();
                String endereco = ((EditText) findViewById(R.id.edtRegCity)).getText().toString();

                if (!senha.equals(repitaSenha))
                    Toast.makeText(RegisterActivity.this, "As senhas não são equivalentes.", Toast.LENGTH_LONG).show();
                else {
                    if((isInsert && !(new PessoaDAO()).insert(new Pessoa(login, senha, nome, telefone, email, endereco))) ||
                            (!isInsert && !(new PessoaDAO()).update(new Pessoa(login, senha, nome, telefone, email, endereco))))
                        Toast.makeText(RegisterActivity.this, "Erro de conexão.", Toast.LENGTH_LONG).show();
                    else finish();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public void setPessoa() {
        Pessoa pessoa = (new PessoaDAO()).getPessoa(LoginActivity.getUsuerLogado());

        ((EditText) findViewById(R.id.edtRegName)).setText(pessoa.getNome());
        ((EditText) findViewById(R.id.edtRegFone)).setText(pessoa.getTelefone());
        ((EditText) findViewById(R.id.edtRegEmail)).setText(pessoa.getEmail());
        ((EditText) findViewById(R.id.edtRegCity)).setText(pessoa.getEndereco());
        ((EditText) findViewById(R.id.edtRegLogin)).setText(pessoa.getLogin());
        ((EditText) findViewById(R.id.edtRegPassword)).setText(pessoa.getSenha());
        ((EditText) findViewById(R.id.edtRegLogin)).setEnabled(false);
        ((Button) findViewById(R.id.email_register_button)).setText("Atualizar");
        isInsert = false;
    }
}
