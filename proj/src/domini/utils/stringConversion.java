package domini.utils;




public class stringConversion {
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