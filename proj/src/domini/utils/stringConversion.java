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

}