package task1;

import java.util.ArrayList;
import java.util.Map;

public interface Visitor<T>{
    Map<String, String> onSignature(Task<T> task);
    Map<String, String> onGroupStart(Group<T> task);
    Map<String, String> onGroupEnd(Group<T> task);
}
