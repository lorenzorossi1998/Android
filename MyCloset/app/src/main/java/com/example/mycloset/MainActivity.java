package com.example.mycloset;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.classes.objects.Listeners;
import com.classes.objects.Utente;
import com.classes.utility.DB;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    DB db;
    MyCloset app;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        db = new DB("", "", "");

        db.connect();

        Listeners l = new Listeners() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(ArrayList<Object> list, Context context) {
                /*
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
                mRecyclerView.setHasFixedSize(true);
                GridLayoutManager mLayoutManager = new GridLayoutManager(context, 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                MyAdapter mAdapter = new MyAdapter(list, Utente.class);
                mRecyclerView.setAdapter(mAdapter);
                */

                ConstraintLayout cl = findViewById(R.id.layout);
                int y = 500;

                for (Object o : list) {
                    // castare l'oggetto
                    Utente utente = (Utente) o;
                    // usare l'oggetto
                    Log.d("DEBUG", "I received " + utente.toString());

                    TextView tw = new TextView(context);
                    tw.setText(utente.getNome());
                    tw.setX(20);
                    tw.setY(y);
                    y += 100;
                    cl.addView(tw);
                }
            }

            @Override
            public void onFailed(DatabaseError databaseError) {

            }
        };

        HashMap<String, String> filters = new HashMap<>();

        //filters.put("id", "-N853PjPD8zC7DZ3el4K");
        //filters.put("nome", "o");
        //filters.put("cognome", "i");

        db.SELECT("Utente", filters, l, this);

        //Log.d("DEBUG", users.toString());
        /*for (String key : users.keySet()) {
            TextView tw = new TextView(this);
            tw.setText(key);
            cl.addView(tw);
        }

        recyclerView = findViewById(R.id.recView);

        ArrayList<Item> list = db.SELECT("");
        ChatAdapter chatAdapter = new ChatAdapter(this, list);

        Button button = (Button) findViewById(R.id.Social);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
            }
        });*/
    }

    public void logout(View v) {
        auth.signOut();
        Toast.makeText(MainActivity.this, getString(R.string.logout_succeed), Toast.LENGTH_LONG).show();
    }

    public void save (View view){
        EditText t = (EditText) findViewById(R.id.ev_email);
        String text = t.getText().toString();

        String debug;
        if (!text.isEmpty()) {
            Utente utente = new Utente();
            utente.setNome(text);

            db.INSERT("Utente", utente, this);
            // positive alert
            debug = "Inserito";
        } else {
            // negative alert
            debug = "Non inserito";
        }
        Log.d("DEBUG", debug);
    }
}