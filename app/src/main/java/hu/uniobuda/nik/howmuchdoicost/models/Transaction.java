package hu.uniobuda.nik.howmuchdoicost.models;

import java.util.Date;

public class Transaction {

    private int id;
    private String type;
    private String name;
    private double price;
    private String date;
    private String place;
    private int rating;
    private String comment;

    public Transaction(String type, String name, double price, String date, String place, int rating, String comment) {
     //   this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.date = date;
        this.place = place;
        this.rating = rating;
        this.comment = comment;
    }

    public Transaction(){

    }

    public int getId() {
        return id;
    }

  /*  public void setId(int id) {
        this.id = id;
    }
*/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
