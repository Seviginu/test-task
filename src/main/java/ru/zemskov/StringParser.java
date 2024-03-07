package ru.zemskov;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringParser {
    private final List<String> strings;

    public StringParser(List<String> strings) {
        this.strings = strings;
    }

    private List<String> split(String string, char split) {
        int ptr = 0;
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        while (ptr < string.length()) {
            if (string.charAt(ptr) == split) {
                list.add(builder.toString());
                builder.setLength(0);
            } else {
                builder.append(string.charAt(ptr));
            }
            ptr++;
        }
        list.add(builder.toString());
        return list;
    }

    public Set<List<String>> parseFile() throws IOException {
        Set<List<String>> parsedLines = new HashSet<>();
        for (String a : strings) {
            parsedLines.add(split(a, ';'));
        }
        return parsedLines;
    }
}
