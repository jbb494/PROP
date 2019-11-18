package domini.algorithm;

import domini.utils.BinTree;


/**
 * @class Huffman
 * @brief Codificació Huffman
 * 
 * Aquesta classe codifica enters mitjançant Huffman.
 * Té dos modes: l'automàtic (codifica mitjançant una taula de huffman per defecte) 
 * i manual (assigna codis en funció de la freqüència dels enters).
 * El mode manual encara no està disponible. La intenció és que estigui disponible
 * per la segona entrega.
 * 
 * @author Joan Lapeyra Amat
 */
class Huffman { 

    /**
	 *  @param auto indica si el mode és automàtic.
	*/
    private boolean auto;

    /**
	 *  @param auto_codes és la taula de huffman que representa la codificació automàtica.
     * Donat un enter x, code(auto_codes[x]) és la codificació de x, que té una mida de size(auto_codes[x])
     * Aquesta taula és la que proporciona https://www.ece.ucdavis.edu/cerl/reliablejpeg/coding/,
     * pensada per ser utilitzada en l'algoritme JPEG.
	*/
    private long[] auto_codes;

    /**
	 *  @param tree és l'arbre de huffan que emmegatzema la codificació.
	*/
    private BinTree tree;

    /**
	 * @fn private void set_auto_codes()
	 * @brief Inicialitza el vector auto_codes, la taula de huffman automàtica.
     * 
	 */
    private void set_auto_codes() {
        // https://www.ece.ucdavis.edu/cerl/reliablejpeg/coding/

        auto_codes = new long[256];
        
        //auto_codes[runlength<<4 + size]
        auto_codes[0x00] = size_code(4, 0b1010);
        auto_codes[0x01] = size_code(2, 0b00);
        auto_codes[0x02] = size_code(2, 0b01);
        auto_codes[0x03] = size_code(3, 0b100);
        auto_codes[0x04] = size_code(4, 0b1011);
        auto_codes[0x05] = size_code(5, 0b11010);
        auto_codes[0x06] = size_code(7, 0b1111000);
        auto_codes[0x07] = size_code(8, 0b11111000);
        auto_codes[0x08] = size_code(10, 0b1111110110);

        auto_codes[0x011] = size_code(4, 0b1100);
        auto_codes[0x012] = size_code(5, 0b11011);
        auto_codes[0x013] = size_code(7, 0b1111001);
        auto_codes[0x014] = size_code(9, 0b111110110);
        auto_codes[0x015] = size_code(11,0b11111110110);

        auto_codes[0x021] = size_code(5, 0b11100);
        auto_codes[0x022] = size_code(8, 0b11111001);
        auto_codes[0x023] = size_code(10, 0b1111110111);
        auto_codes[0x024] = size_code(12, 0b111111110100);

        auto_codes[0x031] = size_code(6, 0b111010);
        auto_codes[0x032] = size_code(9, 0b111110111);
        auto_codes[0x033] = size_code(12, 0b111111110101);

        ///

        auto_codes[0x041] = size_code(6, 0b111011);
        auto_codes[0x042] = size_code(10, 0b1111111000);

        auto_codes[0x051] = size_code(7, 0b1111010);
        auto_codes[0x052] = size_code(11, 0b11111110111);

        auto_codes[0x061] = size_code(7, 0b1111011);
        auto_codes[0x062] = size_code(12, 0xFF6);

        auto_codes[0x071] = size_code(8, 0xFA);
        auto_codes[0x072] = size_code(12, 0xFF7);

        ///

        auto_codes[0x081] = size_code(9, 0x1F8);
        auto_codes[0x082] = size_code(15, 0x7FC0);

        auto_codes[0x091] = size_code(9, 0x1F9);
        auto_codes[0x0A1] = size_code(9, 0x1FA);
        auto_codes[0x0B1] = size_code(10, 0x3F9);
        ///
        auto_codes[0x0C1] = size_code(10, 0x3FA);
        auto_codes[0x0D1] = size_code(11, 0x7F8);

        auto_codes[0x0F0] = size_code(11, 0x7F9);

        

        int[] cod16 = {9, 6, 5, 4,
                       3, 3, 3, 3,
                       3, 2, 2, 2,
                       2, 2, 1, 1};
        
        int x = 0b1111111110000010;
        for (int i = 0; i < 16; ++i) {
            for (int j = cod16[i]; j <= 10; ++j) {
                auto_codes[(i<<4) + j] = size_code(16, x);
                x++;
            }
        }

        //dummy data
        for (int i = 1; i <= 14; ++i) {
            auto_codes[i<<4] = size_code(32, -1);
        }
        for (int i = 0; i < 16; ++i) {
            for (int j = 11; j < 16; ++j) {
                auto_codes[(i<<4) + j] = size_code(32, -1);
            }
        }
    }

