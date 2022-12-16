package task2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ParserActive implements Parser {
    public JSONArray parse(String page) throws IOException {
        URL url = new URL(page);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        String text = new Scanner(connection.getInputStream()).useDelimiter("\\Z").next();

        Document document = Jsoup.parse(text);
        Elements tag = document.select("script[type=application/ld+json]");

        // json list object
        JSONArray jsonList = new JSONArray();
        for (Element element : tag) {
            String json = element.data();
            try {
                JSONObject jsonObject = new JSONObject(json);
                jsonList.put(jsonObject);
            } catch (JSONException ignored) {
            }
        }
        return jsonList;
    }
}
