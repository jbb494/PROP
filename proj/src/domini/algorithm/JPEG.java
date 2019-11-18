package domini.algorithm;


import persistencia.input.Ctrl_Input_Img;
import persistencia.input.Ctrl_Input_JPEG;
import persistencia.output.Ctrl_Output;
import persistencia.output.Ctrl_Output_Img;

/**
 * @class JPEG
 * @brief Compressió i descompressió pel mètode JPEG
 * 
 * @author Joan Lapeyra Amat
 */
public class JPEG {

	/**
	 *  @param out és utilitxat per emmegatzemar la compressió o descompressió.
	*/
	Ctrl_Output out;
	/**
	 *  @param path és el path de sortida.
	*/
	String path;
	/**
	 *  @param decode_or_encode indica si és compressió (true) o descompressió (false)
	*/
	boolean decode_or_encode; // if true, decode; otherwise, encode

	/**
	 *  @param huff és la codificació de huffman utilitzat a Entropy Conding
	*/
	Huffman huff;

	/**
	 * @brief Constructor de la clase JPEG
	 * @param aux Path de sortida
	 * @param b False si estas comprimint, True si estas descomprimint
	 */
	public JPEG(String aux, boolean b) {
		huff = new Huffman(true);
		path = aux;
		decode_or_encode = b;
	}

	
	/**
	 *  @param quantization_matrix és la matriu de quantització
	*/
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

	/**
	 *  @param zigZag_X[i] és la fila que es cnsulta a la ièssima iteració 
	 * d'un recorregut en zigzag d'una matriu 8x8
	*/
	private static int[] zigZag_X = {
		0,
		0, 1,
		2, 1, 0,
		0, 1, 2, 3, 
		4, 3, 2, 1, 0,
		0, 1, 2, 3, 4, 5,
		6, 5, 4, 3, 2, 1, 0,
		0, 1, 2, 3, 4, 5, 6, 7,
		7, 6, 5, 4, 3, 2, 1,
		2, 3, 4, 5, 6, 7,
		7, 6, 5, 4, 3,
		4, 5, 6, 7, 
		7, 6, 5,
		6, 7,
		7
	};
		
	/**
	 *  @param zigZag_Y[i] és la columna que es cnsulta a la ièssima iteració 
	 * d'un recorregut en zigzag d'una matriu 8x8
	*/
	private static int[] zigZag_Y = {
		0,
		1, 0,
		0, 1, 2,
		3, 2, 1, 0,
		0, 1, 2, 3, 4,
		5, 4, 3, 2, 1, 0,
		0, 1, 2, 3, 4, 5, 6,
		7, 6, 5, 4, 3, 2, 1, 0,
		1, 2, 3, 4, 5, 6, 7,
		7, 6, 5, 4, 3, 2,
		3, 4, 5, 6, 7,
		7, 6, 5, 4,
		5, 6, 7,
		7, 6,
		7
	};


	/**
	 * @fn private static int round(double x)
	 * @brief Arrodoneix un número real
     * @param x és el real
     * @return l'enter més pròxim a x
	 */
	private static int round(double x) {
		return (int)(x + 0.5);
	}