    /**
     * @fn private BinTree code_subtree(int size, int code, int info)
     * @brief Genera un (sub)arbre de huffman
     * @param size És la mida del codi de huffman.
     * @param code És el codi de huffman
     * @param info És el símbol que volem codificar
     * @return Un arbre de huffman que codifica el símbol 'info' en el codi 'code' de mide 'size'
     */
    private BinTree code_subtree(int size, int code, int info) {
        if (size == 0) return new BinTree(info);

        BinTree bt = new BinTree();
        bt.setChild(0, code&1, code_subtree(size-1, code>>1, info));
        return bt;
    }

    /**
     * @fn private void code_to_tree(int node, int size, int code, int info)
     * @brief S'actualitza l'atribut 'tree' a partir d'un node. 
     * Després d'haver executat la funció s'ha de poder recórrer l'arbre de huffman a partir de 
     * node per trobar codificat el símbol 'info' en el codi 'code' de mida 'size'
     * @param node És el node a partir del qual volem codificar.
     * @param size És la mida del codi de huffman.
     * @param code És el codi de huffman
     * @param info És el símbol que volem codificar
     */
    private void code_to_tree(int node, int size, int code, int info) {
        if (size == 0 || tree.isLeaf(node)) {
            if (size == 0 && tree.isLeaf(node)) {
                if (tree.getData(node) == info) return;
                else throw new IllegalArgumentException("Code maps two different symbols: "
                    +Integer.toString(info,16)+" and "+Integer.toString(tree.getData(node),16));
            }
            else throw new IllegalArgumentException("Prefixed codes");
        }

        if (tree.getChild(node, code&1) == -1) {
            tree.setChild(node, code&1, code_subtree(size-1, code>>1, info));
        }
        else {
            code_to_tree(tree.getChild(node, code&1), size-1, code>>1, info);
        }
    }

    /**
     * @fn private void set_auto_tree()
     * @brief Incialitza l'arbre de huffman pel mode automàtic.
     */
    private void set_auto_tree() {
        for(int i = 0; i < auto_codes.length; ++i) {
            long cd = auto_codes[i];
            if(code(cd) != -1) {
                //System.out.println(Integer.toString(i,16)+", "+Integer.toString(size(cd),10)+", "+Integer.toString(code(cd),2)+":");
                code_to_tree(0, size(cd), code(cd), i);
            }
        }
    }

    /**
     * @brief Constructora.
     * @param automatic indica si el mode és automàtic
     */
    public Huffman(boolean automatic) {
        auto_codes = new long[0];
        auto = automatic;
        tree = new BinTree();
        node_pointer = 0;
        sym_found = false;
        if(auto) {
            set_auto_codes();
            set_auto_tree();
        }
    }

    /**
	 * @fn public int getCode(int symbol)
	 * @brief Retorna el codi de huffman corresponent a un símbol
	 * @param symbol és el símbol en qüestió
     * @return la codificació de symbol amb els primers bits com a bits menys significatius
	 */
    public int getCode(int symbol) {
        if (auto) {
            return code(auto_codes[symbol]);
        }
        return 0;
    }

    /**
	 * @fn public int getSize(int symbol)
	 * @brief Retorna la mide del codi de huffman corresponent a un símbol
	 * @param symbol és el símbol en qüestió
     * @return la mida en quüestió
	 */
    public int getSize(int symbol) {
        if (auto) {
            return size(auto_codes[symbol]);
        }
        return 0;
    }

