package ru.zemskov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static ru.zemskov.Utils.*;

public class Main {
    public static void stringGroup(String filename) {
        try (FileInputStream fis = new FileInputStream(filename)) {
            StringParser parser = new StringParser(fis);
            StringGrouper grouper = new StringGrouper();
            fillGrouper(grouper, parser);
            var groups = grouper.group();
            removeCopies(groups);
            removeOneStringGroups(groups);
            Comparator<List<?>> listSizeComparator = Comparator.comparingInt(List::size);
            groups.sort(listSizeComparator.reversed());
            printGroups(groups);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }


    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Необходимо указать файл(java -jar app.jar *filename*.txt");
            return;
        }
        String filename = args[0];
        long startTime = System.nanoTime();
        stringGroup(filename);
        System.out.printf("Время работы программы: %d ms", (System.nanoTime() - startTime) / 1000000);
    }
}