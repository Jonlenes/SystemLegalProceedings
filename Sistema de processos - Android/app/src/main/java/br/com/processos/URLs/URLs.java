package br.com.processos.URLs;

/**
 * Created by Jhon on 22/05/2015.
 */
public class URLs {
    private static final String URLbase = "http://192.168.0.103:8080/WebServiceProcessos/services/";
    private static final String NameSpace = "http://dao.webservice.com.br";

    public static String getURLbase() {
        return URLbase;
    }

    public static String getNameSpace() {
        return NameSpace;
    }
}
