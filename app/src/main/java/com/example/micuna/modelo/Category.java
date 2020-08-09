package com.example.micuna.modelo;

public class Category {
    private String Name;
    private String Description;

    public Category(){

    }

    @Override
    public String toString() {
        return "Category{" +
                "Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }

    public Category(String name, String description){
        Name = name;
        Description = description;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
