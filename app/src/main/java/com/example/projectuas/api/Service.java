package com.example.projectuas.api;

import com.example.projectuas.models.Book;
import com.example.projectuas.models.BookResponse;
import com.example.projectuas.models.Books;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Service {
    @GET("books")
    Call<Books> getBooks(
    );

    @GET("book/{id}")
    Call<BookResponse> getBook(
            @Path("id") int id
    );

    @FormUrlEncoded
    @PUT("book/{id}")
    Call<Book> updateBook (
            @Path("id") int id,
            @Field("name") String name,
            @Field("author") String author,
            @Field("image") String image,
            @Field("description") String description
    );

    Call<BookResponse> deleteBook(
            @Field("id") int bookId
    );
}
