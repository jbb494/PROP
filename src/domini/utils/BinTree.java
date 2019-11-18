package domini.utils;

import java.util.ArrayList;

/**
 * @class BinTree
 * @brief Permet expressar arbres binaris on les fulles poden prendre valors enters.
 *
 * Els nodes s'identifiquen amb enters. Aquesta estroctura de dades permet afegir informació però no permet
 * eliminar o modificar informació prèviament afegida.
 *
 * @author Joan Lapeyra Amat
 */
public class BinTree {

    
    
    /**
     * @param arr
     * sigui x un node
     * si arr[2*x] = -2, x és una fulla i arr[2*x+1] és el valor de x
     * altrament x+arr[2*x] i x+arr[2*x+1] són els fills de x (-1 significa indefinit)
     * Un node no està inicialitzat <-> els dos fills son indefinits
     * El node arrel és 0
     */
    private ArrayList<Integer> arr;
    

    /**
     * @param num_undefs és el número de fills indefinits en el global desl nodes
     */
    private int num_undefs;    

    /**
	 * @brief Constructora: es crea un arbre amb un sol node que té els dos fills indefinits
	 */
    public BinTree() {
        arr = new ArrayList<Integer>();
        arr.add(-1);
        arr.add(-1);
        num_undefs = 2;
    }

    /**
	 * @brief Constructora: es crea un arbre amb un sol node que té valor
	 * @param data és el valor de l'unic node
	 */
    public BinTree(int data) {
        arr = new ArrayList<Integer>();
        arr.add(-2);
        arr.add(data);
        num_undefs = 0;
    }
    /**
     * @fn private void checkNode(int x)
	 * @brief Salta una excepció si x no representa un node de l'arbre
	 * @param x vol representar un node
	 */
    private void checkNode(int x) {
        if (2*x >= arr.size() || x < 0) throw new IllegalArgumentException(Integer.toString(x)+" is an invalid node");
    }
    /**
     * @fn public boolean wellDefined()
	 * @brief Indica si l'arbre està ben deninit, és a dir, si tots els nodes estan inicialitzats
     * @return true <-> l'arbre està ben deninit
	 */
    public boolean wellDefined() {
        return num_undefs == 0;
    }
    /**
     * @fn public int size()
	 * @brief Indica la mida de l'arbre
     * @return el nombre de nodes
	 */
    public int size() {
        return arr.size()/2;
    }
    /**
     * @fn public boolean isInit(int x)
	 * @brief Indica si un node està inicialitzat 
     * @param x representa un node
     * @return true <-> x està inicialitzat 
	 */
    public boolean isInit(int x) {
        checkNode(x);
        return arr.get(2*x) != -1 || arr.get(2*x+1) != -1;
    }
    /**
     * @fn public boolean isLeaf(int x)
	 * @brief Indica si un node és una fulla amb valor
     * @param x representa un node
     * @return true <-> x és una fula amb valor
	 */
    public boolean isLeaf(int x) {
        checkNode(x);
        return arr.get(2*x) == -2;
    }

    /**
     * @fn public int getData(int x)
	 * @brief Retorna el valor d'un node
     * @param x representa una fulla
     * @return el valor de la fulla x
	 */
    public int getData(int x) {
        if (!isLeaf(x)) throw new IllegalArgumentException("Non-leaf nodes don't contain data");
        return arr.get(2*x + 1);
    }

    /**
     * @fn public void setData(int x, int data)
	 * @brief Assigna un valor a un node x
     * @param x representa un node no inicialitzat
	 */
    public void setData(int x, int data) {
        if (isInit(x)) throw new IllegalArgumentException("This node has been alredy initialized");;
        arr.set(2*x, -2);
        arr.set(2*x+1, data);
        num_undefs -= 2;
    }

    /**
     * @fn public int getChild(int x, int left_right)
	 * @brief Retorna el node fill d'un node
     * @param x representa un node que no es fulla
     * @param left_right és 0 si es vol consultar el fill esquerre i és 1 si es vol consultar el dret
     * @return el fill del costat left_right del node x
	 */
    public int getChild(int x, int left_right) {
        if (isLeaf(x)) throw new IllegalArgumentException("Leaf nodes have no children");
        int despl = arr.get(2*x + (left_right&1));
        if (despl==-1) return -1;
        return x + despl;
    }
    /**
     * @fn public int setChild(int x, int left_right, BinTree child)
	 * @brief Assigna un fill a un node
     * @param x representa un node que no es fulla
     * @param left_right és 0 si es vol afegir el fill a l'esquerra i és 1 si es vol afegir a la dreta
     * @param child conté els nodes que descendiran de x. 
     * L'arrel de child serà fill de x i la resta de nodes mantindran la jerarquia
     * @return el fill afegit.
	 */
    public int setChild(int x, int left_right, BinTree child) {
        if (isLeaf(x)) throw new IllegalArgumentException("Leaf nodes can't have children");
        if (arr.get(2*x + (left_right&1)) != -1)
            throw new IllegalArgumentException("This node has alredy a child on this side");
        int pos = size();
        arr.addAll(child.arr);
        checkNode(pos);
        arr.set(2*x + (left_right&1), pos-x);
        num_undefs += child.num_undefs - 1;
        return pos;
    }

    /*private void print_v2(int tabs, int x) {
        if (x == -1) {
            System.out.println(" -");
            return;
        }
        System.out.println(x);
        for(int i = 0; i < tabs; ++i) System.out.print("  ");
        if (isLeaf(x)) System.out.println("leaf: "+Integer.toString(getData(x)));
        else {
            System.out.print("->");
            print_v2(tabs+1, getChild(x, 0));
            for(int i = 0; i < tabs; ++i) System.out.print("  ");
            System.out.print("->");
            print_v2(tabs+1, getChild(x, 1));

        }
    }*/

    /**
     * @fn private void print()
	 * @brief Imprimeix el subarbre dels descendents de x en preordre inicant cada fill amb un 0 o un 1 segons s'escaigui
     * i indicant el valor de les fulles.
     * @param x representa un node
	 */
    private void print(int tabs, int x) {
        if (x == -1) {
            for(int i = 0; i < tabs; ++i) System.out.print("  ");
            System.out.println(" -");
            return;
        }
        //System.out.println(x);
        for(int i = 0; i < tabs; ++i) System.out.print("  ");
        if (isLeaf(x)) System.out.println("leaf: "+Integer.toString(getData(x)));
        else {
            System.out.println("->0");
            print(tabs+1, getChild(x, 0));
            for(int i = 0; i < tabs; ++i) System.out.print("  ");
            System.out.println("->1");
            print(tabs+1, getChild(x, 1));

        }
    }

    /**
     * @fn public void print()
	 * @brief Imprimeix l'arbre en preordre inicant cada fill amb un 0 o un 1 segons s'escaigui
     * i indicant el valor de les fulles.
	 */
    public void print() {
        print(0,0);
    }

    
}



