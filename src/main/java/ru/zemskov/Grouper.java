package ru.zemskov;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

abstract class Grouper<T> {

    protected DSU dsu;
    protected List<HashMap<T, Integer>> columns = new ArrayList<>();
    protected List<List<T>> rows = new ArrayList<>();

    private void resizeColumns(int size) {
        while (columns.size() != size)
            columns.add(new HashMap<>());
    }

    public void addLine(T[] a) {
        if (columns.size() < a.length)
            resizeColumns(a.length);
        rows.add(new ArrayList<>(List.of(a)));
    }

    abstract public List<List<List<T>>> group();

    protected static class DSU {
        private final int[] parent;

        public DSU(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; ++i) {
                this.parent[i] = i;
            }
        }

        public int find_set(int v) {
            if (parent[v] == v)
                return v;
            int set = find_set(parent[v]);
            parent[v] = set;
            return set;
        }

        public void union_sets(int a, int b) {
            a = find_set(a);
            b = find_set(b);
            if (a != b)
                parent[b] = a;
        }
    }
}
