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
import br.com.processos.model.Advogado;
import br.com.processos.model.AdvogadoRank;

/**
 * Created by Jhon on 24/05/2015.
 */
public class AdvogadoDAO {
    protected static final String posURLpessoaDao;
    protected static final String getAllAdvogados;

    static {
        getAllAdvogados = "getAllAdvogados";
        posURLpessoaDao = "AdvogadoDAO?wsdl";
    }

    private static final String getRank = "getRank";

    public ArrayList<Advogado> getAllAdvogados() {
        SoapObject soapGetAllUsu = new SoapObject(URLs.getNameSpace(), getAllAdvogados);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapGetAllUsu);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        ArrayList<Advogado> listAdvogados = new ArrayList<Advogado>();

        try {
            http.call("urn:" + getAllAdvogados, soapEnvelope);
            Vector<SoapObject> response = (Vector<SoapObject>) soapEnvelope.getResponse();
            if (response == null) return null;
            for (SoapObject item : response) {
                listAdvogados.add(new Advogado(item.getProperty("login").toString(),
                                            item.getProperty("senha").toString(),
                                            item.getProperty("nome").toString(),
                                            item.getProperty("endereco").toString(),
                                            item.getProperty("email").toString(),
                                            item.getProperty("telefone").toString(),
                                            Long.parseLong(item.getProperty("regOAB").toString())));
            }
        } catch (IOException e) {
            if(e != null) e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            if(e != null) e.printStackTrace();
            return null;
        }
        return listAdvogados;
    }

    public ArrayList<AdvogadoRank> getRank(String strArea) {
        SoapObject soapRank = new SoapObject(URLs.getNameSpace(), getRank);
        soapRank.addProperty("Area", strArea);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapRank);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        ArrayList<AdvogadoRank> listRank = new ArrayList<AdvogadoRank>();

        try {
            http.call("urn:" + getRank, soapEnvelope);
            Vector<SoapObject> response = (Vector<SoapObject>) soapEnvelope.getResponse();
            if (response == null) return null;
            for (SoapObject item : response) {
                listRank.add(new AdvogadoRank(Long.parseLong(item.getProperty("regOAB").toString()),
                        item.getProperty("nome").toString(),
                        Integer.parseInt(item.getProperty("qtdeProc").toString()),
                        Integer.parseInt(item.getProperty("posRank").toString())));
            }
        } catch (Exception e) {
            if(e != null) e.printStackTrace();
            return null;
        }
        return listRank;
    }
}
