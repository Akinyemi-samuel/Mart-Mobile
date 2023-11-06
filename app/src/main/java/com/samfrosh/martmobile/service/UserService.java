package com.samfrosh.martmobile.service;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.samfrosh.martmobile.MainActivity;
import com.samfrosh.martmobile.environemnt.Environment;

import org.json.JSONObject;

import java.util.Objects;

public class UserService {

    private final ValidateInputField validateInputField = new ValidateInputField();

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


    public void UserLogin(EditText em, EditText pw, Context context, Response.Listener<JSONObject> response ){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = Environment.getBaseUrl()+"user/login";

        final String email = validateInputField.apply(em);
        final String password = validateInputField.apply(pw);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                response.onResponse(jsonObject);
                Log.e("LOGIN TAG", "Login successful:{}  ");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("LOGIN ERROR ", Objects.requireNonNull(volleyError.getMessage()));
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
