package com.atharvakale.facerecognition;

public class Item {
    private String amount;
    private String date;
    private String description;

    public Item() {
        // Default constructor required for Firebase deserialization
    }


    public Item(String amount, String description,String date) {
        this.amount = amount;
        this.description = description;
        this.date=date;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
    public String getDate(){
        return date;
    }
}
