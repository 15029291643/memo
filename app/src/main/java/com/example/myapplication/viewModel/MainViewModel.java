package com.example.myapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.model.ThingUtils;
import com.example.myapplication.model.db.Thing;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Application application;
    private MutableLiveData<List<Thing>> things = new MutableLiveData<List<Thing>>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<Thing>> getThings() {
        return things;
    }

    public void add(Thing thing) {
        new Thread(() -> {
            if (thing.getId() == 0) {
                ThingUtils.add(application, thing);
            } else {
                ThingUtils.update(application, thing);
            }
        }).start();
    }
    public void all() {
        new Thread(() -> things.postValue(ThingUtils.all(application))).start();

    }

    public void delete(int id) {
        new Thread(() -> ThingUtils.delete(application, id)).start();
    }
}
