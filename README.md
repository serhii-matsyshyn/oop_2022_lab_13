# oop_2022_lab_13 Visitor, Proxy

Made by Serhii Matsyshyn

## Task 1

We have the following structure  
- group
    - NestedGroup
        - s1
        - s2
    - s3

So we can mark it as follows:
- group - [1]
- NestedGroup - [1,2]
- s1 - [1,2]
- s2 - [1,2]
- s3 - [1]

If you want to see marking as:
- group - []
- NestedGroup - [1]
- s1 - [1,2]
- s2 - [1,2]
- s3 - [1]  

then uncomment the line in StamplingVisitor:
```java
public Map<String, String> onGroupEnd(Group<T> group) {
    groups.remove(group.groupUuid);
//        group.setHeader("groups", groups.toString()); // uncomment this line to change behaviour

    return Map.of("groups", group.getHeader("groups"));
}
```

Stampling exactly does that marking. See code for more details of realization.  
