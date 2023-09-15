package com.samfrosh.martmobile.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samfrosh.martmobile.R;
import com.samfrosh.martmobile.dto.ProductStatus;

import java.util.List;


public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder> {

    List<ProductStatus> list;

     ItemClickListener itemClickListener;
    private int selectedItem = 0; // Store the selected item position

    public ProductCategoryAdapter(List<ProductStatus> list, ItemClickListener itemClickListener) {
        this.list = list;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category_recyclerview_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryNames.setText(list.get(position).getStatusName());

        // Check if the current item is selected and update text appearance accordingly
        if (position == selectedItem) {
            holder.categoryNames.setTextColor(Color.parseColor("#8E313A"));
        } else {
            holder.categoryNames.setTextColor(Color.BLACK);
        }

        holder.categoryNames.setOnClickListener( v -> {
            int previousSelected = selectedItem;
            selectedItem = position;
            notifyItemChanged(previousSelected);
            notifyItemChanged(selectedItem);

            itemClickListener.onItemClick(list.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryNames;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryNames = itemView.findViewById(R.id.category);
        }
    }

    public interface ItemClickListener {
        void onItemClick(ProductStatus productStatus);
    }
}
