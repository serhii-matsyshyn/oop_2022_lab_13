package task2;

import org.json.JSONArray;

import java.io.IOException;

public class TimedParser implements Parser {
    private final Parser parser;

    public TimedParser(Parser parser) {
        this.parser = parser;
    }

    @Override
    public JSONArray parse(String link) throws IOException {
        long startTime = System.currentTimeMillis();
        JSONArray result = parser.parse(link);
        long endTime = System.currentTimeMillis();
        System.out.println("Time took to parse web link: " + (endTime - startTime) + "ms");
        return result;
    }
}