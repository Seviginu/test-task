package ru.zemskov;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Utils {
    public static void removeCopies(List<List<List<String>>> groupsList) {
        groupsList.replaceAll(lists -> lists.stream().distinct().toList());
    }

    public static void removeOneStringGroups(List<List<List<String>>> groupsList) {
        groupsList.removeIf(lst -> lst.size() <= 1);
    }


    public static void printGroups(List<List<List<String>>> groups, String filename) {
        try (
                FileWriter fstream = new FileWriter(filename);
                BufferedWriter writer = new BufferedWriter(fstream)
        ) {
            System.out.printf("Количество групп: %d\n", groups.size());
            int groupCounter = 0;
            for (var group : groups) {
                writer.write("Группа " + (++groupCounter) + "\n");
                for (var row : group) {
                    int elCounter = 0;
                    for (var el : row) {
                        writer.write(el);
                        if (++elCounter != row.size())
                            writer.write(";");
                    }
                    writer.write("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Не удалось записать в файл");
        }
    }
}
