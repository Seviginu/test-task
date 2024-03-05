package ru.zemskov;

import java.util.HashMap;

public class Grouper<T> {

    private static class nnn {

    }

    HashMap<T, Integer> elementToIndex = new HashMap<>();

    public void addLine(T[] a) {
        for(T el : a) {
            if(el == null) continue;
            if(elementToIndex.containsKey(el)){

            }
        }
    }
}
