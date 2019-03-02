package br.com.processos.dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import br.com.processos.URLs.URLs;
import br.com.processos.model.Pessoa;
import br.com.processos.model.Usuario;

/**
 * Created by Jhon on 21/05/2015.
 */
public class PessoaDAO {
    private static final String posURLpessoaDao = "PessoaDAO?wsdl";
    private static final String autenticarUser = "autenticar";
    private static final String insertPessoa = "insert";
    private static final String getPessoa = "getPessoa";
    private static final String updatePessoa = "update";

    public boolean autenticar(Usuario user) {
        SoapObject soapAutenticar = new SoapObject(URLs.getNameSpace(), autenticarUser);
        SoapObject soapUsuer = new SoapObject(URLs.getNameSpace(), "user");

        soapUsuer.addProperty("login", user.getLogin());
        soapUsuer.addProperty("senha", user.getSenha());

        soapAutenticar.addSoapObject(soapUsuer);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapAutenticar);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        try {
            http.call("urn:" + autenticarUser, soapEnvelope);
            SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert(Pessoa pessoa) {
        SoapObject soapInsert = new SoapObject(URLs.getNameSpace(), insertPessoa);
        SoapObject soapPessoa = new SoapObject(URLs.getNameSpace(), "pessoa");

        soapPessoa.addProperty("login", pessoa.getLogin());
        soapPessoa.addProperty("senha", pessoa.getSenha());
        soapPessoa.addProperty("email", pessoa.getEmail());
        soapPessoa.addProperty("endereco", pessoa.getEndereco());
        soapPessoa.addProperty("nome", pessoa.getNome());
        soapPessoa.addProperty("telefone", pessoa.getTelefone());

        soapInsert.addSoapObject(soapPessoa);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapInsert);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        try {
            http.call("urn:" + insertPessoa, soapEnvelope);
            SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Pessoa pessoa) {
        SoapObject soapUpdate = new SoapObject(URLs.getNameSpace(), updatePessoa);
        SoapObject soapPessoa = new SoapObject(URLs.getNameSpace(), "pessoa");

        soapPessoa.addProperty("login", pessoa.getLogin());
        soapPessoa.addProperty("senha", pessoa.getSenha());
        soapPessoa.addProperty("email", pessoa.getEmail());
        soapPessoa.addProperty("endereco", pessoa.getEndereco());
        soapPessoa.addProperty("nome", pessoa.getNome());
        soapPessoa.addProperty("telefone", pessoa.getTelefone());

        soapUpdate.addSoapObject(soapPessoa);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapUpdate);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        try {
            http.call("urn:" + updatePessoa, soapEnvelope);
            SoapPrimitive response = (SoapPrimitive) soapEnvelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pessoa getPessoa(String login) {
        SoapObject soapGetpes = new SoapObject(URLs.getNameSpace(), getPessoa);
        soapGetpes.addProperty("login", login);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapGetpes);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        try {
            http.call("urn:" + getPessoa, envelope);
            SoapObject response = (SoapObject) envelope.getResponse();
            return (new Pessoa(response.getProperty("login").toString(),
                    response.getProperty("senha").toString(),
                    response.getProperty("nome").toString(),
                    response.getProperty("telefone").toString(),
                    response.getProperty("email").toString(),
                    response.getProperty("endereco").toString()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
    }
}
