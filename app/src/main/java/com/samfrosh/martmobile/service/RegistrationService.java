package com.samfrosh.martmobile.service;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.samfrosh.martmobile.environemnt.Environment;

import org.json.JSONObject;

public class RegistrationService {

    public void RegisterUser(Context context, JSONObject jsonObject){

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        final String url = Environment.getBaseUrl()+"user/new";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.e(TAG, "Registered user:{}  ");
                Toast.makeText(context, "Successfully registered", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "Error failed", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Volley Error: " + volleyError.getMessage());
            }
        });

        requestQueue.add(jsonObjectRequest);

    }
}
