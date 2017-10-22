package com.huutrung.exercisesqlite;

/**
 * Created by Admin on 10/22/2017.
 */
public class Contact {
    private int id;
    private String name;
    private String number;
    private String address;
    private String date;
    private String time;
    private String gendle;

    public Contact(int id, String name, String number, String address, String date, String time, String gendle) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.address = address;
        this.date = date;
        this.time = time;
        this.gendle = gendle;
    }

    public Contact() {
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGendle() {
        return gendle;
    }

    public void setGendle(String gendle) {
        this.gendle = gendle;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", address='" + address + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", gendle='" + gendle + '\'' +
                '}';
    }
}