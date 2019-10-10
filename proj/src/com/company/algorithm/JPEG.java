


public class JPEG {
	private void f(double[][] mat) {
		mat[0][0] = 1.0;
	}

	public byte[][][] convert(byte[][][] inRGB) {
		int n = inRGB.length;
		int m = inRGB[0].length;
		
		//1. Color space transformation
		double[][][] YCbCr = new double[3][n][m];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				double r, g, b;
				r = inRGB[i][j][0]; g = inRGB[i][j][1]; b = inRGB[i][j][2];
				double y, cb, cr;
				// https://sistenix.com/rgb2ycbcr.html
				y = 16 + (65.738*r + 129.057*g + 25.064f*b)/256;
				cb = 128 + (-37.945*r - 74.494*g + 112.439*b)/256;
				cr = 128 + (112.439*r - 94.154*g - 18.285*b)/256;
				YCbCr[0][i][j] = y; YCbCr[1][i][j] = cb; YCbCr[2][i][j] = cr;
			}
		}
		
		for (int k = 0; k < 3; ++k) {

			//3. Block splitting
			for (int i = 0; i+7 < n; i += 8) {
				for (int j = 0; j+7 < m; j += 8) {
					//f();
				}
			}
		}

		return inRGB;
	}
	
	public static void main(String args[]) {
			
	}
}