    /**
	 * @fn private int getSymbol(int code)
	 * @brief Retorna el símbol corresponent a un codi de huffman 
     * que es pot llegir al recorrer l'arbre de huffman a paritr d'un node 
	 * @param code és el codi de huffman amb els primers bits com a menys significatius
     * @param node és el node de tree en qüestió
     * @return el símbol en qüestió
	 */
    private int getSymbol(int code, int node) {
        if (tree.isLeaf(node)) return tree.getData(node);
        return getSymbol(code>>1, tree.getChild(node, code&1));
    }

    /**
	 * @fn public int getSymbol(int code)
	 * @brief Retorna el símbol corresponent a un codi de huffman
	 * @param code és el codi de huffman amb els primers bits com a menys significatius
     * @return el símbol en qüestió
	 */
    public int getSymbol(int code) {
        return getSymbol(code, 0);
    }

    ///

    /**
	 *  @param node_pointer és el node de 'tree' fins on s'ha buscat un cert símbol 
     * mitjançant HuffmanSearch
	*/
    private int node_pointer;

    /**
	 *  @param last_symbol és l'últim símbol trobar d'una HuffmanSearch
	*/
    private int last_symbol;
    /**
	 *  @param sym_found indica si en la HuffmanSearch actual s'ha trobat ja un símbol.
	*/
    private boolean sym_found;

    /**
	 * @fn public void initSearchSymbol()
	 * @brief Inicialitza una HuffmanSearch. El codi de huffman implícit és buit.
	 */
    public void initSearchSymbol() {
        node_pointer = 0;
        sym_found = false;
    }

    /**
	 * @fn public int searchSymbol(int bit)
	 * @brief Fa avançar la HuffmanSearch: afageix un bit al codi de huffman implícit.
     * @param bit és el bit que s'afageix al codi de la HuffmanSeach
     * @return 
     *          1 si s'ha trobat un símbol, és a dir, el codi de huffman implícit correspon a un símbol.
     *          0 si no s'ha trobat un símbol però pot ser que sigui trobat en un futur, és a dir,
     *            el codi de huffman implícit és prefix de codis de huffman vàlids.
     *          -1 si no s'ha trobat cap símbol ni es trobarà a l'actual HuffmanSeach, és a dir, 
     *            el codi de Huffman implícit no és prefix de cap codi vàlid.
	 */
    public int searchSymbol(int bit) {
        bit = bit&1;
        node_pointer = tree.getChild(node_pointer, bit);
        if (node_pointer == -1) {
            initSearchSymbol();
            return -1; //no trobat
        }
        if (tree.isLeaf(node_pointer)) {
            last_symbol = tree.getData(node_pointer);
            node_pointer = 0;
            sym_found = true;
            return 1; //trobat
        }
        sym_found = false;
        return 0; //cal seguir buscant
    }

    /**
	 * @fn public int getFoundSymbol()
	 * @brief Retorna el símbol trobat a la HuffmanSearch, és a dir, el símbol representat
     * pel codi de Huffman implícit.
	 */
    public int getFoundSymbol() {
        if(!sym_found) throw new IllegalArgumentException("There is no found symbol"); 
        return last_symbol;
    }


    ////

    /**
	 * @fn private static long size_code(int size, int code)
	 * @brief Retorna un long que representa un codi d'una mida determinada.
     * @param size és la mida del codi en qüestó
     * @param code és el codi en qüestió, on els primers bits es donen com a bits més significatius
	 */
    private static long size_code(int size, int code) { //most significant bits are the first ones
        int x = 0;
        for (int i = 0; i < size; ++i) {
            x <<= 1;
            x += code & 1;
            code >>= 1;
        }
        return ((long)size << 32) + (long)x;
    }
    
    /**
	 * @fn private static long size_code(int size, int code)
	 * @brief Retorna la mida d'un codi representat per un long.
     * @param x represnta el codi en qüestió
     * @return la mida del codi representat per x
	 */
    private static int size(long x) {
        return (int)(x >> 32);
    }

    /**
	 * @fn private static long size_code(int size, int code)
	 * @brief Retorna un codi representat per un long.
     * @param x represnta el codi en qüestió
     * @return el codi representar per x. Els primers bits es retornen com a bits de menys pes.
	 */
    private static int code(long x) { //lest significant bits are the first ones
        return (int)x;
    }

}