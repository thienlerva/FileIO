package com.example;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static List<String> fileTree = new ArrayList<>();

    public static void main(String[] args) throws IOException {

      //printService();


        String dirToList = System.getProperty("user.home") + File.separator + "Documents/testDir";

        DirectoryTreeFS node = sortDir(dirToList);
        System.out.println(node);

        listDirectory(dirToList, 0);
        //fileTree.forEach(System.out::println);
//        displayAll(new File(dirToList));
//
//        fileTree.forEach(System.out::println);
//        System.out.println("-----------");
//
//        listFiles(dirToList);

//        Files.walk(root.toPath())
//                .filter(path -> !Files.isDirectory(path))
//                .forEach(path -> System.out.println(path));

//        Files.walk(Paths.get(dirToList))
//                .filter(Files::isRegularFile)
//                .forEach(f -> System.out.println(f.getFileName()));

//        List<String> listA = Arrays.asList("A", "C", "C");
//        List<String> listB = Arrays.asList("A", "B", "A");
//
//        Set<String> setA = new HashSet<>(listA);
//        Set<String> setB = new HashSet<>(listB);
//        Set<String> results = listA.stream().filter(f -> !setB.contains(f)).collect(Collectors.toSet());
//
//        System.out.println("testing");
//        System.out.println(results);
//
//        File[] directories = new File("/usr").listFiles(File::isDirectory);
//
//        List<File> files = Arrays.asList(directories);
//        List<String> fileList = new ArrayList<>();
//        for(File f : files) {
//            fileList.add(f.getAbsolutePath());
//        }
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
        //dataInputStreamDemo();
        //dataInputStreamDemo2();

        byte b = 110;
        System.out.println("binay: " + (char) b);



        List<Customer> customers = Customer.initializeCustomerData();

        //DataOutputStream custOutput = Customer.createFile("customers.dat");

//        for(Customer person : customers) {
//            Customer.writeCustomerToFile(person, "customers.dat");
//        }

       // Customer.getFileInfo("customers.dat");
    }

    static List<String> displayAll(File path){

        if(path.isFile()){
            //System.out.println(path.getName());
            fileTree.add(path.getName());
        }else{
            //System.out.println(path.getName());
            fileTree.add("-" + path.getName());
            File[] files = path.listFiles();
            for(File dirOrFile: files){
                displayAll(dirOrFile);
            }
        }
        return fileTree;
    }

    static void displayTree(File path) {
        File[] files = path.listFiles();
        for (int i=0; i<files.length; i++) {

        }
    }

    static void listFiles(String startDir) {
        File dir = new File(startDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {

                if (file.isDirectory()) {
                    listFiles(file.getAbsolutePath());
                } else {
                    // We can use .length() to get the file size
                    System.out.println(file.getName() + " (size in bytes: " + file.length()+")");
                }
            }
        }
    }

    static List<DirectoryTreeFS> getTreeFS(String startDir) {
        List<DirectoryTreeFS> parent = new ArrayList<>();
        File dir = new File(startDir);
        File[] files = dir.listFiles();

        if (files != null && files.length > 0) {
            for (File file : files) {

            }
        }

        return parent;
    }

    static void listDirectory(String dirPath, int level) {
//        File dir = new File(dirPath);
//        File[] firstLevelFiles = dir.listFiles();
        File[] firstLevelFiles = sortedFiles(dirPath);
        if (firstLevelFiles != null && firstLevelFiles.length > 0) {
            for (File aFile : firstLevelFiles) {
                for (int i = 0; i < level; i++) {
                    System.out.print("\t");
                    fileTree.add("\t");
                }
                if (aFile.isDirectory()) {
                    System.out.println("[" + aFile.getName() + "]");
                    fileTree.add("[" + aFile.getName() + "]");
                    listDirectory(aFile.getAbsolutePath(), level + 1);
                } else {
                    System.out.println(aFile.getName());
                    fileTree.add(aFile.getName());
                }
            }
        }
    }

    static File[] sortedFiles(String dirPath) {

        File dir = new File(dirPath);
        File[] onlyFiles = dir.listFiles(File::isFile);
        File[] onlyDir = dir.listFiles(File::isDirectory);

        File[] result = new File[onlyFiles.length + onlyDir.length];
        int count = 0;
        for (int i=0; i<onlyFiles.length;i++) {
            result[i] = onlyFiles[i];
            count++;
        }

        for (int j = 0; j<onlyDir.length;j++) {
            result[count++] = onlyDir[j];
        }
        return result;
    }

    static DirectoryTreeFS sortDir(String rootDir) {
        File dir = new File(rootDir);
        File[] files = dir.listFiles();
        DirectoryTreeFS node = new DirectoryTreeFS();

        if (files != null && files.length >0) {
            node.directoryName = dir.getName();

            for ( int i =0; i<files.length; i++) {
                if (files[i].isFile()) {
                    node.fileNames.add(files[i].getName());
                } else if (files[i].isDirectory()) {
                    node.children.add(files[i]);
                }
            }
        }
        return node;
    }



    static void printService() {
        PrintService[] printServices = PrintServiceLookup
                .lookupPrintServices(null, null);
        PrintService defaultPrintService = PrintServiceLookup
                .lookupDefaultPrintService();
        PrintRequestAttributeSet attrib =
                new HashPrintRequestAttributeSet();
        PrintService selectedPrintService =
                ServiceUI.printDialog(null, 150, 150,
                        printServices, defaultPrintService, null, attrib);
        if(selectedPrintService!=null)
            System.out.println("selected printer:"
                    +selectedPrintService.getName());
        else
            System.out.println("selection cancelled");
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
            dout.writeShort(10);
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
            short e = din.readShort();
            System.out.println("Values: " + a + " " + b + " " + c + " " + d + " " + e);
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

    // inner class Customer
    private static class Customer {
        String custName;
        int custAge;
        double custDebt;
        boolean oweMoney;
        char custSex;

        public Customer() {}

        public Customer(String custName, int custAge, double custDebt, boolean oweMoney, char custSex) {
            this.custName = custName;
            this.custAge = custAge;
            this.custDebt = custDebt;
            this.oweMoney = oweMoney;
            this.custSex = custSex;
        }

        private static List<Customer> initializeCustomerData() {
            Customer[] customers = new Customer[5];

            customers[0] = new Customer("John Smith", 21, 12.25, true, 'M');
            customers[1] = new Customer("Sally Smith", 30, 2.25, true, 'F');
            customers[2] = new Customer("Paul Ryan", 21, 0, false, 'M');
            customers[3] = new Customer("Mark Jacobs", 21, 3.25, true, 'M');
            customers[4] = new Customer("Steve Nash", 21, 5.25, true, 'M');

            return Arrays.asList(customers);
        }

        // Create the file and DataOutputStream that will write to the file
        private static DataOutputStream createFile(String fileName) {
            File listOfNames = new File(fileName);

            try (DataOutputStream infoToWrite = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(listOfNames)
                    )
            )) {
                return infoToWrite;
            } catch (IOException e) {
                System.out.println("An I/O Error Occurred");
            }
            return null;
        }

        // Create a string with the customer info and write it to the file
        private static void createCustomerFile(Customer customer, DataOutputStream custOutput) {
            try {
                //write primitive data to the file
                custOutput.writeUTF(customer.custName);
                custOutput.writeInt(customer.custAge);
                custOutput.writeDouble(customer.custDebt);
                custOutput.writeBoolean(customer.oweMoney);
                custOutput.writeChar(customer.custSex);
            } catch (IOException e) {
                System.out.println("An I/O Error occurred");
            }
        }

        private static void writeCustomerToFile(Customer customer, String fileName) {
            try (DataOutputStream write = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(fileName, true)
                    )
            )) {
                //write primitive data to the file
                write.writeUTF(customer.custName);
                write.writeInt(customer.custAge);
                write.writeDouble(customer.custDebt);
                write.writeBoolean(customer.oweMoney);
                write.writeChar(customer.custSex);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("I/O Error Occurred");
            }
        }

        // Read info from the file and write it to the screen
        private static void getFileInfo(String fileName) {
            System.out.println("Info Writtten to File \n");

            File listOfNames = new File(fileName);
            boolean eof = false;

            try ( DataInputStream getInfo = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(listOfNames)
                    )
            )){
                while (!eof) {
                    String name = getInfo.readUTF();
                    int age = getInfo.readInt();
                    double debt = getInfo.readDouble();
                    boolean hasOwned = getInfo.readBoolean();
                    char sex = getInfo.readChar();

                    System.out.println(name + "\n" + age + "\n" + debt + "\n" + hasOwned + "\n" + sex + "\n");

                }
            } catch (EOFException e) {
                System.out.println("End of file");


            } catch (FileNotFoundException e) {
                System.out.println("Could not file the file");
            } catch (IOException e) {
                System.out.println("An I/O Error Occurred");
            }
        }
    }
}

class DirectoryTreeFS {
    String directoryName;
    List<String> fileNames = new ArrayList<>();
    List<File> children = new ArrayList<>();

    @Override
    public String toString() {
        return "DirectoryTreeFS{" +
                "directoryName='" + directoryName + '\'' +
                ", fileNames=" + fileNames +
                ", children=" + children +
                '}';
    }
}
