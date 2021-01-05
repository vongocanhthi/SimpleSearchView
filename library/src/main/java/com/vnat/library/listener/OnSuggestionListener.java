package com.vnat.library.listener;

public interface OnSuggestionListener {
    void onSuggestionClick(int position);
    void onSuggestionLongClick(int position);
    void onSuggestionRightIconClick(int position);
}