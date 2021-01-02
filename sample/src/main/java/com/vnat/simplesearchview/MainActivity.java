package com.vnat.simplesearchview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.vnat.library.listeners.OnQueryTextListener;
import com.vnat.library.view.SimpleSearchView;
import com.vnat.simplesearchview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private String TAG = "zzz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        //mBinding.setHandlers(this);
        setContentView(mBinding.getRoot());

    }


}