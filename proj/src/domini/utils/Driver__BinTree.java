package domini.utils;

import domini.utils.BinTree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class Driver__BinTree {
   
    private static void showOptions(){
        System.out.println("Driver de BinTree");
        System.out.println("Constructores: ");
        System.out.println("\t 1. BinTree()");
        System.out.println("\t BinTree: 1");
        System.out.println("\t 2. BinTree(int data)");
        System.out.println("\t BinTree: 2");
        System.out.println();

        System.out.println("Consultoras: ");
        System.out.println("\t 3. wellDefined()");
        System.out.println("\t BinTree: 3");
        System.out.println("\t 4. size()");
        System.out.println("\t BinTree: 4");
        System.out.println("\t 5. isInit(int x)");
        System.out.println("\t BinTree: 5");
        System.out.println("\t 6. isLeaf(int x)");
        System.out.println("\t BinTree: 6");
        System.out.println("\t 7. getData(int x)");
        System.out.println("\t BinTree: 7");
        System.out.println("\t 8. getChild(int x, int left_right)");
        System.out.println("\t BinTree: 8");
        System.out.println();

        System.out.println("\t 9. setData(int x, int data)");
        System.out.println("\t BinTree: 9");
        System.out.println("\t 10. setChild(int x, int left_right, BinTree child)");
        System.out.println("\t BinTree: 10");
        System.out.println();

        System.out.println("\t 0. Salir");
        System.out.println("\t BinTree: 0");

        System.out.println("----------------------------------------");
    }

    private static void comprovarExcepcions(int constructed, String linea){
        int op = Integer.parseInt(linea);
        if(op > 2 && constructed == 0){
            throw new IllegalArgumentException("Debes llamar al constructor antes");
        }
    }

    private static void print_res(String s1, int i, String s2) {
        System.out.print("\t ");
        System.out.print(s1);
        System.out.print(i);
        System.out.println(s2);
    }

    private static void print_res(String s1, int i1, String s2, int i2, String s3) {
        System.out.print("\t ");
        System.out.print(s1);
        System.out.print(i1);
        System.out.print(s2);
        System.out.print(i2);
        System.out.println(s3);
    }

    private static void selecciona_bintree(int n) {
        System.out.print("Introduce numero de BinTree creado sobre el que quieres operar (entre 0 y ");
        System.out.print(n-1);
        System.out.println(")");
    }

    public static void main(String[] args) {
        try {
            ArrayList<BinTree> arr = new ArrayList<BinTree>();
            showOptions();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String linea = "";
            while(linea != null){
                System.out.println();
                System.out.println("Selecciona una opcion:");
                linea = reader.readLine().trim();
                System.out.println("Opcion: " + linea + " seleccionada");
                comprovarExcepcions(arr.size(), linea);

                BinTree bt; int n, x, data, left_right; String s;
                switch(linea) {
                    case "1":
                        n = arr.size();
                        bt = new BinTree();
                        arr.add(bt);
                        print_res("BinTree numero ", n, " creado.");
                    break;
                    case "2":
                        System.out.println("Introduce el valor de la raiz (data) del nuevo BinTree");
                        data = Integer.parseInt(reader.readLine().trim());
                        n = arr.size();
                        bt = new BinTree(data);
                        arr.add(bt);
                        print_res("BinTree numero ", n, " creado.");
                    break;
                    case "3":
                        selecciona_bintree(arr.size());
                        n = Integer.parseInt(reader.readLine().trim());
                        if (arr.get(n).wellDefined())
                            System.out.println("\t El BinTree esta bien definido.");
                        else 
                            System.out.println("\t El BinTree tiene elementos indefinidos.");
                    break;
                    case "4":
                        selecciona_bintree(arr.size());
                        n = Integer.parseInt(reader.readLine().trim());
                        print_res("El BinTree tiene ", arr.get(n).size()," nodos.");
                    break;
                    case "5":
                        selecciona_bintree(arr.size());
                        n = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Indica un nodo");
                        x = Integer.parseInt(reader.readLine().trim());
                        if (arr.get(n).isInit(x))
                            print_res("El nodo ",x," está inicializado.");
                        else 
                            print_res("El nodo ",x," no está inicializado: sus dos hijos estan indefinidos.");
                    break;
                    case "6":
                        selecciona_bintree(arr.size());
                        n = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Indica un nodo");
                        x = Integer.parseInt(reader.readLine().trim());
                        if (arr.get(n).isLeaf(x))
                            print_res("El nodo ",x," es una hoja (tiene valor).");
                        else 
                            print_res("El nodo ",x," no es una hoja (no tiene valor).");
                    break;
                    case "7":
                        selecciona_bintree(arr.size());
                        n = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Indica un nodo");
                        x = Integer.parseInt(reader.readLine().trim());
                        print_res("El valor de la hoja ",x," es ",arr.get(n).getData(x),".");
                    break;
                    case "8":
                        selecciona_bintree(arr.size());
                        n = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Indica un nodo");
                        x = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Indica si quieres consultar el hijo izquierdo (0) o derecho(1)");
                        left_right = Integer.parseInt(reader.readLine().trim());
                        if (left_right%2 == 0) s = "izquierdo";
                        else s = "derecho";
                        print_res("El hijo "+s+" del nodo ",x," es el nodo ",arr.get(n).getChild(x, left_right),"");
                    break;
                    case "9":
                        selecciona_bintree(arr.size());
                        n = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Indica un nodo");
                        x = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Indica el valor que quiere introducir");
                        data = Integer.parseInt(reader.readLine().trim());
                        arr.get(n).setData(x, data);
                        print_res("El valor de la nueva hoja ",x," es ",arr.get(n).getData(x),".");
                    break;
                    case "10":
                        selecciona_bintree(arr.size());
                        n = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Indica un nodo");
                        x = Integer.parseInt(reader.readLine().trim());
                        System.out.println("Indica si quieres añadir el hijo izquierdo (0) o derecho(1)");
                        left_right = Integer.parseInt(reader.readLine().trim());
                        if (left_right%2 == 0) s = "izquierdo";
                        else s = "derecho";
                        System.out.println("Indica el numero de BinTree creado que quieres añadir como hijo");
                        data = Integer.parseInt(reader.readLine().trim());
                        bt = arr.get(data);
                        print_res("El nuevo hijo "+s+" del nodo ",x," es el nodo ",arr.get(n).setChild(x, left_right, bt),".");
                    break;
                    case "0":
                        return;
                    default:
                        System.out.println("La opción no es valida");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}