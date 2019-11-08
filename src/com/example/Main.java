package com.example;

import java.io.File;
import java.io.FilenameFilter;

public class Main {

    public static void main(String[] args) {
	// write your code here
        File file = new File("/Users/thienle/Documents/FileIO/FileFolder/");
        String[] fileList = file.list();
        for (String name : fileList) {
            System.out.println(name);
        }

        filterFile();
    }

    public static void filterFile() {
        File file = new File("/Users/thienle/Documents/FileIO/FileFolder/");
        String[] files = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.toLowerCase().endsWith(".txt")) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        for (String f : files) {
            System.out.println(f);
        }
    }
}
