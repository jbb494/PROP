package com.company.algorithm;

import java.util.ArrayList;

public class JPEG {

	public JPEG() {}

	private static int[][] quantization_matrix =
	{
		{16, 11, 10, 16, 24, 40, 51, 61},
		{12, 12, 14, 19, 26, 58, 60, 55},
		{14, 13, 16, 24, 40, 57, 69, 56},
		{14, 17, 22, 29, 51, 87, 80, 62},
		{18, 22, 37, 56, 68, 109, 103, 77},
		{24, 35, 55, 64, 81, 104, 113, 92},
		{49, 64, 78, 87, 103, 121, 120, 101},
		{72, 92, 95, 98, 112, 100, 103, 99}
	};

	private static Byte sizeOf(Byte b) {
		return 9; //num de bits "importants" de b
	}

	private static Byte round(double x) {
		return (byte)(x + 0.5);
	}

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

		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) { 
				mat1[x0 + x][y0 + y] = mat2[x][y];
			}
		}
	}


	public static ArrayList<Byte[]> encode(byte[][][] inRGB) {
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
		
		Byte[][][] outYCbCr = new Byte[n][m][3];
		ArrayList<Byte[]> ret = new ArrayList<Byte[]>();

		//3. Block splitting
		for (int i = 0; i+7 < n; i += 8) {
			for (int j = 0; j+7 < m; j += 8) {

				
				for (int k = 0; k < 3; ++k) {

					//4. Discrete cosnie transform
					for(int ii = 0; ii < 8; ++ii) {
						for (int jj = 0; jj < 8; ++jj) {
							YCbCr[k][i+ii][j+jj] -= 128;
						}
					}
					discrete_cosine_transform(YCbCr[k], i, j);
					
					//5. Quantization
					for(int ii = 0; ii < 8; ++ii) {
						for (int jj = 0; jj < 8; ++jj) {
							outYCbCr[i+ii][j+jj][k] = round(YCbCr[k][i+ii][j+jj] / quantization_matrix[ii][jj]);
						}
					}

					//6. Entropy coding
					int ii = 0, jj = 0;
					boolean up = true;
					Byte zeros = 0;
					Byte[] z16 = {15,0,0};
					for (int aux = 0; aux < 64; ++aux) {
						//https://en.wikipedia.org/wiki/JPEG#Entropy_coding diu:
						//"The first value in the matrix is the DC coefficient; it is not encoded the same way."
						//Però no diu com
						Byte x = outYCbCr[i+ii][j+jj][k];
						if (x == 0) {
							zeros++;
							if (zeros == 16) {
								zeros = 0;
								ret.add(z16);
							}
						} else {
							Byte[] y = {zeros, sizeOf(x), x};
							ret.add(y);
							zeros = 0;
						}
						
						
						if      ((ii == 0 && up) || (ii == 7 && !up)) {jj++; up = !up;}
						else if ((jj == 0 && !up) || (jj == 7 && up)) {ii++; up = !up;}
						else if (up) {ii--; jj++;}
						else         {ii++; jj--;}
					}
					while (!ret.isEmpty() && ret.get(ret.size()-1)[0] == 15 && ret.get(ret.size()-1)[1] == 0) {
						ret.remove(ret.size()-1);
					}
					Byte[] eob = {0,0,0};
					ret.add(eob);

				}
			}
		}

		return ret;
	}
	
	public static void main(String args[]) {
			
	}
}


