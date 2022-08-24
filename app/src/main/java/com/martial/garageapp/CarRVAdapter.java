package com.martial.garageapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarRVAdapter extends RecyclerView.Adapter<CarRVAdapter.ViewHolder> {
    private ArrayList<CarRVModal> carRVModalArrayList;
    private Context context;

    public CarRVAdapter(ArrayList<CarRVModal> carRVModalArrayList, Context context) {
        this.carRVModalArrayList = carRVModalArrayList;
        this.context = context;
    }

    public void filterList(ArrayList<CarRVModal> filteredList) {
        carRVModalArrayList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_rv_item, parent, false);
        return new CarRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarRVAdapter.ViewHolder holder, int position) {
        CarRVModal carRVModel = carRVModalArrayList.get(position);
        holder.makeName.setText(carRVModel.getMakeName());
        holder.makeId.setText(carRVModel.getMakeID());
    }

    @Override
    public int getItemCount() {
        return carRVModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView makeName, makeId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            makeName = itemView.findViewById(R.id.Make_Name);
            makeId = itemView.findViewById(R.id.Make_ID);
        }
    }
}


