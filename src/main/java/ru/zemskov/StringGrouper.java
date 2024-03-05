package ru.zemskov;

import java.util.*;

public class StringGrouper extends Grouper<String> {
    private void init_dsu() {
        this.dsu = new DSU(this.rows.size());
        for (int i = 0; i < this.rows.size(); ++i) {
            List<String> row = this.rows.get(i);
            for (int j = 0; j < row.size(); ++j) {
                String element = row.get(j);
                if (element.equals("\"\"")) continue;
                HashMap<String, Integer> column = this.columns.get(j);
                if (column.containsKey(element))
                    dsu.union_sets(i, column.get(element));
                else
                    column.put(element, i);
            }
        }
    }

    private void removeCopies(List<List<List<String>>> groupsList) {
        groupsList.replaceAll(lists -> lists.stream().distinct().toList());
    }

    private void removeOneStringGroups(List<List<List<String>>> groupsList) {
        groupsList.removeIf(lst -> lst.size() <= 1);
    }

    @Override
    public List<List<List<String>>> group() {
        init_dsu();

        List<List<List<String>>> groupsList = new ArrayList<>(rows.size());
        for (int i = 0; i < rows.size(); ++i) {
            groupsList.add(new ArrayList<>());
        }
        for (int i = 0; i < rows.size(); ++i) {
            int rowGroup = dsu.find_set(i);
            groupsList.get(rowGroup).add(rows.get(i));
        }
        removeCopies(groupsList);
        removeOneStringGroups(groupsList);
        groupsList.sort(Comparator.comparingInt(List::size));
        Collections.reverse(groupsList);
        return groupsList;
    }
}
