package com.example.jhon.appprocessos;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;
import java.util.List;

import br.com.processos.dao.PessoaDAO;
import br.com.processos.model.Usuario;


public class LoginActivity extends Activity {

    private String strLogin;
    private String strSenha;
    static private String usuarioLogado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //botão cadastre-se
        ((Button) findViewById(R.id.email_register_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        //Login
        ((Button) findViewById(R.id.email_sign_in_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                strLogin = ((EditText) findViewById(R.id.email)).getText().toString();
                strSenha = ((EditText) findViewById(R.id.password)).getText().toString();
                if (new PessoaDAO().autenticar(new Usuario(strLogin, strSenha))) {
                    usuarioLogado = strLogin;
                    startActivity(new Intent(LoginActivity.this, MyProcessActivity.class));
                    finish();
                }
                else
                    Toast.makeText(LoginActivity.this, "Login/senha incorreto ou erro de conexão.", Toast.LENGTH_SHORT).show();
            }
        });

    }
    static public String getUsuerLogado() { return usuarioLogado; }
    static public void deslogar() { usuarioLogado = ""; }
}

