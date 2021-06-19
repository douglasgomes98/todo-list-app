package com.example.todo.view;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.entities.Item;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    Activity context;
    ArrayList<Item> items;

    public ItemsAdapter(Activity c, ArrayList<Item> i){
        context = c;
        items = i;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final String title = items.get(position).getTitle();
        final String desc = items.get(position).getDesc();
        final String key = items.get(position).getKey();

        holder.title.setText(title);
        holder.desc.setText(desc);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTaskActivity.class);
                intent.putExtra("itemTitle", title);
                intent.putExtra("itemDesc", desc);
                intent.putExtra("itemKey", key);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items == null ? 0: items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.itemTitle);
            desc = (TextView) itemView.findViewById(R.id.itemDesc);
        }
    }
}
