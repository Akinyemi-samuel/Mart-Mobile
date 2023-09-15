package com.samfrosh.martmobile.fragment;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.samfrosh.martmobile.adapter.ProductCategoryAdapter;
import com.samfrosh.martmobile.databinding.FragmentHomeBinding;
import com.samfrosh.martmobile.dto.ProductStatus;
import com.samfrosh.martmobile.environemnt.Environment;
import com.samfrosh.martmobile.service.ProductService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment implements ProductCategoryAdapter.ItemClickListener {

    private List<ProductStatus> productStatusList = new ArrayList<>();

    FragmentHomeBinding binding;
    RecyclerView recyclerView, recyclerView2;
    ProductCategoryAdapter adapter;
    private static final String PRODUCT_CATEGORY_API = Environment.getBaseUrl() + "productstatus";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);


        recyclerView = binding.categoryRecyclerview;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setHasFixedSize(true);


        recyclerView2 = binding.productRecyclerview;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView2.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);


        loadProductCategoryItems();
        ProductService productService = new ProductService(recyclerView2);
        productService.getProductsByStatus(getContext(), "1");


        return binding.getRoot();
    }

    private void loadProductCategoryItems() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, PRODUCT_CATEGORY_API, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject item = response.getJSONObject(i);
                        String id = item.getString("id");
                        String statusName = item.getString("statusName");

                        productStatusList.add(new ProductStatus(id, statusName));
                    }
                    adapter = new ProductCategoryAdapter(productStatusList, Home.this);
                    recyclerView.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley Error: " + error.getMessage());
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onItemClick(ProductStatus productStatus) {
        ProductService productService = new ProductService(recyclerView2);
        productService.getProductsByStatus(getContext(), productStatus.getId());
    }
}