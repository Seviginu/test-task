package ru.zemskov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        String filename = args.length == 1 ? args[0] : "test1.txt";

        try(FileInputStream fis = new FileInputStream(filename)) {
            StringParser parser = new StringParser(fis);
            String[] list = parser.parseNextLine();
            Arrays.stream(list).forEach(System.out::print);
            list = parser.parseNextLine();
            Arrays.stream(list).sequential().forEach(System.out::print);
        }
        catch (FileNotFoundException e){
            System.out.println("File doesn't exist");
        }
        catch (IOException e) {
            System.out.println("File read error");
        }
    }
}