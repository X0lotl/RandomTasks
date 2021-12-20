package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringReplace {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("String.txt");
        Scanner scanner = new Scanner(file);
        StringBuilder outputBuilder = new StringBuilder();
        final String neededStringToReplace = "text";
        String scannerNextLine;
        while (scanner.hasNextLine()) {
            outputBuilder.append(scanner.nextLine());
            System.out.println(outputBuilder);
        }
    }
}