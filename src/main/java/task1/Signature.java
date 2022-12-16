package task1;


import java.util.HashMap;
import java.util.function.Consumer;

public class Signature<T> extends Task<T> {
    public Consumer<T> consumer;
    public Signature(Consumer<T> consumer) {
        this.consumer = consumer;
        this.headers = new HashMap<>();
    }

    public void apply(T arg) {
        this.freeze();
        consumer.accept(arg);
    }

    @Override
    public void stamp(Visitor<T> visitor) {
        this.setHeader("groups", visitor.onSignature(this).get("groups"));
    }
}
