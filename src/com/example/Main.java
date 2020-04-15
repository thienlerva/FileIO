package com.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        String geoserverUrl = "http://localhost:9191/geoserver";
        String hostname = "nvay";

        boolean isNonFRA = geoserverUrl != null && geoserverUrl.contains(hostname);

        System.out.println(isNonFRA);

        int i = 1;
        Integer I = (Integer) i;

        System.out.println(I);

        List<String> listA = Arrays.asList("A", "C", "C");
        List<String> listB = Arrays.asList("A", "B", "A");

        Set<String> setA = new HashSet<>(listA);
        Set<String> setB = new HashSet<>(listB);
        Set<String> results = listA.stream().filter(f -> !setB.contains(f)).collect(Collectors.toSet());

        System.out.println("testing");
        System.out.println(results);

        File[] directories = new File("/usr").listFiles(File::isDirectory);

        List<File> files = Arrays.asList(directories);
        List<String> fileList = new ArrayList<>();
        for(File f : files) {
            fileList.add(f.getAbsolutePath());
        }
        //System.out.println(fileList);

        File file = new File("/Users/thienle/Documents/FileIO/FileFolde/file.txt");
        //write("/Users/thienle/Documents/FileIO/FileFolder/file.txt");
        //getFile("/Users/thienle/Documents/FileIO/FileFolde/");
        //readBashScript();

        //File file = new File("script3.sh");
        //filterFile();
        //System.out.println(file.getName());
        //System.out.println(createScript("/Users/thienle/Documents/FileIO/FileFolder/script7.sh") ? "script7.sh is created" : "scipt.sh is not crated");

        //byteArrayInputStream();
        dataInputStreamDemo();
        dataInputStreamDemo2();
    }

    public static void byteArrayInputStream() {

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(12);

            while (byteArrayOutputStream.size() != 10) {
                // Get inputs from the user
                byteArrayOutputStream.write("hello".getBytes());
            }

            byte b[] = byteArrayOutputStream.toByteArray();
            System.out.println("Print the content, byte b[] " + Arrays.toString(b));

            for (int i = 0; i < b.length; i++) {
                // Printing the characters
                System.out.println((char) b[i] + "     ");
            }
            System.out.println("     ");

            int c;
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(b);
            System.out.println("Converting characters to Upper case");

            for (int i = 0; i < i; i++) {
                while ((c = byteArrayInputStream.read()) != -1) {
                    System.out.println(Character.toUpperCase((char) c));
                }
                byteArrayInputStream.reset();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void dataInputStreamDemo() {

        // Writing the data
        try (DataOutputStream dout = new DataOutputStream(new FileOutputStream("file.txt"))) {
            dout.writeDouble(1.1);
            dout.writeInt(55);
            dout.writeBoolean(true);
            dout.writeChar('4');
        } catch (FileNotFoundException ex) {
            System.out.println("Can't open output file");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }

        // Reading the data back
        try (DataInputStream din = new DataInputStream(new FileInputStream("file.txt"))) {

            // illustating readDouble() method
            double a = din.readDouble();
            int b = din.readInt();
            boolean c = din.readBoolean();
            char d = din.readChar();
            System.out.println("Values: " + a + " " + b + " " + c + " " + d);
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot Open the Input file file.txt");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void dataInputStreamDemo2() {
        // writing the data
        try ( DataOutputStream dout = new DataOutputStream(new FileOutputStream("file2.txt"))) {
            dout.writeByte(1);
            dout.writeFloat(4.4545f);
            dout.writeUTF("geeksforgeeks");
            dout.writeChars("GeeksforGeeks\n");
            dout.writeBytes("ABCDEFG");
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot open the output file");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Reading the data back
        try ( DataInputStream din = new DataInputStream(new FileInputStream("file2.txt"))) {
            //readByte() method
            byte t = din.readByte();
            float u = din.readFloat();
            String temp = din.readUTF();
            //String temp1 = din.readLine();

            System.out.println("Values: " + t + " " + u + " " + temp + " " );

            // illustrating skipBytes() method
            //skipping "AB"
            din.skipBytes(2);

            //illustrating readFully()
            byte[] b = new byte[4];
            din.readFully(b, 0, 4);
            System.out.println(Arrays.toString(b));
        } catch (FileNotFoundException ex) {
            System.out.println("Cannot open the input file: file2.dat" );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void getFile(String path) {

            File file = new File(path);
            File[] files = file.listFiles();

            if (files != null) {
                for (File f : files) {
                    System.out.println(f.getName());
                }
            }
    }

    public static void write(String script) {
        try {
            File file = new File(script);
            if (!file.exists()) {
                file.createNewFile();
            }
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean createScript(String script) {
        try {
            File file = new File(script);
            boolean hasCreated = false;
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
                File aFile = new File(f);
                System.out.println(aFile.getAbsolutePath());
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
