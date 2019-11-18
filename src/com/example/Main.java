package com.example;

import java.io.*;

public class Main {

    public static void main(String[] args) {

//        File file = new File("/Users/thienle/Documents/FileIO/FileFolder/");
//        String[] fileList = file.list();
//        for (String name : fileList) {
//            System.out.println(name);
//        }
        //readBashScript();

        File file = new File("/Users/thienle/Documents/script6.sh");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getName());
        //System.out.println(createScript("script6.sh") ? "script.sh is created" : "scipt.sh is not crated");
    }

    static boolean createScript(String script) {
        try {
            File file = new File(script);
            boolean hasCreated=false;
            if (!file.exists()) {
                hasCreated = file.createNewFile();
            }
            if (hasCreated) {
                Runtime.getRuntime().exec("chmod +x " + script);
                //file.setExecutable(true);
                System.out.println(file.getAbsolutePath() + " is executable :" + file.canExecute());
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static void readBashScript(String script) {
        try {
            Process proc = Runtime.getRuntime().exec(script); //Whatever you want to execute
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
