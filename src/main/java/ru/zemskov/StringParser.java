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



    public Set<List<String>> parseFile() throws IOException {
        Set<List<String>> parsedLines = new HashSet<>();
        for (String a : strings) {
            String[] splitArray = a.split(";");
            parsedLines.add(List.of(splitArray));
        }
        return parsedLines;
    }
}
