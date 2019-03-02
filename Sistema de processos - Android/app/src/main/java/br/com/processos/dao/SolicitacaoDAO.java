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
import br.com.processos.model.Solicitacao;

/**
 * Created by Jhon on 24/05/2015.
 */
public class SolicitacaoDAO {
    private static final String posURLsolicitacaoDao = "SolicitacaoDAO?wsdl";
    private static final String insertSol = "insert";

    public boolean insert(Solicitacao solicitacao) {
        SoapObject soapInsert = new SoapObject(URLs.getNameSpace(), insertSol);
        SoapObject soapSol = new SoapObject(URLs.getNameSpace(), "solicitacao");

        soapSol.addProperty("dataSolicitacao", "");
        soapSol.addProperty("descricao", solicitacao.getDescricao());
        soapSol.addProperty("regOAB", solicitacao.getRegOAB());
        soapSol.addProperty("requerente", solicitacao.getRequerente());
        soapSol.addProperty("tipoAcao", solicitacao.getTipoAcao());

        soapInsert.addSoapObject(soapSol);

        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        soapEnvelope.setOutputSoapObject(soapInsert);
        soapEnvelope.implicitTypes = true;

        HttpTransportSE http = new HttpTransportSE(URLs.getURLbase() + posURLsolicitacaoDao);
        try {
            http.call("urn:" + insertSol, soapEnvelope);
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
