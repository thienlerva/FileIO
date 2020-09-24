package com.example;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DirectoryTree {
    static Map<Integer, List<String>> levelTreeMap = new HashMap<>();
    static Map<File, List<String>> map = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        String dirToList = System.getProperty("user.home") + File.separator + "Documents/testDir";

        List<DirectoryTreeNode> list = new ArrayList<>();
        DirectoryTreeNode file = new DirectoryTreeNode();
        file.filename = "/" + new File(dirToList).getName();
        list.add(file);
        createDirectoryTreeList(dirToList, 0, list);
        list.forEach(System.out::println);
    }


    static void directories(String startDir, int level) {

        File dir = new File(startDir);
        File[] firstLevelFiles = dir.listFiles();


        if (firstLevelFiles != null && firstLevelFiles.length > 0) {
            int key = level + 1;
            List<String> value = new ArrayList<>();

            for (File aFile : firstLevelFiles) {

                if (aFile.isDirectory()) {

                    value.add(aFile.getName());
                    levelTreeMap.put(key, value);
                    directories(aFile.getAbsolutePath(), level + 1);
                }
            }

        }

    }


    static void createDirectoryTreeList(String dirPath, int level, List<DirectoryTreeNode> list) {

        try {
            File[] files = sortCurrentDirectory(dirPath);
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    String separator = "../";
                    String space = "";
                    for (int i = 0; i < level; i++) {
                        separator += "../";
                        space += "   ";
                    }

                    if (level == 0) {
                        separator = "../";
                        space = " ";
                    }

                    DirectoryTreeNode file = new DirectoryTreeNode();

                    if (aFile.isDirectory()) {
                        file.filename = separator + aFile.getName();
                        System.out.println(file);
                        list.add(file);
                        createDirectoryTreeList(aFile.getAbsolutePath(), level + 1, list);
                    } else {
                        file.filename = space + aFile.getName();
                        System.out.println(file);
                        list.add(file);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception");
        }
    }


    static File[] sortCurrentDirectory(String path) {


            File dir = new File(path);
            File[] files = dir.listFiles(File::isFile);
            File[] directories = dir.listFiles(File::isDirectory);
            int fileArrayLength = 0;
            int directoryArrayLength = 0;
            if (files != null) {
                fileArrayLength = files.length;
            }

            if (directories != null) {
                directoryArrayLength = directories.length;
            }

            File[] result = new File[fileArrayLength + directoryArrayLength];
            int count = 0;
            for (int i = 0; i < fileArrayLength; i++) {
                result[i] = files[i];
                count++;
            }

            for (int j = 0; j < directoryArrayLength; j++) {
                result[count++] = directories[j];
            }
            return result;

    }


    private static class DirectoryTreeNode {
       String filename;
    }
}
