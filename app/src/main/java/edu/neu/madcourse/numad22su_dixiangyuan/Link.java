package edu.neu.madcourse.numad22su_dixiangyuan;

public class Link {
    private final String name;
    private final String link;
    public Link(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return this.name;
    }

    public String getLink() {
        return this.link;
    }
}
