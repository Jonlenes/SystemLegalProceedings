package br.com.processos.dao;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import br.com.processos.URLs.URLs;
import br.com.processos.model.TipoAcao;

/**
 * Created by Jhon on 24/05/2015.
 */
public class TipoAcaoDAO {
    protected static final String posURLTipoAcaoDao;
    protected static final String getAllTypes;

    static {
        posURLTipoAcaoDao = "TipoAcaoDAO?wsdl";
        getAllTypes = "getAllTypes";
    }


    public ArrayList<TipoAcao> getAllTypes() {
        SoapObject soapGetAllTypes = new SoapObject(URLs.getNameSpace(), getAllTypes);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapGetAllTypes);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLTipoAcaoDao);
        ArrayList<TipoAcao> listArea = new ArrayList<TipoAcao>();

        try {
            http.call("urn:" + getAllTypes, soapEnvelope);
            Vector<SoapObject> response = (Vector<SoapObject>) soapEnvelope.getResponse();
            if (response == null) return null;
            for (SoapObject item : response) {
                listArea.add(new TipoAcao(item.getProperty("tipo").toString(), item.getProperty("descricao").toString()));
            }
        } catch (Exception e) {
            if(e != null) e.printStackTrace();
            return null;
        }
        return listArea;
    }
}
