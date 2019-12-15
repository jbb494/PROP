package persistencia.output;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import domini.utils.byteToConversion;

/**
 * @class RandomAccessOutput
 * @brief Classe RandomAccessOutput
 * @author Joan Lapeyra
 */
public class RandomAccessOutput {

    /**
     * @fn public static void writeLong(String path, long address, long data)
     * @brief Desa els 8 bytes de data a la posició address del fitxer path en big endian
     * @param path a un fitxer
     * @param address on es guardarà data
     * @param data que volem quardar
     */
    public static void writeLong(String path, long address, long data) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(path, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                raf.seek(address);
                ArrayList<Byte> al = byteToConversion.longToByte(data);
                for (Byte b : al) {
                    raf.write(b);
                }
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}