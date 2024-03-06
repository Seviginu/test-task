package ru.zemskov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class StringGrouper extends Grouper<String> {
    private void init_dsu() {
        this.dsu = new DSU(this.rows.size());
        int i = 0;
        for (var row : this.rows) {
            for (int j = 0; j < row.size(); ++j) {
                String element = row.get(j);
                if (element.equals("\"\"") || element.isEmpty()) continue;
                HashMap<String, Integer> column = this.columns.get(j);
                if (column.containsKey(element))
                    dsu.union_sets(i, column.get(element));
                else
                    column.put(element, i);
            }
            i++;
        }
    }


    @Override
    public List<List<List<String>>> group() {
        System.out.println(rows.size());
        init_dsu();

        List<List<List<String>>> groupsList = new ArrayList<>(rows.size());
        for (int i = 0; i < rows.size(); ++i) {
            groupsList.add(new ArrayList<>());
        }
        int i = 0;
        for (var row : rows) {
            int rowGroup = dsu.find_set(i);
            groupsList.get(rowGroup).add(row);
            i++;
        }

        Collections.reverse(groupsList);
        return groupsList;
    }
}
