package ru.zemskov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String filename = args.length == 1 ? args[0] : "lng.txt";

        try (FileInputStream fis = new FileInputStream(filename)) {
            StringParser parser = new StringParser(fis);
            StringGrouper grouper = new StringGrouper();
            while (parser.hasNextLine()) {
                grouper.addLine(parser.parseNextLine());
            }
            var groups = grouper.group();
            System.out.printf("Количество групп: %d\n", groups.size());
            int groupCounter = 0;
            for (var group : groups) {
                if (group.size() <= 1) continue;
                System.out.printf("ГРУППА %d\n", ++groupCounter);
                for (var row : group) {
                    for (var el : row) {
                        System.out.print(el);
                        System.out.print(";");
                    }
                    System.out.println();
                }
            }
            System.out.printf("Время работы программы: %d ms\n", (System.nanoTime() - startTime) / 1000000);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        }
    }
}