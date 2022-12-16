package task1;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Task<T> {
    private String id;
    Map<String, String> headers;

    public abstract void apply(T arg);

    public void freeze() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    protected void setHeader(String header, String headerValue) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put(header, headerValue);
    }

    public String getHeader(String header) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        return headers.get(header);
    }

    public abstract void stamp(Visitor<T> visitor);
}
