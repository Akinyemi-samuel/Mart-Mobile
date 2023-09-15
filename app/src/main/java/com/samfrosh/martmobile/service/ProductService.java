package com.samfrosh.martmobile.service;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.samfrosh.martmobile.adapter.ProductAdapter;
import com.samfrosh.martmobile.dto.Product;
import com.samfrosh.martmobile.environemnt.Environment;
import com.samfrosh.martmobile.fragment.Home;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private List<Product> productList = new ArrayList<>();
    private ProductAdapter productAdapter;
    private RecyclerView recyclerView;

    Home home = new Home();


    public ProductService(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public void getProductsByStatus(Context context, String statusId) {
        final String PRODUCT_BY_STATUS_ID = Environment.getBaseUrl() + "product/bystatus/" + statusId + "/0/10";

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, PRODUCT_BY_STATUS_ID, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("content");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);

                        String name = item.getString("name");
                        String price = item.getString("unitPrice");

                        productList.add(new Product(name, price));
                    }
                    productAdapter = new ProductAdapter(productList);
                    recyclerView.setAdapter(productAdapter);
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

        requestQueue.add(jsonObjectRequest);

    }

}
