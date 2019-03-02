package process.dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import org.ksoap2.serialization.SoapPrimitive;

import process.urls.URLs;
import process.model.Processo;
import process.model.Solicitacao;

/**
 * Created by Jhon on 22/05/2015.
 */
public class SolicitacaoDAO {
    private static final String posURLSolicitacaoDAO = "SolicitacaoDAO?wsdl";
    private static final String getAllSolicitation = "getAllSolicitation";
    private static final String insert = "insert";

    public ArrayList<Solicitacao> getAllSolicitation(Long regOAB) {
        SoapObject soapObject = new SoapObject(URLs.getNameSpace(), getAllSolicitation);
        soapObject.addProperty("regOAB", regOAB);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapObject);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLSolicitacaoDAO);
        ArrayList<Solicitacao> arrayList = new ArrayList();

        try {
            http.call("urn:" + getAllSolicitation, soapEnvelope);
            Vector<SoapObject> response = (Vector<SoapObject>) soapEnvelope.getResponse();
            if (response == null) return null;
            for (SoapObject item : response) {
                arrayList.add(new Solicitacao(Long.parseLong(item.getProperty("regOAB").toString()),
                                            item.getProperty("requerente").toString(),
                                            item.getProperty("dataSolicitacao").toString(),
                                            item.getProperty("descricao").toString(),
                                            item.getProperty("tipoAcao").toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
        return arrayList;
    }
}
