package org.example.util;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeMap;

public class ParserLaboratories {
    public static TreeMap<Class<?>, String> parserLaboratories(){
        String path = System.getProperty("user.dir") + "/src/main/java/Programming/FourthSemLab";
        var classes = new ArrayList<Class<?>>();
        var dirWhereLabs = new File(path);
        var files = dirWhereLabs.listFiles();
        assert files != null;
        for(var file:files){

        }
        return null;
    }

}
