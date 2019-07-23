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
import com.example.projectuas.models.Book;
import com.example.projectuas.models.BookResponse;
import com.example.projectuas.models.Books;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btncCreate;
    private RecyclerView recyclerViewNovel;
    private RecyclerView.Adapter adapter;
    private List<Book> values;

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO: Step 4 of 4: Finally call getTag() on the view.
            // This viewHolder will have all required values.
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            // viewHolder.getItemId();
            // viewHolder.getItemViewType();
            // viewHolder.itemView;
//            final Book book = values.get(position);
//            Toast.makeText(MainActivity.this, "You Clicked: " + book.getName(), Toast.LENGTH_SHORT).show();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ConfigUtils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Service service = retrofit.create(Service.class);

            Call<BookResponse> call = service.getBook(position+1);

            call.enqueue(new Callback<BookResponse>() {
                @Override
                public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                    Intent intent = new Intent(getApplicationContext(), BookDetailActivity.class);
                    intent.putExtra("name", response.body().getBook().getName());
                    intent.putExtra("author", response.body().getBook().getAuthor());
                    intent.putExtra("description", response.body().getBook().getDescription());
                    intent.putExtra("image", response.body().getBook().getImage());
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<BookResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

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
                BookAdapter adapter = new BookAdapter(response.body().getBooks(), getApplicationContext());
                recyclerViewNovel.setAdapter(adapter);
                adapter.setOnItemClickListener(onItemClickListener);
            }

            @Override
            public void onFailure(Call<Books> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });



    }
}
