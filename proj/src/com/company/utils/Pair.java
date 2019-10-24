package com.company.utils;

public class Pair<T1,T2> {
    //Attributes
    T1 L;
    
    T2 R;

    //Constructor
    public Pair(T1 i, T2 c) {
        L = i;
        R = c;
    }

    //Functions
    public T1 getLeft() {
        return L;
    }

    public T2 getRight() {
        return R;
    }
}