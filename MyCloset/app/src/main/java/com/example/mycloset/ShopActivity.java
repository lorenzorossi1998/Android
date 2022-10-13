package com.example.mycloset;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.classes.objects.Capo;
import com.classes.utility.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    private ArrayList<Capo> capi;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_shop);
        setContentView(R.layout.shop_card);

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        capi = new ArrayList<>();

        fetchData();
    }

    private void fetchData() {
        String url = "https://api.json-generator.com/templates/tBNTIrktaqTo/data?access_token=28if5gp9wkowci0a0ovtadsz9lpwgnzpo69g0rmw";

        /*
        TextView marcaTW = findViewById(R.id.marcaTW);
        TextView modelloTW = findViewById(R.id.modelloTW);
        TextView categoriaTW = findViewById(R.id.categoriaTW);
        TextView votoTW = findViewById(R.id.votoTW);
        TextView descrizioneTW = findViewById(R.id.descrizioneTW);
        ImageView imageView = findViewById(R.id.urlIW);
        Button linkBtn = findViewById(R.id.linkBTN);
         */
        TextView marcaTW = findViewById(R.id.brandText);
        TextView modelloTW = findViewById(R.id.titleText);
        TextView votoTW = findViewById(R.id.ratingText);
        TextView prezzoTW = findViewById(R.id.priceText);
        TextView valutaTW = findViewById(R.id.currencyText);
        ImageView imageView = findViewById(R.id.productImage);
        //ConstraintLayout linkBtn = findViewById(R.id.productInfo);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("search_results");

                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String marca = jsonObject.getString("brand");
                        String modello = jsonObject.getString("title");
                        //String categoria = jsonObject.getString("");
                        double voto = jsonObject.getDouble("rating");

                        JSONObject priceObj = jsonObject.getJSONObject("price");
                        double prezzo = priceObj.getDouble("value");
                        String valuta = priceObj.getString("symbol");

                        String productURL = jsonObject.getString("link");
                        String imageURL = jsonObject.getString("image");

                        Capo capo = new Capo();
                        capo.setMarca(marca);
                        capo.setModello(modello);
                        capo.setVoto(voto);
                        capo.setPrezzo(prezzo);
                        capo.setValuta(valuta);
                        capo.setProductURL(productURL);
                        capo.setImageURL(imageURL);

                        capi.add(capo);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Capo capo = new Capo("", "", "", 5.5, new ArrayList<String>(), "", "");
                for(Capo capo : capi) {
                    marcaTW.setText(capo.getMarca());
                    modelloTW.setText(capo.getModello());
                    //categoriaTW.setText(capo.getCategoria());
                    votoTW.setText(String.valueOf(capo.getVoto()));
                    prezzoTW.setText(String.valueOf(capo.getPrezzo()));
                    valutaTW.setText(capo.getValuta());
                    String desc = "";
                    if(capo.getDescrizione() != null) {
                        for (String d : capo.getDescrizione()) {
                            desc += d + " ";
                        }
                    }
                    //descrizioneTW.setText(desc);
                    Glide.with(ShopActivity.this).load(capo.getImageURL()).into(imageView);
                    /*linkBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            openUrl(capo.getProductURL());
                        }
                    });*/
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShopActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void openUrl(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}