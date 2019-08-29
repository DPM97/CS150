package com.company;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        /* create new file */
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.close();
        getInput();
    }

    public static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        String line = br.readLine();
        while(line != null) { /* make sure line has contents (will return null if blank */
            /* check if char is a letter (not a space) */
            int letters = 0;
            for (int i = 0; i < line.length(); i++) { if (line.charAt(i) !=  ' ') { letters++; } }
            String[] splitLine = line.split(" ");
            /* call write line given amount of words, letters, and last word */
            writeLine("Words: " +splitLine.length+ " Letters: " +letters+ " Last: " +splitLine[splitLine.length - 1]);
            line = br.readLine();
        }
        br.close(); /* cleanup */
    }

    public static void writeLine(String string) throws IOException {
        /* append to current file that was created in main */
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt", true));
        writer.append(string + "\n");
        writer.close(); /* cleanup */
    }
}
