package com.example.myapplication.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ItemThingBinding;
import com.example.myapplication.model.db.Thing;

import java.util.ArrayList;
import java.util.List;

public class ThingAdapter extends RecyclerView.Adapter<ThingAdapter.ViewHolder> {
    public List<Thing> things = new ArrayList<>();
    private Context context;
    private MyCallback<Thing> callback;

    public ThingAdapter(Context context, MyCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ThingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemThingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ThingAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(things.get(position).getTitle());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WriteActivity.class);
            intent.putExtra("thing", things.get(position));
            context.startActivity(intent);
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                callback.callback(things.remove(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return things.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView remove;

        public ViewHolder(@NonNull ItemThingBinding binding) {
            super(binding.getRoot());
            title = binding.textView3;
            remove = binding.imageView;
        }
    }
}
