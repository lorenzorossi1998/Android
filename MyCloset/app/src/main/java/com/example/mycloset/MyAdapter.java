package com.example.mycloset;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.classes.objects.Utente;
import com.example.mycloset.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public ArrayList<Object> mDataset;
    public Class classe;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View v) {
            super(v);
            title= (TextView) v.findViewById(R.id.title);
        }
    }
    public MyAdapter(ArrayList<Object> myDataset, Class classe) {
        /*mDataset = new ArrayList<>();
        for(Object o : myDataset) {
            Object tmp = classe.cast(o);
            mDataset.add(tmp);
            Log.d("TAG", "MyAdapter: " + tmp.getClass());
        }*/
        mDataset = myDataset;
        this.classe = classe;

    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.armadio_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Object tmp = mDataset.get(position);
        //holder.title.setText(tmp.getTitle());
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
