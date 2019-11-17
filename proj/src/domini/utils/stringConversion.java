package domini.utils;

/**
 * @class stringConversion
 * @brief Classe de stringConversion
 * @author Joan Lapeyra
 */
public class stringConversion {

    /**
     * @fn public static int atoi(String str)
     * @brief S'encarrega de convertir un String a Int
     * @param str String a transformar
     * @return Valor enter de l'String que hem passat per paràmetre
     */
    public static int atoi(String str) 
    { 
        int x = 0;
        int n = str.length();
        if (n == 0) return 0;
        int i = 0;
        boolean pos;
        if      (str.charAt(0) == '+') {pos = true; i++;}
        else if (str.charAt(0) == '-') {pos = false; i++;}
        else                           {pos = true;}

        while (i < n) {
            x = x*10 + (str.charAt(i) - '0');
            i++;
        }
        if (!pos) x = -x;
        return x;
    }

    /**
     * @fn public static String intToString(int x)
     * @brief S'encarrega de convertir un Int a String
     * @param x Enter que volem convertir a String
     * @return Valor de l'enter com a String
     */
    public static String intToString(int x) {
        if (x == 0) return "0";
        if (x < 0) return "-" + intToString(-x);

        String str = "";
        while (x > 0) {
            int aux = x%10;
            str = (char)(aux+'0') + str;
            x = x/10;
        }
        return str;

    }

}