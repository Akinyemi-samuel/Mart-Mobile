package com.samfrosh.martmobile.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samfrosh.martmobile.R;
import com.samfrosh.martmobile.dto.ProductCategory;

import java.util.List;


public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder> {

    List<ProductCategory> list;
    private int selectedItem = 0; // Store the selected item position

    public ProductCategoryAdapter(List<ProductCategory> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category_recyclerview_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryNames.setText(list.get(position).getCategoryName());

        // Check if the current item is selected and update text appearance accordingly
        if (position == selectedItem) {
            holder.categoryNames.setTextColor(Color.parseColor("#8E313A"));
        } else {
            holder.categoryNames.setTextColor(Color.BLACK);
        }
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int previousSelected = selectedItem;
                    selectedItem = getAdapterPosition();
                    notifyItemChanged(previousSelected);
                    notifyItemChanged(selectedItem);
                }
            });

        }
    }
}
