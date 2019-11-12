package com.company.utils;


public class Node {
    //Attributes
    final Integer Limit = Integer.MAX_VALUE;

    Integer First;

    char c;

    Integer Left;

    Integer Right;


    //Constructor
    public Node(char c) {
        First = Left = Right = -1;
        this.c = c;
    }

    //Functions
    public void Modify_Left(Integer i) {
        this.Left = i;
    }

    public void Modify_Right(Integer i) {
        this.Right = i;
    }

    public void Modify_First(Integer i) {
        this.First = i;
    }

}