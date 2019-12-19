package domini.folders; 

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @class Driver__Ctrl_FolderFile
 * @brief Driver de Ctrl_FolderFile
 * @file
 * @author Joan Bellavista Bartroli
 */
public class Driver__Ctrl_FolderFile {

	/**
	 * @fn private static void showOptions()
	 * @brief Mostra les accions a realitzar durant l'execució
	*/
	private static void showOptions(){
		System.out.println("Driver de Ctrl_FolderFile");
		System.out.println("Constructores: ");
		System.out.println("	 1. Ctrl_FolderFile(String path_input)");
		System.out.println("	 Input: 1");

		System.out.println("Funciones: ");
		System.out.println("	 2. EncodeManualFolder(String text_method, double img_quality) ");
		System.out.println("	 Input: 2");
		System.out.println("	 3. EncodeManualText(String text_method) ");
		System.out.println("	 Input: 3");
		System.out.println("	 4. EncodeManualImg(double img_quality) ");
		System.out.println("	 Input: 4");
		System.out.println("	 5. EncodeAuto() ");
		System.out.println("	 Input: 5");
		System.out.println("	 6. Decode() ");
		System.out.println("	 Input: 6");
		System.out.println("	 7. DecodeTemp() ");
		System.out.println("	 Input: 7");
		System.out.println("	 8. isEncoded() ");
		System.out.println("	 Input: 8");
		System.out.println("	 9. isEncodedFolder() ");
		System.out.println("	 Input: 9");
		System.out.println("	 10. isEncodedFile() ");
		System.out.println("	 Input: 10");
		System.out.println("	 11. getEncodedExtension() ");
		System.out.println("	 Input: 11");
		System.out.println();

		System.out.println("	 0. Sortir");
		System.out.println("	 Input: 0");
		System.out.println("----------------------------------------");
	}
	/**
	 * @fn private static void comprovarExcepcions(Ctrl_FolderFile ctrl_folderfile, String linea)
	 * @brief Comprovarà les possibles excepcions que puguin apareixer a la classe
	 * @param ctrl_folderfile
	 * @param linea
	 */
	private static void comprovarExcepcions(Ctrl_FolderFile ctrl_folderfile, String linea){
		int op = Integer.parseInt(linea);
		if(ctrl_folderfile == null && op > 1) {
			throw new IllegalArgumentException("Debes llamar al constructor antes");
		}
	}

	public static void main(String[] args) {
	try {
		Ctrl_FolderFile ctrl_folderfile = null;
		showOptions();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String linea = "";
		while(linea != null){
			System.out.println("Selecciona una opción:");
			linea = reader.readLine().trim();
			System.out.println("Opción: " + linea + " seleccionada");
			comprovarExcepcions(ctrl_folderfile, linea);
			switch(linea){
				case "1":
					System.out.println("Escribe el path de lo que quieres comprimir o descomprimir (No cableado)");
					String aux1 = reader.readLine().trim();
					String path_input = aux1;
					ctrl_folderfile = new Ctrl_FolderFile(path_input);
				break;
				case "2":
					System.out.println("Escribe separado por espacios el nombre del metodo con el que quieres que " +
							"se compriman todos los archivos no ppm (\"lz78\" \"lzss\" \"lzw\") y un entero del 1-100" +
							" que representarà la calidad de todas las imagenes comprimidas dentro de la carpeta.");
					String[] aux2 = reader.readLine().trim().split(" ");
					String text_method = aux2[0];
					double img_quality = Double.parseDouble(aux2[1]);
					ctrl_folderfile.EncodeManualFolder(text_method, img_quality);
				break;
				case "3":
					System.out.println("Escribe el nombre del metodo con el que quieres que se comprima el archivo " +
							"(\"lz78\" \"lzss\" \"lzw\")");
					String aux3 = reader.readLine().trim();
					String text_method2 = aux3;
					ctrl_folderfile.EncodeManualText(text_method2);
				break;
				case "4":
					System.out.println("Escribe un entero entre 1-100 que representara la qualidad de la imagen" +
							"comprimida");
					String aux4 = reader.readLine().trim();
					double img_quality2 = Double.parseDouble(aux4);
					ctrl_folderfile.EncodeManualImg(img_quality2);
				break;
				case "5":
					ctrl_folderfile.EncodeAuto();
				break;
				case "6":
					ctrl_folderfile.Decode();
				break;
				case "7":
					ctrl_folderfile.DecodeTemp();
				break;
				case "8":
					System.out.println("It " + (ctrl_folderfile.isEncoded() ? "is Encoded" : "is not Encoded"));
				break;
				case "9":
					System.out.println("The folder " + (ctrl_folderfile.isEncodedFolder() ? "is Encoded" : "is not Encoded"));
				break;
				case "10":
					System.out.println("The file " + (ctrl_folderfile.isEncodedFile() ? "is Encoded" : "is not Encoded"));
				break;
				case "11":
					System.out.println("La extensio es la següent: " + ctrl_folderfile.getEncodedExtension());
				break;
				case "0":
					return;
				default:
					System.out.println("La opción no es válida");
				break;
			}
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
}
