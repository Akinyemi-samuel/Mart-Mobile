package com.samfrosh.martmobile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
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
import com.samfrosh.martmobile.dto.ProductCategory;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private List<ProductCategory> productCategoryList = new ArrayList<>();
    FragmentHomeBinding binding;
    RecyclerView recyclerView;
    ProductCategoryAdapter adapter;

    private static final String PRODUCT_CATEGORY_API = "https://amused-tooth-production.up.railway.app/productstatus";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater);


        recyclerView = binding.recyclerview;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setHasFixedSize(true);



        loadProductCategoryItems();


        return binding.getRoot();
    }

    private void loadProductCategoryItems() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, PRODUCT_CATEGORY_API, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject item  = response.getJSONObject(i);
                        String categoryName = item.getString("statusName");
                        System.out.println(categoryName);

                        productCategoryList.add(new ProductCategory(categoryName));
                    }
                    adapter = new ProductCategoryAdapter(productCategoryList);
                    recyclerView.setAdapter(adapter);

                } catch (Exception e) {
                    System.out.println("errr"+e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error"+ error);
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}