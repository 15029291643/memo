package com.example.myapplication.model;

import android.content.Context;

import com.example.myapplication.model.db.Thing;
import com.example.myapplication.model.db.ThingDatabase;

import java.util.List;

public class ThingUtils {
    public static void add(Context context, Thing thing) {
        ThingDatabase.getInstance(context).thingService().add(thing);
    }

    public static List<Thing> all(Context context) {
        return ThingDatabase.getInstance(context).thingService().all();
    }

    public static void update(Context context, Thing thing) {
        ThingDatabase.getInstance(context).thingService().update(thing);
    }

    public static void delete(Context context, int id) {
        ThingDatabase.getInstance(context).thingService().delete(id);
    }
}
