package task1;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        Group<Integer> nestedGroup = new Group<>();
        Signature<Integer> s1 = new Signature<>(System.out::println);
        Signature<Integer> s2 = new Signature<>(x -> System.out.println(x * x));
        nestedGroup.addTask(s1).addTask(s2);
        Group<Integer> group = new Group<>();
        Signature<Integer> s3 = new Signature<>(x -> System.out.println(x * x * x));
        group.addTask(nestedGroup).addTask(s3);

        group.stamp(new StampingVisitor<>());

        group.apply(10);

        // check if the header is correct
        assertEquals(s1.getHeader("groups"), s2.getHeader("groups"));
        assertEquals(s1.getHeader("groups"), nestedGroup.getHeader("groups"));
        assertEquals(s3.getHeader("groups"), group.getHeader("groups"));
        assertNotEquals(s1.getHeader("groups"), s3.getHeader("groups"));
        assertNotEquals(s2.getHeader("groups"), s3.getHeader("groups"));
        assertNotEquals(nestedGroup.getHeader("groups"), s3.getHeader("groups"));
    }
}