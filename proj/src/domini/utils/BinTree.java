package com.company.utils;

public class BinTree<T> { //falta comprovar
    T data;
    BinTree<T> left;
    BinTree<T> right;

    BinTree() {
        left = right = null;
        data = null;
    }

    BinTree(T x) {
        data = x;
        left = right = null;
    }

    BinTree(int x, BinTree<T> child) {
        if (x == 0) {left = child; right = null;}
        else {right = child; left = null;}
    }

    BinTree(BinTree<T> l, BinTree<T> r) {
        left = l; right = r;
    }

    
    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    
    public BinTree<T> getChild(int x) {
        if (x == 0) return left;
        return right;
    }

    public void setChild(int x, BinTree<T> child) {
        if (x == 0) left = child;
        else right = child;
    }

    public boolean isLeaf() {
        return (left == null) && (right == null);
    }

    public boolean exists(int x) {
        if (x == 0) return left != null;
        return right != null;
    }


}



