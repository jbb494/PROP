


public class JPEG {
	public byte[][][] convert(byte inRGB[][][]) {
		int n = inRGB.length;
		int m = inRGB.[0].length;
		byte inYCbCr[n][m][3];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				byte r, g, b;
				r = inRGB[i][j][0]; g = inRGB[i][j][1]; b = inRGB[i][j][2];
				byte y, cb, cr;
				// https://sistenix.com/rgb2ycbcr.html
				 
			}
		}
	}
	
	/*public static void main(String args[]) {
			int a[][] = {{1, 2}, {3}};
			for (int i[] : a) System.out.println(i.length);
	}*/
}

///github