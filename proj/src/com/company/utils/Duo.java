package com.company.utils;

public class Duo {
    //Attributes
    Integer L;
    
    char R;

    //Constructor
    public Duo(Integer i, char c) {
        L = i;
        R = c;
    }

    //Functions
    public Integer getLeft() {
        return L;
    }

    public char getRight() {
        return R;
    }
}