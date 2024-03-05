package ru.zemskov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void printGroups(List<List<List<String>>> groups) {
        System.out.printf("Количество групп: %d\n", groups.size());
        int groupCounter = 0;
        for (var group : groups) {
            System.out.printf("ГРУППА %d\n", ++groupCounter);
            for (var row : group) {
                int elCounter = 0;
                for (var el : row) {
                    System.out.print(el);
                    if(++elCounter != row.size())
                        System.out.print(";");
                }
                System.out.println();
            }
        }
    }

    private static void fillGrouper(StringGrouper grouper, StringParser parser) throws IOException {
        while (parser.hasNextLine()) {
            grouper.addLine(parser.parseNextLine());
        }
    }

    public static void stringGroup(String filename) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            StringParser parser = new StringParser(fis);
            StringGrouper grouper = new StringGrouper();
            fillGrouper(grouper, parser);
            var groups = grouper.group();

            printGroups(groups);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }



    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Необходимо указать файл(java -jar app.jar *filename*.txt");
            return;
        }
        String filename = args[0];
        long startTime = System.nanoTime();
        stringGroup(filename);
        System.out.printf("Время работы программы: %d", (System.nanoTime()-startTime)/1000000);
    }
}