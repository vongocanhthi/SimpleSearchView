package com.vnat.simplesearchview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.vnat.library.listener.OnQueryTextChangeListener;
import com.vnat.library.listener.OnSuggestionListener;
import com.vnat.simplesearchview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;
    private final List<String> mSuggestionList = new ArrayList<>();

    private String TAG = "zzz";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initList();
        setIcon();
        listener();

    }

    private void listener() {
        mBinding.simpleSearchView.submitSuggestionList(mSuggestionList);

//        mBinding.simpleSearchView.setOnQueryTextChangeListener(new OnQueryTextChangeListener() {
//            @Override
//            public void onQueryTextChange(String queryText) {
//
//            }
//
//            @Override
//            public void onQueryTextSubmit(String queryText) {
//
//            }
//        });

//        mBinding.simpleSearchView.setOnSuggestionListener(new OnSuggestionListener() {
//            @Override
//            public void onSuggestionClick(int position) {
//
//            }
//
//            @Override
//            public void onSuggestionLongClick(int position) {
//
//            }
//
//            @Override
//            public void onSuggestionRightIconClick(int position) {
//
//            }
//
//            @Override
//            public void onSuggestionRightIconLongClick(int position) {
//
//            }
//        });

//        mBinding.simpleSearchView.seO

    }

    private void setIcon() {
//        mBinding.simpleSearchView.setLeftSuggestionIcon(R.drawable.ic_clear_black);
//        mBinding.simpleSearchView.setRightSuggestionIcon(R.drawable.ic_arrow_up_left_black);
//
//        mBinding.simpleSearchView.setLeftActionIcon(R.drawable.ic_search_black);
//        mBinding.simpleSearchView.setRightActionIcon(R.drawable.ic_clear_black);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (getCurrentFocus() != null) {
//            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        }
//        return super.dispatchTouchEvent(ev);
//    }

    private void initList() {
        mSuggestionList.add("Mouse");
        mSuggestionList.add("Buffalo");
        mSuggestionList.add("Tigers");
        mSuggestionList.add("Cat");
        mSuggestionList.add("Dragon");
        mSuggestionList.add("Snake");
        mSuggestionList.add("Horse");
        mSuggestionList.add("Goat");
        mSuggestionList.add("Monkey");
        mSuggestionList.add("Chicky");
        mSuggestionList.add("Dog");
        mSuggestionList.add("Pig");
        mSuggestionList.add("Rhino");
        mSuggestionList.add("Fish");
        mSuggestionList.add("A Frog");
        mSuggestionList.add("Cockroaches");

    }
}