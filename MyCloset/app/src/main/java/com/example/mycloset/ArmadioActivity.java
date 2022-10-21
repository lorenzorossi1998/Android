package com.example.mycloset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.classes.objects.Armadio;
import com.classes.objects.Capo;
import com.classes.objects.Listeners;
import com.classes.utility.DB;
import com.classes.utility.RecyclerViewAdapter;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashMap;

public class ArmadioActivity extends AppCompatActivity {
    DB db;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armadio);

        db = new DB("", "", "");
        db.connect();

        Listeners l = new Listeners() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(ArrayList<Object> list, Context context) {
                Armadio armadio = (Armadio) list.get(0);    // each user have only one wardrobe

                recyclerView = findViewById(R.id.rv_armadio);
                recyclerView.setHasFixedSize(true);

                layoutManager = new GridLayoutManager(context, 2);
                recyclerView.setLayoutManager(layoutManager);

                rvAdapter = new RecyclerViewAdapter(context, Capo.class, armadio.getCapi(), R.layout.armadio_row);
                recyclerView.setAdapter(rvAdapter);
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        };

        HashMap<String, String> filters = new HashMap<String, String>();
        filters.put("id", "-N853PjPD8zC7DZ3el4K");
        db.SELECT("Armadio", filters, l, this);
    }
}