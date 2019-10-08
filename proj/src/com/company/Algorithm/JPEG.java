


public class JPEG {
	public byte[][][] convert(byte inRGB[][][]) {
		int n = in.length;
		int m = in[0].length;
		byte inYCbCr[n][m][3];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				byte r, g, b;
				r = inRGB[i][j][0]; g = inRGB[i][j][1]; b = inRGB[i][j][2];
				byte y, cb, cr;
				// ni puta idea de com es fa la conversió
				// cada font ho fa diferent
			}
		}
	}
	
	/*public static void main(String args[]) {
			int a[][] = {{1, 2}, {3}};
			for (int i[] : a) System.out.println(i.length);
	}*/
}
