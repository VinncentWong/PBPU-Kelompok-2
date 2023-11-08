package com.pbpu.utils;

public class LineUtils {

    public static LineUtils INSTANCE = new LineUtils();
    
    private LineUtils(){}

    public void printLine(){
        System.out.println("============================================================");
    }
}
