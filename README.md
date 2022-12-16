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

## Task 2
In second task we scrap information from link of website https://www.newhomesource.com/.  
Since it uses ld-json, we can use it to get all information about houses without the need to store entire html.  
In order to avoid multiple requests to the same link, we save all links in cache database.

Example of how cache db speeds up program:  
- no cache db: Time took to parse web link: 4792ms
- with cache db: Time took to parse web link: 1ms

**ATTENTION - sql injections possible**

