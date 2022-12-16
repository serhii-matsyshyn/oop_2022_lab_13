package task1;

import java.util.*;

public class Group<T> extends Task<T> {
    public String groupUuid;
    private List<Task<T>> tasks;

    public Group() {
        tasks = new ArrayList<>();
        headers = new HashMap<>();
        headers.put("groups", "");
    }

    public Group<T> addTask(Task<T> task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        return this;
    }

    @Override
    public void freeze() {
        super.freeze();
        groupUuid = UUID.randomUUID().toString();
        for (Task<T> task: tasks) {
            task.freeze();
        }
    }

    @Override
    public void stamp(Visitor<T> visitor) {
        this.setHeader("groups", visitor.onGroupStart(this).get("groups"));

        for (Task<T> task: tasks) {
            task.stamp(visitor);
        }

        this.setHeader("groups", visitor.onGroupEnd(this).get("groups"));
    }

    @Override
    public void apply(T arg) {
        this.freeze();
        tasks = Collections.unmodifiableList(tasks);
        for (Task<T> task: tasks) {
            task.apply(arg);
        }
    }
}
