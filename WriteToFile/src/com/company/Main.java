package com.company;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileName = "output.txt";
        write(fileName);
        read(fileName);
    }

    public static void write(String fileName) throws IOException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println("line 1");
        writer.println("line 2");
        writer.println("line 3");
        writer.close();
    }

    public static void read(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line = br.readLine();
        while(line != null) {
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
    }

}
