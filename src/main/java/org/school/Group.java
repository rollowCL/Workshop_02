package org.school;

public class Group {
    private int id;
    private String name;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "org.school.Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public String validate() {
        if ("".equals(this.name) || this.name == null) {
            return "Missing group name";
        }

        return "OK";
    }
}
