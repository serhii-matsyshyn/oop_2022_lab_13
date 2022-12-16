package task1;

import java.util.*;

public class StampingVisitor<T> implements Visitor<T> {
    private final ArrayList<Object> groups;
    private final ArrayList<Object> stampedHeaders;

    public StampingVisitor() {
        this.groups = new ArrayList<>();
        this.stampedHeaders = new ArrayList<>();
        this.stampedHeaders.add("groups");
    }

    public StampingVisitor(ArrayList<Object> groups, ArrayList<Object> stampedHeaders) {
        this.groups = groups;
        this.stampedHeaders = stampedHeaders;
    }

    @Override
    public Map<String, String> onSignature(Task<T> task) {
        Map<String, String> map = new HashMap<>();
        map.put("groups", groups.toString());
        map.put("stamped_headers", stampedHeaders.toString());
        return map;
    }

    @Override
    public Map<String, String> onGroupStart(Group<T> group) {
        if (group.groupUuid == null) {
            group.groupUuid = UUID.randomUUID().toString();
        }

        if (!group.getHeader("groups").contains(group.groupUuid)) {
            groups.add(group.groupUuid);
            group.setHeader("groups", groups.toString());
        }

        return Map.of("groups", group.getHeader("groups"));
    }

    @Override
    public Map<String, String> onGroupEnd(Group<T> group) {
        groups.remove(group.groupUuid);
//        group.setHeader("groups", groups.toString()); // uncomment this line to change behaviour

        return Map.of("groups", group.getHeader("groups"));
    }
}