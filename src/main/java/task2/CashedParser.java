package task2;

import org.json.JSONArray;

import java.io.IOException;

public class CashedParser implements Parser {
    private final Parser parser;

    public CashedParser(Parser parser) {
        this.parser = parser;
    }

    public JSONArray parse(String link) throws IOException {
        // check is there is a cashed version of the website link in sql database
        // if there is, return it

        DBConnection dbConnection = DBConnection.getInstance();

        // check is there is a cashed version of the document in sql database (check by website link)
        String query = "SELECT text FROM website WHERE link = '" + link + "'";
        String result = dbConnection.executeQueryWithAnswer(query);

        if (result != null) {
            return new JSONArray(result);
        }

        // if there is not, parse the page
        JSONArray jsonArray = parser.parse(link);

        query = "INSERT INTO website (link, text) VALUES ('" + link + "', '" + jsonArray.toString().replaceAll("'", "") + "')";
        dbConnection.executeQuery(query);
        return jsonArray;
    }
}
