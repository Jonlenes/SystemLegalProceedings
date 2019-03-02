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

/**
 * Created by Jhon on 22/05/2015.
 */
public class ProcessoDAO {
    private static final String posURLpessoaDao = "ProcessoDAO?wsdl";
    private static final String getAllProcessAdv = "getAllProcessAdv";
    private static final String insert = "insert";

    public ArrayList<Processo> getAllProcessAdv(Long regOAB) {
        SoapObject soapGetAllUsu = new SoapObject(URLs.getNameSpace(), getAllProcessAdv);
        soapGetAllUsu.addProperty("regOAB", regOAB);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapGetAllUsu);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        ArrayList<Processo> listProcess = new ArrayList();

        try {
            http.call("urn:" + getAllProcessAdv, soapEnvelope);
            Vector<SoapObject> response = (Vector<SoapObject>) soapEnvelope.getResponse();
            if (response == null) return null;
            for (SoapObject item : response) {
                Processo proc = new Processo(Long.parseLong(item.getProperty("numero").toString()),
                                            item.getProperty("requerente").toString(),
                                            item.getProperty("requerido").toString(),
                                            Long.parseLong(item.getProperty("regAdvogado").toString()),
                                            item.getProperty("dataInicial").toString(),
                                            "",
                                            Integer.parseInt(item.getProperty("status").toString()),
                                            item.getProperty("tipo").toString(),
                                            item.getProperty("descricao").toString());
                listProcess.add(proc);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return null;
        }
        return listProcess;
    }
    
        
    public boolean insert(Processo processo) {
        SoapObject soapInsert = new SoapObject(URLs.getNameSpace(), insert);
        SoapObject soapProc = new SoapObject(URLs.getNameSpace(), "processo");

        soapProc.addProperty("numero", processo.getNumero());
        soapProc.addProperty("requerente", processo.getRequerente());
        soapProc.addProperty("requerido", processo.getRequerido());
        soapProc.addProperty("regAdvogado", processo.getRegAdvogado());
        soapProc.addProperty("dataInicial", processo.getDataInicial());
        soapProc.addProperty("dataFinal", processo.getDataFinal());
        soapProc.addProperty("status", processo.getStatus());
        soapProc.addProperty("tipo", processo.getTipo());
        soapProc.addProperty("descricao", processo.getDescricao());

        soapInsert.addSoapObject(soapProc);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapInsert);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        try {
            http.call("urn:" + insert, soapEnvelope);
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
}
