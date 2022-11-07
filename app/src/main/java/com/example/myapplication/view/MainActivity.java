package com.example.myapplication.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.db.Thing;
import com.example.myapplication.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private com.example.myapplication.databinding.ActivityMainBinding binding;
    private MainViewModel viewModel;
    private ThingAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new ThingAdapter(this, new MyCallback<Thing>() {
            @Override
            public void callback(Thing thing) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("确定删除吗？")
                        .setPositiveButton("确定", (dialog, which) -> {
                            adapter.things.remove(thing);
                            adapter.notifyDataSetChanged();
                            viewModel.delete(thing.getId());
                        })
                        .setNegativeButton("取消", (dialog, which) -> {
                        }).show();

            }
        });
        binding.floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(this, WriteActivity.class));
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
        viewModel.getThings().observe(this, things -> {
            adapter.things.clear();
            adapter.things.addAll(things);
            adapter.notifyDataSetChanged();
        });
        viewModel.all();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.all();
    }
}