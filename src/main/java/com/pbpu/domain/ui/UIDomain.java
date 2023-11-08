package com.pbpu.domain.ui;

import com.pbpu.utils.LineUtils;

public class UIDomain {

    public static final UIDomain INSTANCE = new UIDomain();

    private LineUtils utils;
    
    private UIDomain(){
        utils = LineUtils.INSTANCE;
    }

    public void showCommandLine(){
        this.utils.printLine();

        this.utils.printLine();
    }
}
