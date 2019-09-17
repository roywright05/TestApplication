package com.example.testapplication.mvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    List <String> dataSet;

    public CustomAdapter(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvTitle.setText(dataSet.get(position));
        holder.tvSubTitle.setText(dataSet.get(position));

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{

        public TextView tvTitle, tvSubTitle;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubTitle = itemView.findViewById(R.id.tvSubtitle);
        }
    }


}
