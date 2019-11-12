package com.example;

import java.io.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        File file = new File("/Users/thienle/Documents/FileIO/FileFolder/");
//        String[] fileList = file.list();
//        for (String name : fileList) {
//            System.out.println(name);
//        }
        readBashScript();

    }

    public static void readBashScript() {
        try {
            Process proc = Runtime.getRuntime().exec("/Users/thienle/Documents/FileIO/myscript.sh"); //Whatever you want to execute
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                System.out.println(read.readLine());
            }
        } catch (IOException e) {

            System.out.println(e.getMessage());
        }
    }

    public static void filterFile() {
        try {
            File file = new File("/Users/thienle/Documents/FileIO/FileFolde/");
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
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
