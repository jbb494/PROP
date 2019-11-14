package domini.utils;

public class utf8 {

    public utf8() {}

    public static String uft8Encode(String text) {   
        StringBuilder res = new StringBuilder();                
        for (char c : text.toCharArray()) {
            int i = (int) c;
            if (i < 128) {
                res.append((char) i);
            } else if (i > 127 && i < 2048) {
                int j = (i >> 6) | 192;
                res.append((char) j);
                j = (i & 63) | 128;
                res.append((char) j);
            } else {
                int j = (i >> 12) | 224;
                res.append((char) j);
                j = ((i >> 6) & 63) | 128;
                res.append((char) j);
                j = (c & 63) | 128;
                res.append((char) j);
            }
        }
        return res.toString();
    }

    public static String utf8Decode(String text) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        char[] chars = text.toCharArray();
        while (i < chars.length) {
            int c = chars[i++];
            if (c > 191 && c < 224) {
                int c1 = chars[i++];
                c = ((c & 31) << 6) | (c1 & 63);
            } else if (c > 127) {
                int c1 = chars[i++];
                int c2 = chars[i++];
                c = ((c & 15) << 12) | ((c1 & 63) << 6) | (c2 & 63);
            }
            res.append((char) c);
        }
        return res.toString();
    }

}
