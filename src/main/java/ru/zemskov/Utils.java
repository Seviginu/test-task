package ru.zemskov;

import java.util.List;

public class Utils {
    public static void removeCopies(List<List<List<String>>> groupsList) {
        groupsList.replaceAll(lists -> lists.stream().distinct().toList());
    }

    public static void removeOneStringGroups(List<List<List<String>>> groupsList) {
        groupsList.removeIf(lst -> lst.size() <= 1);
    }

    public static void printGroups(List<List<List<String>>> groups) {
        System.out.printf("Количество групп: %d\n", groups.size());
        int groupCounter = 0;
        for (var group : groups) {
            System.out.printf("ГРУППА %d\n", ++groupCounter);
            for (var row : group) {
                int elCounter = 0;
                for (var el : row) {
                    System.out.print(el);
                    if (++elCounter != row.size())
                        System.out.print(";");
                }
                System.out.println();
            }
        }
    }
}
