package process.dao;


import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.//<editor-fold defaultstate="collapsed" desc="Testando isso que não sei nem o que é...">
        ksoap2
//</editor-fold>
.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import process.model.Advogado;
import process.model.Usuario;
import process.urls.URLs;

public class AdvogadoDAO {
    private static final String posURLpessoaDao = "AdvogadoDAO?wsdl";
    private static final String autenticarUser = "autenticar";
    private static final String getAdvogado = "getAdvogado";
    
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
        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
            return false;
        } 
    }
    
     public Advogado getAdvogado(String login) {
        SoapObject soapObject = new SoapObject(URLs.getNameSpace(), getAdvogado);
        soapObject.addProperty("login", login);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);
        envelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        try {
            http.call("urn:" + getAdvogado, envelope);
            SoapObject response = (SoapObject) envelope.getResponse();
            return (new Advogado(response.getProperty("login").toString(),
                    response.getProperty("senha").toString(),
                    response.getProperty("nome").toString(),
                    response.getProperty("telefone").toString(),
                    response.getProperty("email").toString(),
                    response.getProperty("endereco").toString(),
                    Long.parseLong(response.getProperty("regOAB").toString())));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
    }
}