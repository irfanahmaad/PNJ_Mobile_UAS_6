package com.example.projectuas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectuas.api.ConfigUtils;
import com.example.projectuas.api.Service;
import com.example.projectuas.models.BookResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookDetailActivity extends AppCompatActivity {
    TextView name, author, description;
    ImageView image;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        name = findViewById(R.id.name);
        author = findViewById(R.id.author);
        description = findViewById(R.id.description);
        image = findViewById(R.id.image);

        name.setText(getIntent().getStringExtra("name"));
        author.setText(getIntent().getStringExtra("author"));
        description.setText(getIntent().getStringExtra("description"));
        Picasso.get().load(getIntent().getStringExtra("image")).into(image);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

//        Call<BookResponse> deleteRequest = service.deleteBook(Integer.parseInt(getIntent().getStringExtra("id")));
//        deleteRequest.enqueue(new Callback<BookResponse>() {
//            @Override
//            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
//                // use response.code, response.headers, etc.
//            }
//
//            @Override
//            public void onFailure(Call<BookResponse> call, Throwable t) {
//                // handle failure
//            }
//        });
    }
}
