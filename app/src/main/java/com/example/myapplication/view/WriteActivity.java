package com.example.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityWriteBinding;
import com.example.myapplication.model.db.Thing;
import com.example.myapplication.viewModel.MainViewModel;

import java.io.Serializable;

public class WriteActivity extends AppCompatActivity {

    private com.example.myapplication.databinding.ActivityWriteBinding binding;
    private MainViewModel viewModel;
    private Boolean isNew = false;
    private Thing thing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        thing = (Thing) getIntent().getSerializableExtra("thing");
        if (thing != null) {
            binding.textView.setText(thing.getTitle());
            binding.textView2.setText(thing.getContent());
        }
        binding.button.setOnClickListener(v -> {
            if (thing == null) {
                thing = new Thing();
            }
            thing.setTitle(binding.textView.getText().toString());
            thing.setContent(binding.textView2.getText().toString());
            viewModel.add(thing);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}