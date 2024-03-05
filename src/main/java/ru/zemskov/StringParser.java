package ru.zemskov;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class StringParser {
    private final Scanner scanner;

    public StringParser(InputStream is) {
        this.scanner = new Scanner(is);
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public String[] parseNextLine() throws IOException {
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            return line.split(";");
        } else {
            throw new IOException("End of file reached. Couldn't read next line");
        }
    }
}
