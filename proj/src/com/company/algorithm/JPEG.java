package com.company.algorithm;


public class JPEG {

	private static double alpha(int x) { // only used in discrete_cosine_transform
		if (x == 0) return 1.0 / Math.sqrt(2);
		return 1.0;
	}

	private static void discrete_cosine_transform(double[][] mat1, int x0, int y0) {
		//int n = 8;
		//int m = 8;
		
		double[][] mat2 = new double[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; ++j) {

				double sum = 0;
				for (int x = 0; x < 8; ++x) { 
                    for (int y = 0; y < 8; ++y)  { 
                        sum += mat1[x0 + x][y0 + y] *  
                               Math.cos((2*x + 1) * i * Math.PI / (2*8)) *  
                               Math.cos((2*y + 1) * j * Math.PI / (2*8)); 
                    } 
				}
				mat2[i][j] = (1.0/4.0) * alpha(i) * alpha(j) * sum;
			}
		}

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; ++y) { 
				mat1[x0 + x][y0 + y] = mat2[x][y];
			}
		}
	}

	public static byte[][][] convert(byte[][][] inRGB) {
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
					discrete_cosine_transform(YCbCr[k], i, j);
				}
			}
		}

		return inRGB;
	}
	
	public static void main(String args[]) {
			
	}
}

