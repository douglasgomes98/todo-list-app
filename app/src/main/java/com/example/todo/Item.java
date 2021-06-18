package com.example.todo;

public class Item {
    String title, desc, date, key;

    public String getTitle() {
        return title;
    }

    public Item() {
    }

    public Item(String title, String desc, String date, String key) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
