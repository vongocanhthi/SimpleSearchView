package com.vnat.library.listener;

public interface OnSuggestionListener {
    void onSuggestionClick(String suggestionText, int position);
    void onSuggestionLongClick(String suggestionText, int position);
}