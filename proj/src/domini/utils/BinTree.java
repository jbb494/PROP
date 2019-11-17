package domini.utils;

import java.util.ArrayList;

public class BinTree {

    /**
     * Permet expressar arbres binaris on les fulles poden prendre valors enters.
     * Els nodes s'identifiquen amb enters.
     * Aquesta estroctura de dades permet afegir informació però no permet 
     * eliminar o modificar informació prèviament afegida,
     */
    
    /**
     * sigui x un node
     * si arr[2*x] = -2, x és una fulla i arr[2*x+1] és el valor de x
     * altrament x+arr[2*x] i x+arr[2*x+1] són els fills de x (-1 significa indefinit)
     * Un node no està inicialitzat <-> els dos fills son indefinits
     * El node arrel és 0
     */
    private ArrayList<Integer> arr;
    

    private int num_undefs;    

    public BinTree() {
        arr = new ArrayList<Integer>();
        arr.add(-1);
        arr.add(-1);
        num_undefs = 2;
    }

    public BinTree(int data) {
        arr = new ArrayList<Integer>();
        arr.add(-2);
        arr.add(data);
        num_undefs = 0;
    }

    private void checkNode(int x) {
        if (2*x >= arr.size() || x < 0) throw new IllegalArgumentException(Integer.toString(x)+" is an invalid node");
    }

    public boolean wellDefined() {
        return num_undefs == 0;
    }

    public int size() {
        return arr.size()/2;
    }

    public boolean isInit(int x) {
        checkNode(x);
        return arr.get(2*x) != -1 || arr.get(2*x+1) != -1;
    }

    public boolean isLeaf(int x) {
        checkNode(x);
        return arr.get(2*x) == -2;
    }

    //
    public int getData(int x) {
        if (!isLeaf(x)) throw new IllegalArgumentException("Non-leaf nodes don't contain data");
        return arr.get(2*x + 1);
    }

    public void setData(int x, int data) {
        if (isInit(x)) throw new IllegalArgumentException("This node has been alredy initialized");;
        arr.set(2*x, -2);
        arr.set(2*x+1, data);
        num_undefs -= 2;
    }

    //
    public int getChild(int x, int left_right) {
        if (isLeaf(x)) throw new IllegalArgumentException("Leaf nodes have no children");
        int despl = arr.get(2*x + (left_right&1));
        if (despl==-1) return -1;
        return x + despl;
    }

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

    private void print_v2(int tabs, int x) {
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
    }

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

    public void print() {
        print(0,0);
    }

    private void print_arr() {
        for (int i = 0; i < size(); ++i) {
            System.out.print(arr.get(2*i));
            System.out.print(" ");
            System.out.print(arr.get(2*i+1));
            System.out.print("  ");
        }
        System.out.println();
    }

    
}



