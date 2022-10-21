package com.classes.utility;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.classes.objects.Armadio;
import com.classes.objects.Capo;
import com.classes.objects.Utente;
import com.example.mycloset.R;
import com.example.mycloset.ShopActivity;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RVViewHolder> {
    Context context;
    Class c;
    ArrayList<Capo> list;
    int layout;

    public RecyclerViewAdapter(Context context, Class c, ArrayList<Capo> list, int layout) {
        this.context = context;
        this.c = c;
        this.list = list;
        this.layout = layout;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RVViewHolder holder, int position) {
        holder.tv_title.setText(list.get(position).getModello());
        Glide.with(context).load(list.get(position).getImageURL()).into(holder.iv_cloth);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RVViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_cloth;

        public RVViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_wardrobe_title);
            iv_cloth = itemView.findViewById(R.id.iv_wardrobe_cloth);
        }
    }
}
