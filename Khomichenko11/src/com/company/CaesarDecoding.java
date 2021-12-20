package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Decoding {
    private static void writeFile(String output) throws IOException {
        FileWriter writer = new FileWriter("decoded.txt");
        writer.write(output);
        writer.close();
    }

    private static String encodeString(String input, int alphabetShift) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length() - 1; i++) {
            char inputChar = input.charAt(i);
            char encodedChar = (char) (inputChar - alphabetShift);
            output.append(encodedChar);
        }
        return output.toString();
    }

    private static String readFile(final String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        StringBuilder outputBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            outputBuilder.append(scanner.nextLine() + "\n");
        }
        return outputBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name (or file path): ");
        final String filePath = scanner.nextLine();
        System.out.print("Enter alphabet shift: ");
        final int alphabetShift = scanner.nextInt();
        writeFile(encodeString(readFile(filePath), alphabetShift));

    }
}
