package com.example.xquotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<QuotesViewholder> {

    Context context;
    List<Model> list;
    CopyListener copyListener;

    public Adapter(Context context, List<Model> list, CopyListener copyListener) {
        this.context = context;
        this.list = list;
        this.copyListener = copyListener;
    }

    @NonNull
    @Override
    public QuotesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuotesViewholder(LayoutInflater.from(context).inflate(R.layout.singlerow,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewholder holder, int position) {
      holder.quote.setText(list.get(position).getText());
      holder.author.setText(list.get(position).getAuthor());
      holder.copy.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              copyListener.onCopyClicked(list.get(holder.getAdapterPosition()).getText());
          }
      });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}


class QuotesViewholder extends RecyclerView.ViewHolder{

    TextView quote,author;
    Button copy;

    public QuotesViewholder(@NonNull View itemView) {
        super(itemView);
        quote = itemView.findViewById(R.id.tv_quote);
        author = itemView.findViewById(R.id.tv_author);
        copy = itemView.findViewById(R.id.button_copy);
    }
}
