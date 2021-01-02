package com.vnat.library.view;

import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatEditText;

import com.vnat.library.R;
import com.vnat.library.databinding.ActivitySearchBinding;
import com.vnat.library.listeners.OnQueryTextListener;
import com.vnat.library.listeners.SearchSuggestion;
import com.vnat.library.utils.DiffCallBack;

import java.util.ArrayList;
import java.util.List;

public class SimpleSearchView extends LinearLayout implements
        TextWatcher {

    private ActivitySearchBinding mBinding;
    private SearchSuggestionAdapter mAdapter;
    private List<SearchSuggestion> mSearchSuggestionList = new ArrayList<>();
    private OnQueryTextListener onQueryTextListener;

    public SimpleSearchView(Context context) {
        super(context);
        init(context);
    }

    public SimpleSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleSearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.activity_search, this);
        mBinding = ActivitySearchBinding.bind(this);
        mAdapter = new SearchSuggestionAdapter(new DiffCallBack<>());

        initListeners();
    }

    private void initListeners() {
        mBinding.imgClear.setOnClickListener(v -> mBinding.edtSearch.setText(""));
        mBinding.edtSearch.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        filterQuery(s.toString());
    }

    private void filterQuery(String queryText) {
        onQueryTextListener.onQueryTextChange(queryText);
//        mAdapter.submitList(getSuggestionList(queryText));
//        mBinding.rcvSuggestion.setAdapter(mAdapter);
    }

    private List<SearchSuggestion> getSuggestionList(String query) {
        List<SearchSuggestion> suggestionsList = new ArrayList<>();
        for (SearchSuggestion suggestionText : mSearchSuggestionList) {
            if (suggestionText.toString().toLowerCase().contains(query.toLowerCase())) {
                suggestionsList.add(suggestionText);
            }
        }
        return suggestionsList;
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.onQueryTextListener = onQueryTextListener;
    }
}

