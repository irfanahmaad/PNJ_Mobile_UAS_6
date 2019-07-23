package com.example.projectuas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectuas.R;
import com.example.projectuas.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    private List<Book> values;
    private Context mCtx;
    private View.OnClickListener mOnItemClickListener;

    public BookAdapter(List<Book> data, Context mCtx) {
        this.values = data;
        this.mCtx = mCtx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        final Book book =values.get(position);
        holder.name.setText(book.getName());
        holder.author.setText(book.getAuthor());
        holder.description.setText(book.getDescription().substring(0, 150) + "...");
        Picasso.get().load(book.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,author, description;
        private ImageView image;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            author = view.findViewById(R.id.author);
            description = view.findViewById(R.id.description);
            image = view.findViewById(R.id.image);

            view.setTag(this);
            view.setOnClickListener(mOnItemClickListener);
        }
    }

    //TODO: Step 2 of 4: Assign itemClickListener to your local View.OnClickListener variable
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}
