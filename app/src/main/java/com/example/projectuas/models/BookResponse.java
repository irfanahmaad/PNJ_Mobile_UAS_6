package com.example.projectuas.models;

import com.google.gson.annotations.SerializedName;

public class BookResponse {
    @SerializedName("values")
    private Book values;

    public BookResponse(Book book) { this.values = book;}

    public Book getBook(){
        return values;
    }
}