	/**
	 * @fn private double force255(double x)
     * @param x és un real
     * @return el real més proper a x de l'interval [0..255]
	 */
	private static double force255(double x) {
        if (x < 0) return 0;
        if (x > 255) return 255;
        return x;
	}
	/**
	 * @fn private double force255(double x)
	 * @brief és la funcí alfa de DCT
	 */
	private static double alpha(int x) { // only used in discrete_cosine_transform
		if (x == 0) return 1.0 / Math.sqrt(2);
		return 1.0;
	}

	 
	/**
	 * @fn private static void discrete_cosine_transform(double[][] mat1)
	 * @brief aplica discrete cosine transform (DCT) a una matriu 8x8
	 * @param mat1 és la matriu 8x8 a la qual s'aplica DCT
	 */
	private static void discrete_cosine_transform(double[][] mat1) {
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
	
	/**
	 * @fn private static void inverse_discrete_cosine_transform(double[][] mat1)
	 * @brief aplica discrete cosine transform (DCT) inversa a una matriu 8x8
	 * @param mat1 és la matriu 8x8 a la qual s'aplica DCT inversa
	 */
	private static void inverse_discrete_cosine_transform(double[][] mat1) {
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
	/**
	 * @fn private static int get_entropy1_code(int runlength, int size)
	 * @brief codifica runlength i size en el simbol1 de Entropy Coding
	 */
	private static int get_entropy1_code(int runlength, int size) {
		return ((runlength<<4) + size);
	}
	/**
	 * @fn private static int get_runlength_from_entropy1(int code)
	 * @brief retorna el runlength codificat en un simbol1 de Entropy Coding
	 */
	private static int get_runlength_from_entropy1(int code) {
		return (code >> 4) & 0x000F;
	}
	/**
	 * @fn private static int get_runlength_from_entropy1(int code)
	 * @brief retorna la mida codificada en un simbol1 de Entropy Coding
	 */
	private static int get_size_from_entropy1(int code) {
		return code & 0x000F;
	}
	/**
	 * @fn private static int get_entropy2_size(int value)
	 * @brief codifica value en el symbol2 de Entropy Coding i en retorna la mida
	 */
	private static int get_entropy2_size(int value) {
		if (value == 0) return 0;
		if (value < 0) return get_entropy2_size(-value);
		return 1 + get_entropy2_size(value / 2);
	}
	/**
	 * @fn private static int get_entropy2_code(int value)
	 * @brief retorna la codifiacació en el simbol2 de Entropy Coding d'un cert valor
	 */
	private static int get_entropy2_code(int value) {
		if (value < 0) return ~(-value);
		return value;
	}
	/**
	 * @fn private static int get_value_from_entropy2(int code, int size)
	 * @brief retorna el valor codificat en un codi d'una determinada mida
	 */
	private static int get_value_from_entropy2(int code, int size) {
		if (size == 0) return 0;
		if (((code >> (size-1)) & 1) == 0) return -(~(code | (-1 << size)));
		return code;
	}


	/**
	 * @fn public void Compressor(Ctrl_Input_Img in)
	 * @brief Comprimim un text amb l'algoritme JPEG
	 * @param in accés al Controlador d'Input del fitxer a comprimir
	 */
	public void Compressor(Ctrl_Input_Img in) {

		out = new Ctrl_Output(path, "jpeg", false);

		int num_i_blocks = in.getHeight()/8;
		int num_j_blocks = in.getWidth()/8;

		out.add2(num_j_blocks*8, 32);
		out.add2(num_i_blocks*8, 32);

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
				
				int[][][] outYCbCr = new int[8][8][3];
				//ArrayList<Byte[]> ret = new ArrayList<Byte[]>();
						
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
						}
					}

					//6. Entropy coding
					int zeros = 0;
					for (int aux = 0; aux < 64; ++aux) {
						int x = outYCbCr[zigZag_X[aux]][zigZag_Y[aux]][k];
						if (x == 0) {
							zeros++;
						} else {
							while (zeros >= 16) {
								out.add2(huff.getCode(0x0F0), huff.getSize(0x0F0));
								zeros -= 16;
							}
							int by = get_entropy1_code(zeros, get_entropy2_size(x));
							out.add2(huff.getCode(by), huff.getSize(by));
							zeros = 0;
							out.add2(get_entropy2_code(x), get_entropy2_size(x));
							/*if (j_block == 0 && i_block == 0 && k == 0) { //debug
								System.out.print(x);
								System.out.print(",");
								System.out.print(get_entropy2_code(x));
								System.out.print(",");
								System.out.print(get_entropy2_size(x));
								System.out.print(":");
								System.out.print(zeros);
								System.out.print(":");
								System.out.print(Integer.toHexString(by));
								System.out.print(",");
								System.out.print(Integer.toBinaryString(huff.getCode(by)));
								System.out.print(",");
								System.out.print(huff.getSize(by));
								System.out.print("  ");
							}*/
						}
					}
					out.add2(huff.getCode(0), huff.getSize(0));

				}
			}
		}

		
	}


	/**
	 * @fn public void Decompressor(Ctrl_Input_JPEG in
	 * @brief Desomprimim un text amb l'algoritme JPEG
	 * @param in accés al Controlador d'Input del fitxer a descomprimir
	 */
	public void Decompressor(Ctrl_Input_JPEG in) {

		int num_i_blocks = in.getHeight()/8;
		int num_j_blocks = in.getWidth()/8;

		out = new Ctrl_Output_Img(path, in.getWidth(), in.getHeight(), 255);

		//int it = 0;

		for (int i_block = 0; i_block < num_i_blocks; i_block++)  {

			double[][][][] outRGB = new double[num_j_blocks][8][8][3];
			
			for (int j_block = 0; j_block < num_j_blocks; j_block++)  {

				int[][][] inYCbCr = new int[8][8][3];
				for (int i = 0; i < 8; ++i) {
                    for (int j = 0; j < 8; ++j) {
                        for (int k = 0; k < 3; ++k) inYCbCr[i][j][k] = 0;
                    }
				}
				
				double[][][] YCbCr = new double[3][8][8];

				
				for (int k = 0; k < 3; ++k) {
					//6. Entropy coding
					int zz = 0;
					while (true) {

						int found;
						while ((found = huff.searchSymbol(in.get(1))) == 0)
						if (found == -1) throw new IllegalArgumentException("Huffman code not found");
						
						int by = huff.getFoundSymbol();
						int size = get_size_from_entropy1(by);

						if (by == 0x00) break;
						else if (by == 0x0F0) {
							zz += 16;
						}
						else {
							zz += get_runlength_from_entropy1(by);
							if (zz >= 64) throw new IllegalArgumentException("Entropy coding failed");
							int code = in.get(size);
							int x = get_value_from_entropy2(code, size);
							/*if (j_block == 0 && i_block == 0 && k == 0) { //debug
								System.out.print(x);
								System.out.print(",");
								System.out.print(code);
								System.out.print(",");
								System.out.print(size);
								System.out.print(":");
								System.out.print(get_runlength_from_entropy1(by));
								System.out.print(":");
								System.out.print(Integer.toHexString(by));
								System.out.print(",");
								System.out.print(Integer.toBinaryString(huff.getCode(by)));
								System.out.print(",");
								System.out.print(huff.getSize(by));
								System.out.print("   ");
							}*/
							inYCbCr[zigZag_X[zz]][zigZag_Y[zz]][k] = x;
							zz++;
						}
					}

					//5. Quantization
					for (int i = 0; i < 8; ++i) {
						for (int j = 0; j < 8; ++j) {
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

			((Ctrl_Output_Img)out).add(outRGB);
		}

	}

	/**
	 * @fn public Ctrl_Output print()
	 * @brief Retorna el Ctrl_Output.
	 * @return Retorna el Ctrl_Output on està l'arxiu comprimit o descomprimit.
	 */
	public Ctrl_Output print() {
        return out;
	}

	/*public static void main(String[] args) {
		
		Ctrl_Input_Img img = new Ctrl_Input_Img(args[0]);
		JPEG alg = new JPEG(args[1], false);
		alg.Compressor(img);
		Ctrl_Output out1 = alg.print();
		out1.print();
		System.out.println("--");
		Ctrl_Input_JPEG jpeg = new Ctrl_Input_JPEG(args[1]);
		JPEG alg2 = new JPEG(args[2], true);
		alg2.Decompressor(jpeg);
		Ctrl_Output out2 = alg2.print();
		out2.print();
	}*/
	
	
}

