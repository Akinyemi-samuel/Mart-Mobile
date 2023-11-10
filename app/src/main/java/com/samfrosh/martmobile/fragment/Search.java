package com.samfrosh.martmobile.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.samfrosh.martmobile.R;
import com.samfrosh.martmobile.databinding.FragmentSearchBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Search extends Fragment {

    FragmentSearchBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =FragmentSearchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        binding.searchingview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("")){
                    System.out.println("hello");
                    Toast.makeText(getContext(), "Hello there", Toast.LENGTH_SHORT).show();
                }else {
                    System.out.println("hello");
                    Toast.makeText(getContext(), "Hello there", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return view;
    }
}