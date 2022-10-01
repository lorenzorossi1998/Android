package com.example.mycloset;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mycloset.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<DataProvider.Content> mDataset;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public ViewHolder(View v) {
            super(v);
            image= (ImageView) v.findViewById(R.id.image);
        }
    }
    public MyAdapter(List<DataProvider.Content> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.armadio_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DataProvider.Content tmp=mDataset.get(position);
        holder.image.setBackgroundResource(tmp.getImage());
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
