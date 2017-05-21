package io.codebonobos.utils;

/**
 * Created by afilakovic on 21.05.17..
 */
public class CodebookEntry {
    private int id;
    private String value;

    public CodebookEntry() {
    }

    public CodebookEntry(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
