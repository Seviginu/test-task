package ru.zemskov;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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

        Collections.reverse(groupsList);
        return groupsList;
    }
}
