package domini.algorithm;

import java.util.ArrayList;


import persistencia.input.*;
import persistencia.output.*;

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
		return 8; //num de bits "importants" de b
	}

	private static Byte round(double x) {
		return (byte)(x + 0.5);
	}

	private static double force255(double x) {
        if (x < 0) return 0;
        if (x > 255) return 255;
        return x;
    }
	private static double alpha(int x) { // only used in discrete_cosine_transform
		if (x == 0) return 1.0 / Math.sqrt(2);
		return 1.0;
	}

	public static void discrete_cosine_transform(double[][] mat1) {
		//int n = 8;
		//int m = 8;
		
		double[][] mat2 = new double[8][8];

		for (int u = 0; u < 8; u++) {
			for (int v = 0; v < 8; ++v) {

				double sum = 0;
				for (int x = 0; x < 8; ++x) { 
                    for (int y = 0; y < 8; ++y)  { 
                        sum += mat1[x][y] *  
                               Math.cos((2*x + 1) * u * Math.PI / (16.0)) *  
                               Math.cos((2*y + 1) * v * Math.PI / (16.0)); 
                    } 
				}
				mat2[u][v] = (1.0/4.0) * alpha(u) * alpha(v) * sum;
			}
		}

		

		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) { 
				mat1[x][y] = mat2[x][y];
			}
		}
	}

	public static void inverse_discrete_cosine_transform(double[][] mat1) {
		//int n = 8;
		//int m = 8;
		
		double[][] mat2 = new double[8][8];

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; ++y) {

				double sum = 0;
				for (int u = 0; u < 8; ++u) { 
                    for (int v = 0; v < 8; ++v)  { 
						sum += alpha(u) * alpha(v) * 
							   mat1[u][v] *  
                               Math.cos((2*x + 1) * u * Math.PI / (16.0)) *  
                               Math.cos((2*y + 1) * v * Math.PI / (16.0)); 
                    } 
				}
				mat2[x][y] = (1.0/4.0) * sum;
			}
		}

		for (int x = 0; x < 8; ++x) {
			for (int y = 0; y < 8; ++y) { 
				mat1[x][y] = mat2[x][y];
			}
		}
	}

	Byte[][][][][] com;
	int com_w, com_h;

	public void encode(Ctrl_Input_Img in) {

		com = new Byte[in.getHeight()/8][in.getWidth()/8][8][8][3];

		int num_i_blocks = in.getHeight()/8;
		int num_j_blocks = in.getWidth()/8;
		////////////////////////////////////////////////
		com_h = num_i_blocks*8; com_w = num_j_blocks*8;
		////////////////////////////////////////////////

		for (int i_block = 0; i_block < num_i_blocks; i_block++)  {

			double[][][][] inRGB = in.get();

			for (int j_block = 0; j_block < num_j_blocks; j_block++)  {

				//1. Color space transformation
				double[][][] YCbCr = new double[3][8][8];
				for (int i = 0; i < 8; ++i) {
					for (int j = 0; j < 8; ++j) {
						double r, g, b;
						r = inRGB[j_block][i][j][0]; g = inRGB[j_block][i][j][1]; b = inRGB[j_block][i][j][2];
						double y, cb, cr;
						// https://sistenix.com/rgb2ycbcr.html
						// https://en.wikipedia.org/wiki/YCbCr#ITU-R_BT.601_conversion
						y = 16 + (65.738*r + 129.057*g + 25.064*b)/256;
						cb = 128 + (-37.945*r - 74.494*g + 112.439*b)/256;
						cr = 128 + (112.439*r - 94.154*g - 18.285*b)/256;
						YCbCr[0][i][j] = y; YCbCr[1][i][j] = cb; YCbCr[2][i][j] = cr;
					}
				}
				
				Byte[][][] outYCbCr = new Byte[8][8][3];
				ArrayList<Byte[]> ret = new ArrayList<Byte[]>();
						
				for (int k = 0; k < 3; ++k) {
					
					//4. Discrete cosnie transform
					for(int i = 0; i < 8; ++i) {
						for (int j = 0; j < 8; ++j) {
							YCbCr[k][i][j] -= 128;
						}
					}
					discrete_cosine_transform(YCbCr[k]);
					
					//5. Quantization
					for(int i = 0; i < 8; ++i) {
						for (int j = 0; j < 8; ++j) {
							outYCbCr[i][j][k] = round(YCbCr[k][i][j] / quantization_matrix[i][j]);
							///////////////////////////////////////////////////
							com[i_block][j_block][i][j][k] = outYCbCr[i][j][k];
							///////////////////////////////////////////////////
						}
					}

					//6. Entropy coding
					int i = 0, j = 0;
					boolean up = true;
					byte zeros = 0;
					for (int aux = 0; aux < 64; ++aux) {
						//https://en.wikipedia.org/wiki/JPEG#Entropy_coding diu:
						//"The first value in the matrix is the DC coefficient; it is not encoded the same way."
						//Però no diu com
						Byte x = outYCbCr[i][j][k];
						if (x == 0) {
							zeros++;
						} else {
							while (zeros >= 16) {
								zeros = 0;
								Byte[] z16_val = {15,0,0};
								ret.add(z16_val);
								zeros -= 16;
							}
							Byte[] y = {zeros, sizeOf(x), x};
							ret.add(y);
							zeros = 0;
						}
						
						
						if      ((i == 0 && up) || (i == 7 && !up)) {j++; up = !up;}
						else if ((j == 0 && !up) || (j == 7 && up)) {i++; up = !up;}
						else if (up) {i--; j++;}
						else         {i++; j--;}
					}
					Byte[] eob = {0,0,0};
					ret.add(eob);

				}
			}
		}

		
	}


	public void decode(String path) {

		///////////////////////////
		int num_i_blocks = com_h/8;
		int num_j_blocks = com_w/8;

		Ctrl_Output_Img out = new Ctrl_Output_Img(path, com_w, com_h, 255);
		////////////////////////////

		for (int i_block = 0; i_block < num_i_blocks; i_block++)  {

			double[][][][] outRGB = new double[num_j_blocks][8][8][3];
			
			for (int j_block = 0; j_block < num_j_blocks; j_block++)  {

				Byte[][][] inYCbCr = new Byte[8][8][3];
				double[][][] YCbCr = new double[3][8][8];

				for (int k = 0; k < 3; ++k) {
					//Falta (6.) entropy coding

					//5. Quantization
					for(int i = 0; i < 8; ++i) {
						for (int j = 0; j < 8; ++j) {
							///////////////////////////////////////////////////
							inYCbCr[i][j][k] = com[i_block][j_block][i][j][k];
							///////////////////////////////////////////////////
							YCbCr[k][i][j] = inYCbCr[i][j][k] * quantization_matrix[i][j];
						}
					}

					//4. Discrete cosnie transform
					inverse_discrete_cosine_transform(YCbCr[k]);
					for(int i = 0; i < 8; ++i) {
						for (int j = 0; j < 8; ++j) {
							YCbCr[k][i][j] += 128;
						}
					}

				}

				//1. Color space transformation
				for (int i = 0; i < 8; ++i) {
					for (int j = 0; j < 8; ++j) {
						double y, cb, cr;
						y = YCbCr[0][i][j]; cb = YCbCr[1][i][j]; cr = YCbCr[2][i][j];
						double r, g, b;
						// https://en.wikipedia.org/wiki/YCbCr#ITU-R_BT.601_conversion
						r = 255./219.*(y-16) + 255./224.*1.402*(cr-128.);
                        g = 255./219.*(y-16) + 255./224.*(1.772*0.114/0.587*(cb-128) - 1.402*0.299/0.587*(cr-128));
                        b = 255./219.*(y-16) + 255./224.*1.772*(cb-128);
                        r = force255(r); g = force255(g); b = force255(b);
						
						outRGB[j_block][i][j][0] = r; g = outRGB[j_block][i][j][1] = g; outRGB[j_block][i][j][2] = b;
					}
				}
			}
			out.add(outRGB);
		}
		out.print();

	}
	
	
}

