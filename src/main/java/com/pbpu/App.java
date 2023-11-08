package com.pbpu;

import java.util.Scanner;

import com.pbpu.domain.database.DatabaseDomain;
import com.pbpu.domain.ui.UIDomain;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args ){
        var input = new Scanner(System.in);

        var databaseDomain = new DatabaseDomain();
        var uiDomain = new UIDomain(databaseDomain);

        uiDomain.showCommandLine();

        input.close();
    }
}
