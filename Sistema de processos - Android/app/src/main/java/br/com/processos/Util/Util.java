package br.com.processos.Util;

/**
 * Created by Jhon on 24/05/2015.
 */
public class Util {
    static public Long separaCodigo(String text) {
        try {
            char[] arrayChar = text.toCharArray();
            String nova = new String();
            int i = 0;
            while (arrayChar[i] != (new String("-")).charAt(0) && arrayChar[i] != (new String(" ")).charAt(0)) {
                nova += arrayChar[i];
                i++;
            }
            return Long.parseLong(nova);
        } catch (Exception e) {
            e.printStackTrace();
        };
        return new Long(-1);
    }

}
