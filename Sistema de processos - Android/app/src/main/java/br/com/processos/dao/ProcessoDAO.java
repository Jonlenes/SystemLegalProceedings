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
import br.com.processos.model.Processo;

/**
 * Created by Jhon on 22/05/2015.
 */
public class ProcessoDAO {
    private static final String posURLpessoaDao = "ProcessoDAO?wsdl";
    private static final String getAllProcess = "getAllProcess";

    public ArrayList<Processo>[] getAllProcess(String usuario) {
        SoapObject soapGetAllUsu = new SoapObject(URLs.getNameSpace(), getAllProcess);
        soapGetAllUsu.addProperty("usuario", usuario);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapGetAllUsu);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLpessoaDao);
        ArrayList<Processo> [] listProcess = new ArrayList[3];

        for(int i = 0; i < 3; i++)
            listProcess[i] = new ArrayList<Processo>();

        try {
            http.call("urn:" + getAllProcess, soapEnvelope);
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
                listProcess[proc.getStatus()].add(proc);
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
}
