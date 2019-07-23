package com.example.projectuas;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.projectuas.adapter.BookAdapter;
import com.example.projectuas.api.ConfigUtils;
import com.example.projectuas.api.Service;
import com.example.projectuas.models.Books;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btncCreate;
    private RecyclerView recyclerViewNovel;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btncCreate = findViewById(R.id.btnCreate);

        btncCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });

        //recycler view
        recyclerViewNovel = findViewById(R.id.recycler_view);
        recyclerViewNovel.setHasFixedSize(true);
        recyclerViewNovel.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConfigUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call<Books> call = service.getBooks();

        call.enqueue(new Callback<Books>() {
            @Override
            public void onResponse(Call<Books> call, Response<Books> response) {
                adapter = new BookAdapter(response.body().getBooks(), getApplicationContext());
                recyclerViewNovel.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });



    }
}
