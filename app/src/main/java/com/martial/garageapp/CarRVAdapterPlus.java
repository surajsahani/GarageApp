package com.martial.garageapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarRVAdapterPlus extends RecyclerView.Adapter<CarRVAdapterPlus.ViewHolder> {
    private ArrayList<CarRVModalPlus> carRVModalArrayListPlus;
    private Context context;

    public CarRVAdapterPlus(ArrayList<CarRVModalPlus> carRVModalArrayList, Context context) {
        this.carRVModalArrayListPlus = carRVModalArrayList;
        this.context = context;
    }

    public void filterList(ArrayList<CarRVModalPlus> filteredList) {
        carRVModalArrayListPlus = filteredList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_rv_item_plus, parent, false);

        return new CarRVAdapterPlus.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarRVModalPlus carRVModelPlus = carRVModalArrayListPlus.get(position);
        holder.makeNamePlus.setText(carRVModelPlus.getMakeName());
        holder.modelNamePlus.setText(carRVModelPlus.getModelName());
    }

    @Override
    public int getItemCount() {
        return carRVModalArrayListPlus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView makeNamePlus,  modelNamePlus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            makeNamePlus = itemView.findViewById(R.id.Make_NamePlus);
            modelNamePlus = itemView.findViewById(R.id.Model_NamePlus);

        }
    }
}

