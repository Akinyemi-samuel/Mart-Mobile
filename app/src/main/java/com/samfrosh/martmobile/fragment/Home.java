package com.samfrosh.martmobile.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samfrosh.martmobile.R;
import com.samfrosh.martmobile.adapter.ProductCategoryAdapter;
import com.samfrosh.martmobile.databinding.FragmentHomeBinding;
import com.samfrosh.martmobile.dto.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private List<ProductCategory> productCategoryList = new ArrayList<>();
    FragmentHomeBinding binding;
    RecyclerView recyclerView;
    ProductCategoryAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);


        recyclerView = binding.recyclerview;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setHasFixedSize(true);

        adapter = new ProductCategoryAdapter(productCategoryList);
        recyclerView.setAdapter(adapter);

        loadItems();


        return binding.getRoot();
    }

    private void loadItems() {
        productCategoryList.add(new ProductCategory("Men"));
        productCategoryList.add(new ProductCategory("Women"));
        productCategoryList.add(new ProductCategory("Hats"));
        productCategoryList.add(new ProductCategory("Bags"));
        productCategoryList.add(new ProductCategory("Shoes"));
    }
}