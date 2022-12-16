package task2;


import org.json.JSONArray;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser = new TimedParser(new CashedParser(new ParserActive()));
        JSONArray array = parser.parse("https://www.newhomesource.com/homes/dc/washington-area");
        System.out.println(array);

        JSONArray array2 = parser.parse("https://www.newhomesource.com/homes/dc/washington-area");
        System.out.println(array2);

        JSONArray array3 = parser.parse("https://www.newhomesource.com/communities/ny/new-york-area");
        System.out.println(array3);

        JSONArray array4 = parser.parse("https://www.newhomesource.com/communities/ny/new-york-area");
        System.out.println(array4);

    }
}

