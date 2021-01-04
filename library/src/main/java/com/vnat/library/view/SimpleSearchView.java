package com.vnat.library.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.vnat.library.R;
import com.vnat.library.databinding.LayoutSearchBinding;
import com.vnat.library.listener.OnActionIconListener;
import com.vnat.library.listener.OnSuggestionIconChangeListener;
import com.vnat.library.listener.OnSuggestionListener;
import com.vnat.library.listener.OnQueryTextChangeListener;
import com.vnat.library.util.Constant;
import com.vnat.library.util.DiffCallBack;
import com.vnat.library.util.Util;

import java.util.ArrayList;
import java.util.List;

public class SimpleSearchView extends LinearLayout implements
        TextWatcher,
        OnSuggestionListener,
        OnSuggestionIconChangeListener {

    private LayoutSearchBinding mBinding;
    private SuggestionAdapter mAdapter;

    private List<String> mSuggestionList = new ArrayList<>();
    private List<String> mNewSuggestionList;

    private OnQueryTextChangeListener onQueryTextChangeListener;
    private OnSuggestionListener onSuggestionListener;
    private OnActionIconListener onActionIconListener;

    private int mResIdLeftSuggeston = 0;
    private int mResIdRightSuggeston = 0;
    private String mQueryText = "";
    private boolean mCursorVisible = Constant.ATTRS_FOCUS_ON_EDITTEXT;
    private String TAG = "zzz";


    public SimpleSearchView(Context context) {
        super(context);
        init(null);
    }

    public SimpleSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SimpleSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SimpleSearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.layout_search, this);
        mBinding = LayoutSearchBinding.bind(this);
        mAdapter = new SuggestionAdapter(new DiffCallBack<>());

        initListeners();

        applyXmlAttributes(attrs);
    }

    private void applyXmlAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleSearchView);

        try {
            setSearchHint(typedArray.getString(R.styleable.SimpleSearchView_ssv_hint));
            setSearchHintTextColor(typedArray.getColor(R.styleable.SimpleSearchView_ssv_textColorHint,
                    Util.getColor(getContext(), R.color.hint)));
            setSearchText(typedArray.getString(R.styleable.SimpleSearchView_ssv_text));
            setSearchTextColor(typedArray.getColor(R.styleable.SimpleSearchView_ssv_textColor,
                    Util.getColor(getContext(), R.color.black)));
            setSearchTextSize(typedArray.getDimensionPixelSize(R.styleable.SimpleSearchView_ssv_textSize,
                    Constant.ATTRS_TEXT_SIZE_SP_DEFAULT));
            setSearchCursorVisible(typedArray.getBoolean(R.styleable.SimpleSearchView_ssv_cursorVisible,
                    Constant.ATTRS_FOCUS_ON_EDITTEXT));

        } finally {
            typedArray.recycle();
        }
    }

    private void initListeners() {
        mAdapter.setOnSuggestionListener(this);
        mAdapter.setOnSuggestionIconChangeListener(this);

        mBinding.imgLeftAction.setOnClickListener(v -> {
            if (onActionIconListener != null){
                onActionIconListener.onActionIconLeft();
            }
        });
        mBinding.imgRightAction.setOnClickListener(v -> {
            if (onActionIconListener != null){
                onActionIconListener.onActionIconRight();
            }else {
                mBinding.edtSearch.setText("");
            }
        });

        mBinding.edtSearch.addTextChangedListener(this);
        mBinding.edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (onQueryTextChangeListener != null) {
                    onQueryTextChangeListener.onQueryTextSubmit(mQueryText);
                }
                return true;
            }
            return false;
        });

    }

    private List<String> getSuggestionList(String query) {
        mNewSuggestionList = new ArrayList<>();

        if (!query.isEmpty()) {
            for (String item : mSuggestionList) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    mNewSuggestionList.add(item);
                }
            }
        }

        if (mNewSuggestionList.size() > 0) {
            mBinding.rcvSuggestion.setVisibility(VISIBLE);
            mBinding.viewDivider.setVisibility(VISIBLE);
        } else {
            mBinding.rcvSuggestion.setVisibility(GONE);
            mBinding.viewDivider.setVisibility(GONE);
        }

        return mNewSuggestionList;
    }

    /**
     * Set style
     */
    public void setSearchHint(String hint) {
        mBinding.edtSearch.setHint(hint != null ? hint : getContext().getString(R.string.search));
    }

    public void setSearchHintTextColor(int color) {
        mBinding.edtSearch.setHintTextColor(color);
    }

    public void setSearchText(String text) {
        mBinding.edtSearch.setText(text);
    }

    public void setSearchTextColor(int color) {
        mBinding.edtSearch.setTextColor(color);
    }

    public void setSearchTextSize(float size) {
        mBinding.edtSearch.setTextSize(size);
    }

    public void setSearchCursorVisible(boolean visible) {
        mCursorVisible = visible ? visible : !mCursorVisible;
        mBinding.edtSearch.setCursorVisible(mCursorVisible);
    }

    /**
     * Set listener
     */
    public void submitSuggestionList(List<String> suggestionList) {
        mSuggestionList = suggestionList;
    }

    public void submitSuggestionList(List<String> suggestionList, int limit) {
        mSuggestionList = suggestionList;
        Constant.LIMIT = limit;
    }

    public void setLeftSuggestionIcon(int mResIdLeftSuggeston) {
        this.mResIdLeftSuggeston = mResIdLeftSuggeston;
    }

    public void setRightSuggestionIcon(int mResIdRightSuggeston) {
        this.mResIdRightSuggeston = mResIdRightSuggeston;
    }

    public void setLeftActionIcon(int resId) {
        mBinding.imgLeftAction.setImageResource(resId);
    }

    public void setRightActionIcon(int resId) {
        mBinding.imgRightAction.setImageResource(resId);

    }

    public void setOnQueryTextChangeListener(OnQueryTextChangeListener onQueryTextChangeListener) {
        this.onQueryTextChangeListener = onQueryTextChangeListener;
    }

    public void setOnSuggestionListener(OnSuggestionListener onSuggestionListener) {
        this.onSuggestionListener = onSuggestionListener;
    }

    public void setOnActionIconListener(OnActionIconListener onActionIconListener) {
        this.onActionIconListener = onActionIconListener;
    }

    /**
     * Override
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String queryText = s.toString();
        mQueryText = queryText;
        if (onQueryTextChangeListener != null) {
            onQueryTextChangeListener.onQueryTextChange(queryText);
        }

        mAdapter.submitList(getSuggestionList(queryText));
        mBinding.rcvSuggestion.setAdapter(mAdapter);

    }

    @Override
    public void onSuggestionClick(int position) {
        if (onSuggestionListener != null) {
            onSuggestionListener.onSuggestionClick(position);
        }
    }

    @Override
    public void onSuggestionLongClick(int position) {
        if (onSuggestionListener != null) {
            onSuggestionListener.onSuggestionLongClick(position);
        }
    }

    @Override
    public void onSuggestionRightIconClick(int position) {
        if (onSuggestionListener != null) {
            onSuggestionListener.onSuggestionRightIconClick(position);
        }else {
            mBinding.edtSearch.setText(mNewSuggestionList.get(position).toLowerCase());
        }
    }

    @Override
    public void onSuggestionRightIconLongClick(int position) {
        if (onSuggestionListener != null) {
            onSuggestionListener.onSuggestionRightIconLongClick(position);
        }
    }

    @Override
    public void onLeftSuggestionIconChange(ImageView leftSuggestionIcon) {
        leftSuggestionIcon.setImageResource(mResIdLeftSuggeston != 0 ? mResIdLeftSuggeston : R.drawable.ic_search_black);
    }

    @Override
    public void onRightSuggestionIconChange(ImageView rightSuggestionIcon) {
        rightSuggestionIcon.setImageResource(mResIdRightSuggeston != 0 ? mResIdRightSuggeston : R.drawable.ic_arrow_up_left_black);
    }


}

