package task2;

import lombok.Getter;
import org.json.JSONArray;

import java.io.IOException;

public interface Parser {
    JSONArray parse(String link) throws IOException;
}
