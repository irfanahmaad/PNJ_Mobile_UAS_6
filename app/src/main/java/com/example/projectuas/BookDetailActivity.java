package com.example.projectuas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {
    TextView name, author, description;
    ImageView image;

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
    }
}
