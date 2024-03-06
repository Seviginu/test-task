package ru.zemskov;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static ru.zemskov.Utils.*;

public class Main {
    public static void stringGroup(String filename) {

        Set<List<String>> lines = null;
        try {

            List<String> content = Files.readAllLines(Path.of(filename));
            StringParser parser = new StringParser(content);
            lines = parser.parseFile();
        } catch (FileNotFoundException e) {
            System.out.println("Файла не существует");
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
        }
        if (lines == null) {
            System.out.println("Не удалось считать файл");
            return;
        }

        StringGrouper grouper = new StringGrouper();
        grouper.addLines(lines);
        var groups = grouper.group();
        removeCopies(groups);
        removeOneStringGroups(groups);
        Comparator<List<?>> listSizeComparator = Comparator.comparingInt(List::size);
        groups.sort(listSizeComparator.reversed());
        printGroups(groups);
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