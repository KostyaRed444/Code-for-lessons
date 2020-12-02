package com.example.notes;

public class Item {
    private String _Name;
    private String _Data;

    public Item() {
    }

    public Item(String name, String data) {
        _Name = name;
        _Data = data;
    }

    public String getName() {
        return _Name;
    }

    public void setName(String name) {
        _Name = name;
    }

    public String getData() {
        return _Data;
    }

    public void setPrice(String price) {
        _Data = price;
    }
}
